// Function to get query parameters from the URL
function getQueryParams() {
    const urlParams = new URLSearchParams(window.location.search);
    const userType = urlParams.get('usertype'); // Get the usertype (tutor/student)
    const id = urlParams.get('userid'); // Get the id
    return { userType, id };
}

const params = new URLSearchParams(window.location.search);
const usertype = params.get('usertype');
const userid = params.get('userid');

// Function to create a course div and append it
function displayCourse(courseData, containerElement) {
    const courseDiv = document.createElement('div');
    courseDiv.classList.add('course');

    // Add course name and description to the div
    courseDiv.innerHTML = `
        <h3><a href="/course?id=${courseData.id}&usertype=${usertype}&userid=${userid}">${courseData.name}</a></h3>
        <strong>Description:</strong> ${courseData.description} <br>
    `;

    // Append the course div to the container
    containerElement.appendChild(courseDiv);
}

// Function to fetch LMS and courses for a given user
async function fetchLMSData() {
    const { userType, id } = getQueryParams();

    if (!userType || !id) {
        console.error('Missing usertype or id in the query parameters.');
        return;
    }

    try {
        // Fetch LMS information based on usertype
        let lmsUrl = '';
        if (userType === 'tutor') {
            lmsUrl = `/api/tutor/${id}/lms`;
        } else if (userType === 'student') {
            lmsUrl = `/api/students/${id}/lms`;  // Fetch student LMS data
        }

        if (userType == 'student') {
            const element = document.getElementById('courses-header');
            if (element) {
                element.remove();
            }
        }

        // Fetch the LMS data
        const lmsResponse = await fetch(lmsUrl);
        if (!lmsResponse.ok) throw new Error('Failed to fetch LMS data');
        const lmsData = await lmsResponse.json();
        console.log('LMS Data:', lmsData);

        // Display LMS Information (for student)
        if (lmsData.length > 0) {
            const lmsNameElement = document.getElementById('lms-name');
            lmsNameElement.innerHTML = ''; // Clear any previous data
            lmsData.forEach(async (lms) => {
                // For each LMS, display tutor id and fetch courses
                const tutor_url = `/api/tutors/id/${lms.tutor_id}`;
                const tutor_response = await fetch(tutor_url);
                const tutor_data = await tutor_response.json(); // Parse the JSON response
                const lmsItem = document.createElement('div');
                lmsItem.innerHTML = `<strong>LMS Tutor:</strong> ${tutor_data.name} <br>`;
                lmsNameElement.appendChild(lmsItem);

                // Now fetch courses for each LMS id
                const coursesResponse = await fetch(`/api/lms/${lms.id}/courses`);
                if (!coursesResponse.ok) throw new Error('Failed to fetch courses for LMS');
                const coursesData = await coursesResponse.json();
                console.log(`Courses for LMS ${lms.id}:`, coursesData);

                // Display courses for the current LMS
                const coursesContainer = document.createElement('div');
                coursesContainer.classList.add('courses-container');
                coursesData.forEach(course => {
                    displayCourse(course, coursesContainer); // Use displayCourse function to display each course
                });

                // Append the courses list under the LMS info
                lmsItem.appendChild(coursesContainer);
            });
        }

        // Now fetch courses if the usertype is a tutor
        if (userType === 'tutor') {
            const coursesResponse = await fetch(`/api/tutor/${id}/courses`);
            if (!coursesResponse.ok) throw new Error('Failed to fetch courses');
            const coursesData = await coursesResponse.json();
            console.log('Courses Data:', coursesData);


        }
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}

// Call the function when the page loads
window.onload = fetchLMSData;

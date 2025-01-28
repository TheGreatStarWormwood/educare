document.addEventListener("DOMContentLoaded", async function () {
    const params = new URLSearchParams(window.location.search);
    const courseId = params.get('id');
    const usertype = params.get('usertype');
    const userid = params.get('userid');

    if (!courseId) {
        alert("Invalid course ID.");
        return;
    }

    try {
        // Fetch and display course details
        const courseResponse = await fetch(`/api/courses/${courseId}`);
        const courseData = await courseResponse.json();

        if (courseData) {
            const course = courseData[0];  // Assuming we receive an array with one course object
            document.getElementById('course-name').textContent = course.name;
            document.getElementById('course-description').textContent = course.description;
        } else {
            alert("Failed to load course details.");
        }

        // Fetch and display materials
        const materialsResponse = await fetch(`/api/courses/${courseId}/materials`);
        const materialsData = await materialsResponse.json();

        const materialsContainer = document.getElementById('materials-container');
        if (materialsData && materialsData.length > 0) {
            materialsContainer.innerHTML = '';
            materialsData.forEach(material => {
                const materialItem = document.createElement('div');
                materialItem.classList.add('item');
                materialItem.innerHTML = `
                    <h3>${material.title}</h3>
                    <a href="/materials/${material.file}" target="_blank">Download</a>
                `;
                materialsContainer.appendChild(materialItem);
            });
        } else {
            materialsContainer.innerHTML = "<p>No materials found.</p>";
        }

        // Fetch and display assignments
        const assignmentsResponse = await fetch(`/api/courses/${courseId}/assignments`);
        const assignmentsData = await assignmentsResponse.json();

        const assignmentsContainer = document.getElementById('assignments-container');
        if (assignmentsData && assignmentsData.length > 0) {
            assignmentsContainer.innerHTML = '';
            // Inside your existing loop for assignments:
            assignmentsData.forEach(assignment => {
                const assignmentItem = document.createElement('div');
                assignmentItem.classList.add('item');
                assignmentItem.innerHTML = `
        <h3>${assignment.title}</h3>
        <p>${assignment.description}</p>
        <a href="/assignments/${assignment.file}" target="_blank">Download</a>
        <form id="upload-form-${assignment.id}" class="upload-form" enctype="multipart/form-data">
            <input type="file" name="file" id="file-${assignment.id}" />
            <button type="submit">Submit Assignment</button>
        </form>
        <div id="upload-status-${assignment.id}" class="upload-status"></div>
    `;
                assignmentsContainer.appendChild(assignmentItem);

                // Add event listener to the form
                const form = document.getElementById(`upload-form-${assignment.id}`);
                form.addEventListener('submit', async function (e) {
                    e.preventDefault();
                    const fileInput = document.getElementById(`file-${assignment.id}`);
                    const file = fileInput.files[0];

                    if (!file) {
                        alert('Please select a file to upload.');
                        return;
                    }

                    const statusDiv = document.getElementById(`upload-status-${assignment.id}`);
                    statusDiv.textContent = 'Uploading...';

                    const formData = new FormData();
                    formData.append('file', file);
                    formData.append('assignmentId', assignment.id);

                    try {
                        const response = await fetch(`/api/courses/${courseId}/assignments/${assignment.id}/submit`, {
                            method: 'POST',
                            body: formData,
                        });

                        const result = await response.json();
                        if (response.ok) {
                            statusDiv.textContent = 'File uploaded successfully!';
                        } else {
                            statusDiv.textContent = `Error: ${result.message}`;
                        }
                    } catch (error) {
                        console.error('Upload failed:', error);
                        statusDiv.textContent = 'Error uploading file.';
                    }
                });
            });

        } else {
            assignmentsContainer.innerHTML = "<p>No assignments found.</p>";
        }
    } catch (error) {
        console.error("Error loading course details:", error);
        alert("Failed to load course page.");
    }
});

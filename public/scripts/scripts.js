document.addEventListener("DOMContentLoaded", function () {
    // Fetch tutor data when the page loads
    let allTutors = []; // To store all tutor data
    fetchTutors();

    const params = new URLSearchParams(window.location.search);
    const usertype = params.get('usertype');
    const userid = params.get('userid');

    // Function to fetch tutors from the server
    async function fetchTutors() {
        try {
            const response = await fetch('/tutors'); // Fetch all tutors
            const responseData = await response.json();

            if (responseData.success) {
                allTutors = responseData.data; // Store tutors in a global variable
                displayTutors(allTutors); // Display all tutors initially
            } else {
                console.error('Failed to fetch tutors:', responseData.message);
            }
        } catch (error) {
            console.error('Error fetching tutors:', error);
            alert("Failed to fetch tutors.");
        }
    }

    // Display tutors on the page
    function displayTutors(tutors) {
        const tutorItemsContainer = document.getElementById('tutor-items');
        tutorItemsContainer.innerHTML = ''; // Clear previous results

        if (tutors.length === 0) {
            tutorItemsContainer.innerHTML = "<p>No tutors found based on your search.</p>";
            return;
        }

        tutors.forEach(tutor => {
            const tutorItem = document.createElement('div');
            tutorItem.classList.add('tutor-item');
            tutorItem.innerHTML = `
                <h3><a href="/tutor-profile?id=${tutor.id}&usertype=${usertype}&userid=${userid}">${tutor.name}</a></h3>
                <p>${tutor.description}</p>
                <p><strong>Average Rating:</strong> ${getAverageRating(tutor.reviews)}</p>
            `;
            tutorItemsContainer.appendChild(tutorItem);
        });
    }

    // Function to calculate the average rating
    function getAverageRating(reviews) {
        if (!reviews || reviews.length === 0) {
            return 'No ratings yet';
        }

        const totalStars = reviews.reduce((sum, review) => sum + review.stars, 0);
        const averageStars = totalStars / reviews.length;
        return averageStars.toFixed(1); // Display the average with 1 decimal place
    }

    // Handle search form submission
    document.getElementById('search-form').addEventListener('submit', function (event) {
        event.preventDefault();

        const searchQuery = document.getElementById('search').value.trim().toLowerCase();

        // Filter tutors based on their description
        const filteredTutors = allTutors.filter(tutor =>
            tutor.description.toLowerCase().includes(searchQuery)
        );

        displayTutors(filteredTutors); // Display filtered tutors
    });
});

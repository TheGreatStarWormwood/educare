document.addEventListener("DOMContentLoaded", function () {
    // Fetch tutor data when the page loads
    fetchTutors();

    // Function to fetch tutors from the server
    async function fetchTutors(query = '') {
        try {
            const url = query ? `/tutors?search=${query}` : '/tutors'; // Add query if search term is present
            const response = await fetch(url);
            const responseData = await response.json();

            if (responseData.success) {
                const tutors = responseData.data; // Access the tutors from the "data" field
                displayTutors(tutors); // Pass tutors to display function
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
        tutorItemsContainer.innerHTML = '';  // Clear previous results

        if (tutors.length === 0) {
            tutorItemsContainer.innerHTML = "<p>No tutors found based on your search.</p>";
            return;
        }

        tutors.forEach(tutor => {
            const tutorItem = document.createElement('div');
            tutorItem.classList.add('tutor-item');
            tutorItem.innerHTML = `
                <h3><a href="/tutor-profile?id=${tutor.id}">${tutor.name}</a></h3>
                <p>${tutor.description}</p>
        
                <p><strong>Average Rating:</strong> ${getAverageRating(tutor.reviews)}</p>
            `;
            tutorItemsContainer.appendChild(tutorItem);
        });
        
    }

    // Function to calculate the average rating
    function getAverageRating(reviews) {
        if (reviews.length === 0) {
            return 'No ratings yet';
        }

        const totalStars = reviews.reduce((sum, review) => sum + review.stars, 0);
        const averageStars = totalStars / reviews.length;
        return averageStars.toFixed(1);  // Display the average with 1 decimal place
    }

    // Handle search form submission
    document.getElementById('search-form').addEventListener('submit', function (event) {
        event.preventDefault();

        const searchQuery = document.getElementById('search').value.trim();
        fetchTutors(searchQuery); // Call fetchTutors with search query
    });
});

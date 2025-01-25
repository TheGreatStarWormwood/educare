document.addEventListener('DOMContentLoaded', function () {
    const params = new URLSearchParams(window.location.search);
    const tutorId = params.get('id');

    // Fetch tutor data from the backend API
    fetch(`/tutors/${tutorId}`)
        .then(response => response.json())
        .then(tutor => {
            if (tutor) {
                const tutorDetailsContainer = document.getElementById('tutor-profile');
                tutorDetailsContainer.innerHTML = `
                    <div class="tutor-header">
                        <div class="tutor-info">
                            <img src="${tutor.image}" alt="${tutor.name}" class="tutor-image-profile">
                            <h2>${tutor.name}</h2>
                        </div>
                        <p class="tutor-description">${tutor.description}</p>
                    </div>

                    <div class="horizontal-sep"></div>

                    <!-- Dynamically insert listings and reviews containers here -->
                    <div id="listingsContainer"></div>
                    <div id="reviewsContainer"></div>
                `;
                // Fetch tutor listings
                fetch(`/tutors/${tutorId}/listings`)
                    .then(response => response.json())
                    .then(listings => {
                        console.log('Tutor Listings:', listings); // Log the listings JSON to the console

                        // Display listings in HTML
                        const listingsContainer = document.getElementById('listingsContainer');
                        if (listings.length > 0) {
                            listingsContainer.innerHTML = `
                <h3>Listings:</h3>
                <ul>
                    ${listings.map(listing => `<li>${listing.keywords}</li>`).join('')}
                </ul>
            `;
                        } else {
                            listingsContainer.innerHTML = 'No listings available.';
                        }
                    })
                    .catch(err => console.error('Error fetching tutor listings:', err));

                // Fetch tutor reviews
                fetch(`/tutors/${tutorId}/reviews`)
                    .then(response => response.json())
                    .then(reviews => {
                        console.log('Tutor Reviews:', reviews); // Log the reviews JSON to the console

                        // Display reviews in HTML
                        const reviewsContainer = document.getElementById('reviewsContainer');
                        if (reviews.length > 0) {
                            reviewsContainer.innerHTML = `
                <h3>Reviews:</h3>
                <ul>
                    ${reviews.map(review => `<li>${review.text} - Rating: ${review.rating || 'N/A'}</li>`).join('')}
                </ul>
            `;
                        } else {
                            reviewsContainer.innerHTML = 'No reviews available.';
                        }
                    })
                    .catch(err => console.error('Error fetching tutor reviews:', err));

            } else {
                alert('Tutor not found.');
            }
        })
        .catch(err => {
            console.error('Error fetching tutor data:', err);
        });
});

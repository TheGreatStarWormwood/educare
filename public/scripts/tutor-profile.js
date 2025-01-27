document.addEventListener('DOMContentLoaded', function () {
    const params = new URLSearchParams(window.location.search);
    const tutorId = params.get('id');
    const usertype = params.get('usertype');
    const userid = params.get('userid');

    // Initialize the page
    function init() {
        if (!tutorId) {
            alert('Tutor ID not provided.');
            return;
        }
        fetchTutorDetails(tutorId);
        fetchTutorListings(tutorId);
        fetchTutorReviews(tutorId);

        if (usertype === 'student') {
            renderReviewForm(tutorId);
        }
    }

    // Fetch tutor details
    function fetchTutorDetails(tutorId) {
        fetch(`/tutors/${tutorId}`)
            .then(response => response.json())
            .then(tutor => {
                if (tutor) {
                    const tutorDetailsContainer = document.getElementById('tutor-profile');
                    tutorDetailsContainer.innerHTML = `
                        <div class="tutor-header">
                            <div class="tutor-info">
                                <img src="/${tutor.image}" alt="${tutor.name}" class="tutor-image-profile">
                                <h2>${tutor.name}</h2>
                            </div>
                            <p class="tutor-description">${tutor.description}</p>
                        </div>
                        <div class="horizontal-sep"></div>
                        <div id="listingsContainer"></div>
                        <div id="reviewsContainer"></div>
                        <div id="submitReviewContainer"></div>
                    `;

                    fetchTutorListings(tutorId);

                    // Render review form for students after containers exist
                    if (usertype === 'student') {
                        renderReviewForm(tutorId);
                    
                    }
                    fetchTutorReviews(tutorId);

                    

                } else {
                    alert('Tutor not found.');
                }
            })
            .catch(err => console.error('Error fetching tutor details:', err));
    }


    // Fetch tutor listings
    function fetchTutorListings(tutorId) {
        fetch(`/tutors/${tutorId}/listings`)
            .then(response => response.json())
            .then(listings => {
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
    }

    // Fetch tutor reviews
    function fetchTutorReviews(tutorId) {
        fetch(`/tutors/${tutorId}/reviews`)
            .then(response => response.json())
            .then(reviews => {
                const reviewsContainer = document.getElementById('reviewsContainer');
                if (reviews.length > 0) {
                    reviewsContainer.innerHTML = `
                        <h3>Reviews:</h3>
                        <ul>
                            ${reviews.map(review => `<li>${review.text} - Rating: ${review.stars || 'N/A'}</li>`).join('')}
                        </ul>
                    `;
                } else {
                    reviewsContainer.innerHTML = 'No reviews available.';
                }
            })
            .catch(err => console.error('Error fetching tutor reviews:', err));
    }

    // Render review form
    function renderReviewForm(tutorId) {
        const submitReviewContainer = document.getElementById('submitReviewContainer');
        submitReviewContainer.innerHTML = `
            <h3>Leave a Review</h3>
            <form id="reviewForm">
                <textarea id="reviewText" placeholder="Write your review..." required></textarea>
                <input type="number" id="reviewRating" min="1" max="5" placeholder="Rating (1-5)" required />
                <button type="submit">Submit Review</button>
            </form>
        `;

        const reviewForm = document.getElementById('reviewForm');
        reviewForm.addEventListener('submit', function (e) {
            e.preventDefault();
            const reviewText = document.getElementById('reviewText').value;
            const reviewRating = parseInt(document.getElementById('reviewRating').value);

            submitReview(tutorId, reviewText, reviewRating, reviewForm);
        });
    }

    function submitReview(tutorId, reviewText, reviewRating) {
        fetch('/api/add-review', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                tutor_id: tutorId,
                text: reviewText,
                stars: reviewRating,
            }),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Error: ${response.status} - ${response.statusText}`);
                }
                return response.json();
            })
            .then(data => {
                if (data.success) {
                    console.log('Review submitted successfully:', data.message);

                    // Update the UI with the new review
                    const reviewsContainer = document.getElementById('reviewsContainer');
                    const reviewList = reviewsContainer.querySelector('ul') || document.createElement('ul');
                    reviewList.innerHTML += `<li>${reviewText} - Rating: ${reviewRating}</li>`;
                    reviewsContainer.appendChild(reviewList);

                    // Clear the form fields
                    const reviewForm = document.getElementById('reviewForm');
                    if (reviewForm) reviewForm.reset();
                } else {
                    console.error('Error adding review:', data.message);
                }
            })
            .catch(err => console.error('Error submitting review:', err));
    }


    // Run the initializer
    init();
});

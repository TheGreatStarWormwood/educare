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

        // Check if the user is a tutor and the userID matches the tutor's ID
        if (usertype === 'tutor' && userid === tutorId) {
            // Render the profile edit form and create listing form
            renderProfileEditForm(tutorId);
            renderCreateListingForm(tutorId);
        } else {
            // If the condition is not met, remove the edit profile container
            if (editProfileContainer) {
                editProfileContainer.remove();
            }
        }
    }

    // Fetch tutor details
    function fetchTutorDetails(tutorId) {
        fetch(`/tutors/${tutorId}`)
            .then(response => response.json())
            .then(tutor => {
                if (tutor) {
                    const tutorDetailsContainer = document.getElementById('tutorDetailsContainer');
                    tutorDetailsContainer.innerHTML = `
                        <div class="tutor-header">
                            <div class="tutor-info">
                                <img src="/${tutor.image}" alt="${tutor.name}" class="tutor-image-profile">
                                <h2>${tutor.name}</h2>
                            </div>
                            <br>
                            <p class="tutor-description">${tutor.description}</p>
                        </div>
                    `;
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

    // Render the profile edit form for the tutor
    function renderProfileEditForm(tutorId) {
        const editProfileContainer = document.getElementById('editProfileContainer');
        editProfileContainer.innerHTML = `
            <h3>Edit Profile</h3>
            <form id="editProfileForm">
                <input type="text" id="editName" placeholder="Name" required />
                <br>
                <textarea id="editDescription" placeholder="Description"></textarea>
                <br>
                <button type="submit">Update Profile</button>
            </form>
        `;

        const editProfileForm = document.getElementById('editProfileForm');
        editProfileForm.addEventListener('submit', function (e) {
            e.preventDefault();
            const newName = document.getElementById('editName').value;
            const newDescription = document.getElementById('editDescription').value;
            updateTutorProfile(tutorId, newName, newDescription);
        });
    }

    // Call API to update tutor profile
    function updateTutorProfile(tutorId, newName, newDescription) {
        fetch(`/api/update-tutor/${tutorId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                name: newName,
                description: newDescription,
            }),
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('Profile updated successfully.');
                    fetchTutorDetails(tutorId); // Refresh tutor details
                } else {
                    console.error('Error updating profile:', data.message);
                }
            })
            .catch(err => console.error('Error updating profile:', err));
    }

    // Render the create listing form for the tutor
    function renderCreateListingForm(tutorId) {
        const submitListingContainer = document.getElementById('submitListingContainer');
        submitListingContainer.innerHTML = `
            <h3>Create New Listing</h3>
            <form id="createListingForm">
                <input type="text" id="listingKeywords" placeholder="Listing Keywords" required />
                <button type="submit">Create Listing</button>
            </form>
        `;

        const createListingForm = document.getElementById('createListingForm');
        createListingForm.addEventListener('submit', function (e) {
            e.preventDefault();
            const keywords = document.getElementById('listingKeywords').value;
            createListing(tutorId, keywords);
        });
    }

    // Call API to create a new listing
    function createListing(tutorId, keywords) {
        fetch(`/api/create-listing/${tutorId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                keywords: keywords,
            }),
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('Listing created successfully.');
                    fetchTutorListings(tutorId); // Refresh listings
                } else {
                    console.error('Error creating listing:', data.message);
                }
            })
            .catch(err => console.error('Error creating listing:', err));
    }

    // Run the initializer
    init();
});

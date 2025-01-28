document.getElementById('create-user-form').addEventListener('submit', async function (e) {
    e.preventDefault();

    const name = document.getElementById('name').value;
    const description = document.getElementById('description').value;
    const image = document.getElementById('image').value;
    const userType = document.getElementById('user-type').value;

    const userData = {
        name,
        description: description || null,
        image: image || null
    };

    try {
        let apiUrl = '';
        if (userType === 'student') {
            apiUrl = '/api/create-student';
        } else if (userType === 'tutor') {
            apiUrl = '/api/create-tutor';
        }

    } catch (error) {
        console.error('Error:', error);
        document.getElementById('error-message').style.display = 'block';
        document.getElementById('success-message').style.display = 'none';
    }
});

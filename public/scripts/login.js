// public/scripts/login.js

document.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.getElementById("login-form");
    const errorMessage = document.getElementById("error-message");

    loginForm.addEventListener("submit", async function (event) {
        event.preventDefault();

        const name = document.getElementById("name").value.trim();
        const type = document.getElementById("type").value;

        try {
            const response = await fetch('/api/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ name, type }),
            });

            const responseData = await response.json();

            if (responseData.success) {
                const { userid, usertype } = responseData.data;
                window.location.href = `/?userid=${userid}&usertype=${usertype}`;
            } else {
                errorMessage.textContent = responseData.message || "Invalid user. Please try again.";
                errorMessage.style.display = "block";
            }
        } catch (error) {
            console.error('Error during login:', error);
            errorMessage.textContent = "An error occurred. Please try again later.";
            errorMessage.style.display = "block";
        }
    });
});

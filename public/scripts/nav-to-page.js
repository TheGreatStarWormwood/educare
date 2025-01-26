// nav-to-page.js
function navigateToPage(page, userType, id) {
    const url = `${page}?usertype=${encodeURIComponent(userType)}&id=${encodeURIComponent(id)}`;
    window.location.href = url;
}

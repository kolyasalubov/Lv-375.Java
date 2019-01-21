function openPage(pageURL) {
    window.location.href = pageURL;
}

function goBack() {
    window.history.go(-1);
    return false;
}

function openWithConfirm(pageURL) {
    if (confirm("Are you sure?")) {
        openPage(pageURL);
    }
}
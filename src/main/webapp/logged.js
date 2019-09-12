function onProfileLoad(user){
    clearMessages();
    const userEmailSpandEl = document.getElementById('user-username');
    userEmailSpandEl.textContent = user.email;
    showContents(['profile-content']);

}
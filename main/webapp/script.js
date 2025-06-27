const signupButton = document.getElementById('signupButton');
const loginButton = document.getElementById('loginButton');
const container = document.querySelector('.container');

signupButton.addEventListener('click', () => {
    container.classList.add('right-panel-active');
});

loginButton.addEventListener('click', () => {
    container.classList.remove('right-panel-active');
});
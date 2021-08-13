const password = document.querySelector('#password');
const email = document.querySelector('#email');
const login = document.querySelector('#login');

const auth = async (e) => {
    e.preventDefault();
    const user = {email: email.value, password: password.value}
    const request = await fetch('api/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    });

    const response = await request.text();
    if(response) {
        window.localStorage.setItem('token', response);
        window.location.href = 'tables.html'
    } else {
        alert("Credenciales incorrectas");
    }
}

login.addEventListener('click', auth);
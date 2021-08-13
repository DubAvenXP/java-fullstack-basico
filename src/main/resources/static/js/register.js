document.querySelector('#register')
    .addEventListener('click', async (event) => {
        event.preventDefault();
        const user = {
            nombre: document.querySelector('#exampleFirstName').value,
            apellido: document.querySelector('#exampleLastName').value,
            email: document.querySelector('#exampleInputEmail').value,
            telefono: document.querySelector('#exampleInputPhone').value,
            password: document.querySelector('#exampleInputPassword').value
        }

        const request = await fetch('api/usuarios', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });
        const response = await request.text();
        alert(response);
        location.reload();
    })


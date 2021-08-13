// Call the dataTables jQuery plugin
if (!window.localStorage.getItem('token')){
    window.location.href = 'login.html';
    console.log('no hay token')
} else {
    console.log('hay token')
}

const tbody = document.querySelector('#usuarios tbody');

$(document).ready(function () {
    cargarUsuarios();
    $('#usuarios').DataTable();
});

async function cargarUsuarios() {
    const request = await fetch('api/usuarios', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token
        }
    });

    const usuarios = await request.json();
    renderTable(usuarios);
    console.log(usuarios);
}

function renderTable(usuarios) {
    let content = '';

    usuarios.forEach(usuario => {
        content += `
            <tr class="odd">
                <td class="sorting_1">${usuario.id}</td>
                <td>${usuario.nombre + ' ' + usuario.apellido}</td>
                <td>${usuario.email}</td>
                <td>${usuario.telefono}</td>
                <td>
                    <a href="#" onclick="eliminarUsuario(${usuario.id})" class="btn btn-danger btn-circle" id="removeUser">
                        <i class="fas fa-trash"></i>
                    </a>
                </td>
            </tr>
        `
    });

    tbody.innerHTML = content;
}

async function eliminarUsuario(id) {
    if (!confirm('Desea eliminar un usuario?')) {
        return;
    }
    const request = await fetch(`api/usuarios/${id}`, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token
        }
    });

    const response = await request.text();
    alert(response);
    location.reload();
}
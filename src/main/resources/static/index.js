// user's info
function getUser() {
    fetch("api/user", {method: 'GET', dataType: 'JSON'})
        .then((res) => res.json())
        .then((user) => {
            let temp = "";
            temp += `<tr>
<td>${user.id}</td>
<td>${user.name}</td>
<td>${user.lastName}</td>
<td>${user.age}</td>
<td>${user.email}</td>
<td>${user.roles.map(e => e.name).join(', ')}</td>
</tr>`;
            document.getElementById("user-principal").innerHTML = temp;
        });
}

getUser();

// Users info
function getAllUser() {
    let temp = "";
    fetch("/api/users", {method: "GET", datatype: "JSON"})
        .then((res) => res.json())
        .then(users => {
            users.forEach(function (user) {
                temp += `
                <tr>
                <td>${user.id}</td>
<td>${user.name}</td>
<td>${user.lastName}</td>
<td>${user.age}</td>
<td>${user.email}</td>
<td>${user.roles.map(e => e.name).join(', ')}</td>
<td> <button type="submit" data-toggle="modal" onclick="modalUpdateUser(${user.id})" class="btn btn-info"
data-target="#editModal"> Edit </button></td>
<td><button type="submit" class="btn btn-danger" data-toggle="modal"  onclick="modalDeleteUser(${user.id})"
data-target="#deleteModal">Delete</button></td>
</tr>`;
            });
            document.getElementById("users").innerHTML = temp;
        });
}

getAllUser();

// Delete Modal Window
function modalDeleteUser(id) {
    fetch("/api/users/" + id, {method: "GET", dataType: "JSON"})
        .then(res => {
            res.json().then(user => {
                $("#deleteId").val(user.id)
                $("#deleteName").val(user.name)
                $("#deleteLastName").val(user.lastName)
                $("#deleteAge").val(user.age)
                $("#deleteEmail").val(user.email)
                $("#deleteRoles").val(user.roleSet.map(e => e.name + " "))
            })
        })
}

// Delete User
async function deleteUser() {
    await fetch("api/users/" + document.getElementById("deleteId").value,
        {method: "DELETE", dataType: "JSON"})
    $("#deleteUser".close).click();

    getAllUser();
}

// Update Modal Window
function modalUpdateUser(id) {
    fetch("/api/users/" + id, {method: "GET", datatype: "JSON"})
        .then(res => {
            res.json()
                .then(user => {
                    $("#updateId").val(user.id)
                    $("#updateName").val(user.name)
                    $("#updateLastname").val(user.lastName)
                    $("#updateAge").val(user.age)
                    $("#updateEmail").val(user.email)
                    $("#updatePassword").val(user.password)
                    $("#updateRoles").val(user.roleSet.map(e => e.name + " "))
                })
        })
}
// Update User
async function updateUser() {
    let user = {
        id: document.getElementById('updateId').value,
        name: document.getElementById('updateName').value,
        lastName: document.getElementById('updateLastname').value,
        age: document.getElementById('updateAge').value,
        email: document.getElementById('updateEmail').value,
        password: document.getElementById('updatePassword').value,
        roles: $('updateRoles').value,
    }

    await fetch("/api/users",
        {
            method: 'PUT',
            headers: {'Accept': 'application/json', 'Content-Type': 'application/json;charset=UTF-8'},
            body: JSON.stringify(user)
        })

    $("#updateUser".close).click();
    getAllUsers();
    getUser();

}
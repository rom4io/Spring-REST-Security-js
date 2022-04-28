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
<td> <button type="submit" data-toggle="modal" onclick="editModal(${user.id})" class="btn btn-info"
data-target="#editModal"> Edit </button></td>
<td><button type="submit" class="btn btn-danger" data-toggle="modal"  onclick="deleteModal(${user.id})"
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
                $("#deleteRoles").val(user.roles.map(e => e.name + " "))
            })
        })
}

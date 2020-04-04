<!DOCTYPE html>
<html>
    <head>
        <title>Edit User</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css">
        </head>
    <body>
    <div align='center'>
        <h2>Edit user</h2>
<form action="editUser" method="post">

        <table border=1>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Phone Number</th>
                <th>Phone Operator</th>
                <th>Balance</th>
            </tr>

                <tr>
                    <td>${user.id}</td>
                    <input type="hidden" id="fid" name="fid" value="${user.id}">
                    <td>
                    <input type="text" id="fname" name="fname" value="${user.name}">
                    </td>
                    <td>
                    <input type="text" id="fnumber" name="fnumber" value="${user.phoneNumber}">
                    </td>
                    <td>
                    <input type="text" id="fcompany" name="fcompany" value="${user.phoneOperator}">
                    </td>
                    <td>
                    <input type="text" id="fbalance" name="fbalance" value="${user.balance}">
                    </td>
                </tr>

        </table>
<input type="submit" value="Update">
</form>
    </div>



                        <div class="footer" align="center">
                            <p><a href="/">Home page</a>
                            <p><a href="users">List of Users</a>
                        </div>





    </body>
</html>

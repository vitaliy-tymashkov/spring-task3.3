<!DOCTYPE html>
<html>
    <head>
        <title>Users</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css">
        </head>
    <body>
    <div align='center'>
        <h2>List of users</h2>

        <table border=1>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Phone Number</th>
                <th>Phone Operator</th>
                <th>Balance</th>
                <th>Control</th>
            </tr>

            <#list users as user>
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.phoneNumber}</td>
                    <td>${user.phoneOperator}</td>
                    <td>${user.balance}</td>
                    <td>
                        <a href="changeOperator?userId=${user.id}&operator=${user.phoneOperator}">Change ${user.name}'s ${user.phoneOperator} operator</a>
                    </td>
                </tr>
            </#list>
        </table>
    </div>
                        <div class="footer" align="center">
                                <p><a href="/">Home Page</a>
                        </div>
    </body>
</html>

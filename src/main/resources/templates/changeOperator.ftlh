<!DOCTYPE html>
<html>
    <head>
        <title>Change Operator</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css">
        </head>
    <body>
    <div align='center'>
        <h2>Transfer money of ${userName}</h2>
        <h2>Current ${operator} account: ${balance}</h2>
        <h2>
        <#if operationApproved>
        <font color='green'>
        <#else>
        <font color='red'>
        </#if>
        ${transferFeeMessage}
        </font>
        </h2>

        <table border=1>
            <tr>
                <th>Phone Number</th>
                <th>Phone Operator</th>
                <th>Balance</th>
                <th>Control</th>
            </tr>

            <#list users as user>
                <tr>
                    <td>${user.phoneNumber}</td>
                    <td>${user.phoneOperator}</td>
                    <td>${user.balance}</td>
                    <td>
                            <#if operationApproved>
                             <a href="updateBalance?from=${userIdFrom}&to=${user.id}&operator=${operator}">Transfer to ${user.phoneOperator} account</a>
                            <#else>
                            <a href="updateBlocked?from=${userIdFrom}&to=${user.id}&operator=${operator}">Transfer is blocked - not enough money</a>
                            </#if>
                    </td>
                </tr>
            </#list>
        </table>
    </div>
                        <div class="footer" align="center">
                            <p><a href="users">List of Users</a>
                        </div>
    </body>
</html>

<!DOCTYPE html>
<html>
    <head>
        <title>Transfer Status</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css">
        </head>
    <body>
    <div align='center'>
        <h2>Transfer Status for ${userName}</h2>

        <h2>
        <#if operationIsDone>
        <font color='green'>
        <#else>
        <font color='red'>
        </#if>
        ${transferFeeMessage}. Details: from ${operator}, sum ${balance}.
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
                             <!--a href="updateBalance?userId=${user.id}&from=xxx&to=yyy&sum=${balance}">Transfer to ${user.phoneOperator} account</a-->
                             <a href="updateBalance?from=${userIdFrom}&to=${user.id}&operator=${operator}">Transfer to ${user.phoneOperator} account</a>
                            <#else>
                            <!--a href="updateBlocked?userId=${user.id}&from=xxx&to=yyy&sum=${balance}">Transfer is blocked - not enough money</a-->
                            <b>Transfer is blocked - not enough money</b>
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

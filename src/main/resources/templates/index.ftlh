<!DOCTYPE html>
<html>
    <head>
        <title>Spring Advanced #57 - Task 4</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <div align='center'>
        <h2>Task 4 - Home Page</h2>
        <a href="users">Show users</a>

        <div>
             <table border='1'>
             <tr><td>
        <h2> REST </h2>
        <p><a href='/api/user/1'>Show 1st user</a>
        <p><a href='/api/user/all/ascending'>Show all users in ascending order</a>
        <p><a href='/api/user/all/ascending/2'>Show all users in ascending order - limit 2</a>
        <p><a href='/api/user/all/ascending/2/3'>Show all users in ascending order - limit 2 offset 3</a>
        <p><a href='/api/user/all/descending'>Show all users in descending order</a>
             </td></tr>
             </table>
        </div>

    <div align='center'>
         <table border='1'>
         <tr><td>
    <h2> REST - ADD USER </h2>
    <p> <a href="addUser.html"> Add new user - using POSTMAN POST REQUEST</a>
    <p>
    <p>Or use CURL:
    <p>curl -d "{"""name""":"""Ivan""","""phoneNumber""":"""233234234""","""phoneOperator""":"""Orange""","""balance""":11110}" -H "Content-Type: application/json" -X POST http://localhost:8085/api/user/addUser
         </td></tr>
         </table>
    </div>

    <div align='center'>
     <table border='1'>
    <tr><td>
    <h2> PDF resolver </h2>
        <p> <a href="/api/pdf/user_pdf"> Show list of user names</a>

    <p>

    <p>Or use CURL:
    <p>curl -H "Accept: application/pdf" -X GET http://localhost:8085/api/pdf/user_pdf
     </td></tr>
     </table>
    </div>


    <div align='center'>
     <table border='1'>
         <tr><td>
         <h2> ContentNegotiationViewResolver via MVC namespace </h2>
    <p> <a href="api/userCNVR/name/Leo"> Get XML for Leo</a>
    <p> <a href="api/userCNVR/id/1"> Get XML for id=1</a>
    <p> <a href="api/pdf/all"> Get PDF for all users</a>
    </td></tr>
     </table>
         </div>

        </div>
    </body>

</html>

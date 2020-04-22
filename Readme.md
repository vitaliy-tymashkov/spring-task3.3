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
Attack vector: http://localhost:8084/editUser?userId=1
Field: any text field - i.e. Phone Operator
';  INSERT INTO usersPasswords(name, password) VALUES('Leo333', '333Password'); --


Resulting SQL _____________________________________________________________________**********************************************************************************_____________________________
UPDATE users SET  name = 'Ivan', phoneNumber = '233234234', phoneOperator = 'Orange';  INSERT INTO usersPasswords(name, password) VALUES('Leo333', '333Password'); --', balance = 43200 WHERE id=1;

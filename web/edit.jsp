<%-- 
    Document   : edit
    Created on : 10 июл. 2022 г., 21:36:11
    Author     : Fascan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Edit product</h3>
        <form method="post">
            <input type="hidden" value="${contact.id}" name="id" />
            <label>Name</label><br>
            <input name="name" value="${contact.name}" /><br><br>
            <label>Price</label><br>
            <input name="price" value="${contact.price}" type="number" min="100" /><br><br>
            <input type="submit" value="Send" />
        </form>
    </body>
</html>

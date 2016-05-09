<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" >
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <spring:url value="/resources/main.css" var="mainCSS" />
    <link href="${mainCSS}" rel="stylesheet" />
    <title>Read Database</title>
</head>
<body>
<div align="center">
    <div class=”container”>
    <h1>Read Database</h1>
        <form role="form" id="db_form" action="savedbdata" method="POST">
            <div class=”form-group”>
                <label class="newline" for="db_url">DB URL</label>
                <input name="db_url" id="db_url" type="text" required autofocus placeholder="jdbc:postgresql://localhost:5432/postgres" />
            </div>
            <div class=”form-group”>
                <label class="newline" for="username">Username</label>
                <input name="username" id="username" type="text"  placeholder="postgres"/>
            </div>
            <div class=”form-group”>
                <label class="newline" for="password">Password</label>
                <input name="password" id="password" type="password"  placeholder="12345"/>
            </div>
            <div class=”form-group”>
                <label class="newline" for="schema">Schema</label>
                <input name="schema" id="schema" type="text" placeholder="Your Schema" required/>
            </div>
            <button type="submit" class=”btn btn-default”>Submit</button>
        </form>
    </div>
</div>
</body>
</html>
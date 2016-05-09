<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Show ColumnsList</title>
</head>
<body>

<div align="center">
    <h1>R2RML Form</h1>

    <form id="r2rml_form" action="saveR2RMLFields" method="POST">
        <h2>Prefix</h2>
        <div class="input-group" style="width:350px">
            <input type="text" class="form-control" aria-label="..." id="text">
            <div class="input-group-btn">
                <!-- Buttons -->
                <button class="btn btn-default" id="button" type="button">Add!</button>
            </div>
        </div>

        </br>

        <textarea id="prefixTextarea" class="form-control" name="prefixTextarea" style="width:350px"></textarea>

        </br>
        <h2>Properties</h2>
        <c:forEach var="element" items="${co}" varStatus="status">
            <div class=”form-group”>
                <label class="newline" for="property${status.index}">${element}</label>
                <input name="property${status.index}" id="property${status.index}" type="text" required autofocus placeholder="property" />

                <label class="radio-inline"><input type="radio" value="rr:Literal" name="radio${status.index}" checked="checked">Literal</label>
                <label class="radio-inline"><input type="radio" value="xsd:integer" name="radio${status.index}">Integer</label>
                <label class="radio-inline"><input type="radio" value="rr:constant <http://example.com/vocabulary/example>" name="radio${status.index}">Constant</label>
            </div>
        </c:forEach>
        </br>
        <button type="submit" class=”btn btn-default”>Submit</button>
    </form>

    <script type="text/javascript">
        document.getElementById("button").addEventListener('click', function () {
            var text = document.getElementById('text');
            var prefix = document.getElementById('prefixTextarea');
            prefix.value += "@prefix "+text.value+".\n";
        });
    </script>
</div>
</body>
</html>
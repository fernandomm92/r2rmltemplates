<%--
  Created by IntelliJ IDEA.
  User: ferna
  Date: 11/04/2016
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gasolineras Manager Home</title>
</head>
<body>
<div align="center">
    <h1>Gasolineras List</h1>
    <table border="1">
        <th>No</th>
        <th>Label</th>
        <th>Via</th>
        <th>EnlaceSIG</th>
        <th>Latitud</th>
        <th>Longitud</th>

        <c:forEach var="gasolinera" items="${listGasolineras}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${gasolinera.label}</td>
                <td>${gasolinera.via}</td>
                <td>${gasolinera.enlacesig}</td>
                <td>${gasolinera.latitud}</td>
                <td>${gasolinera.longitud}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

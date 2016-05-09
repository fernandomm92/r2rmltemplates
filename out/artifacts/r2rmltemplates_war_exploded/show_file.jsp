<%@page import="com.fernando.util.DisplayLog"%>
<%--
  Created by IntelliJ IDEA.
  User: ferna
  Date: 24/04/2016
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    DisplayLog log = new DisplayLog();
    //log.display("mapping.ttl");
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Mapping File</h2>
<textarea class="form-control" rows="32" cols="140" readonly="readonly" WRAP="off" id="mappingFile"><%=log.display("mapping.ttl")%></textarea>
<h2>Properties File</h2>
<textarea class="form-control" rows="16" cols="140" readonly="readonly" WRAP="off" id="propertiesFile"><%=log.display("properties.r2rml.properties")%></textarea>
</body>
</html>

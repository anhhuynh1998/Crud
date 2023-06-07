<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 6/6/2023
  Time: 2:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${requestScope=['message'].size() != null}">
  <span>${message}</span>
</c:if>
<a href="/customers">Black</a>
</body>
</html>

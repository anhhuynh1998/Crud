<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 6/2/2023
  Time: 3:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>CreateStudent</title>
</head>
<body>
<c:if test="${requestScope['message'] != null}">
    <span>${message}</span>
</c:if>
<a href="/students">Back</a>
<form action="/students?action=create" method="post">
    <label for="name">Name</label>
    <input type="text" name="name" id="name" value="${student.name}" />
    <label for="dob">DOb</label>
    <input type="date" name="dob" id="dob" value="${student.dob}" />
    <label for="gender">Gender</label>
    <input type="text" name="gender" id="gender" value="${student.gender}" />
    <label for="class">Class</label>

    <select name="class" id="class">
        <option value="">--None--</option>
        <c:forEach items="${classmodel}" var="classT">
            <option value="${classT.id}">${classT.name}</option>
        </c:forEach>
    </select>

    <button type="submit">Create</button>
</form>
</body>
</html>
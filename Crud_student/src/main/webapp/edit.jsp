<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 6/2/2023
  Time: 4:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${requestScope['message'] != null}">
    <span>${message}</span>
</c:if>

<form action="/students?action=edit" method="post">
    <input type="hidden" name="id" value="${student.id}">
    <label for="name">Name</label>
    <input type="text" name="name" id="name" value="${student.name}" />
    <label for="dob">Dob</label>
    <input type="date" name="dob" id="dob" value="${student.dob}" />
    <label for="gender">Gender</label>
    <input type="text" name="gender" id="gender" value="${student.gender}" />
    ${student.className.id}
    <select name="class" id="classes">
        <option value="">--None--</option>
        <c:forEach items="${classmodel}" var="classes">
            <c:if test="${student.className.id == classes.id}">
                <option selected value="${classes.id}">${classes.name}</option>
            </c:if>
            <c:if test="${student.className.id != classes.id}">
                <option value="${classes.id}">${classes.name}</option>
            </c:if>
        </c:forEach>
    </select>
    <button type="submit">Edit</button>
    <a href="/students">Back</a><br>
</form>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 6/2/2023
  Time: 2:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${action}</h1>
<a href="/students?action=create">Create Customer</a>
<c:if test="${requestScope['students'].size() != 0}">
    <table border="1">
        <tr>
            <td>Id</td>
            <td>Name</td>
            <td>Dob</td>
            <td>Gender</td>
            <td>Class</td>
            <td>Action</td>
        </tr>
        <c:forEach items="${students}" var="student">
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.dob}</td>
                <td>${student.gender}</td>
                <td>${student.className.name}</td>
                <td><a href="/students?action=edit&id=${student.id}">Edit</a> </td>
                <td><a href="/students?action=delete&id=${student.id}" onclick="return confirm('Do you want to remove ${student.name}?')">Delete</a> </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
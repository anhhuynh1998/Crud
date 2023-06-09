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
    <title>CreateBook</title>
</head>
<body>
<c:if test="${requestScope['message'] != null}">
    <span>${message}</span>
</c:if>
<a href="books">Back</a>
<form action="books?action=create" method="post">
    <label for="name">Name</label>
    <input type="text" name="name" id="name" value="${book.name}" />
    <label for="date">Date</label>
    <input type="date" name="date" id="date" value="${book.date}" />
    <label for="author">Author</label>
    <input type="text" name="author" id="author" value="${book.author}" />
    <label for="category">Role</label>

    <select name="category" id="category" value="${book.category.id}">
        <option value="">--None--</option>
        <c:forEach items="${category}" var="category">
            <option value="${category.id}">${category.name}</option>
        </c:forEach>
    </select>

    <button type="submit">Create</button>
</form>
</body>
</html>
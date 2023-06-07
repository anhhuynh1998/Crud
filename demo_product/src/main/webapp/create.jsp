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
    <title>CreateProduct</title>
</head>
<body>
<c:if test="${requestScope['message'] != null}">
    <span>${message}</span>
</c:if>
<a href="/products">Back</a>
<form action="/products?action=create" method="post">
    <label for="name">Name</label>
    <input type="text" name="name" id="name" value="${product.name}" />
    <label for="price">Price</label>
    <input type="text" name="price" id="price" value="${product.price}" />
    <label for="quantity">Quantity</label>
    <input type="text" name="quantity" id="quantity" value="${product.quantity}" />
    <label for="category">Role</label>

    <select name="category" id="category" value="${product.category.id}">
        <option value="">--None--</option>
        <c:forEach items="${category}" var="category">
            <option value="${category.id}">${category.name}</option>
        </c:forEach>
    </select>

    <button type="submit">Create</button>
</form>
</body>
</html>
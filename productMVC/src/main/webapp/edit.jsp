<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dtv
  Date: 05/06/2023
  Time: 3:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${requestScope['message'] != null}">
    <h1>${message}</h1>
</c:if>
<form action="/products?action=edit" method="post">
    <input type="hidden" name="id" value="${product.id}" />
    <label for="name">Name</label>
    <input type = "text" name="name" id="name" value="${product.name}"/>
    <label for="price">Price</label>
    <input type = "number" step="0.1" name="price" id="price" value="${product.price}"/>
    <label for="quantity">Quantity</label>
    <input type = "number" name="quantity" id="quantity" value="${product.quantity}"/>
    <label for="category">Category</label>
    <select name="category" id="category" value="${product.category}">--%>
        <c:forEach items="${categories}" var="category">
            <c:if test="${category.id== product.category.id}">
                <option value="${category.id}">${category.name}</option>
            </c:if>
            <c:if test="${category.id!= product.category.id}">
                <option value="${category.id}">${category.name}</option>
            </c:if>
        </c:forEach>
    </select>
    <input type="submit" value="EDIT"/>
</form>
<a href="/products">Back</a>
</body>
</html>
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
<a href="/products?action=create">Create Product</a>
<c:if test="${requestScope['products'].size() != 0}">
  <table border="1">
    <tr>
      <td>Id</td>
      <td>Name</td>
      <td>Price</td>
      <td>Quantity</td>
      <td>Category</td>
      <td>Action</td>
    </tr>
    <c:forEach items="${products}" var="product">
      <tr>
        <td>${product.id}</td>
        <td>${product.name}</td>
        <td>${product.price}</td>
        <td>${product.quantity}</td>
        <td>${product.category}</td>
        <td><a href="/products?action=edit&id=${product.id}">Edit</a> </td>
        <td><a href="/products?action=delete&id=${product.id}" onclick="return confirm('Do you want to remove ${product.name}?')">Delete</a> </td>
      </tr>
    </c:forEach>
  </table>
</c:if>

</body>
</html>
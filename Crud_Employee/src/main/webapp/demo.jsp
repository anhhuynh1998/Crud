
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

      <html>
      <head>
      <title>Title</title>
</head>
<body>
<h1>${action}</h1>
<a href="employees?action=create">Create Employee</a>
<form action="employees" method="get">
  <input type="search" name="search" id="search" value="${pageable.search}" onsearch="onClearSearch()" />
  <button id="searchButton">Search</button>
</form>
<form action="employees" method="get">
  <input type="date" name="searchDate" id="searchD" value="${pageable.search}" onsearch="onClearSearch()" />
  <button id="searchDate">Search Date</button>
</form>
<c:if test="${requestScope['employee'].size() != 0}">
  <table border="1">
    <tr>
    <tr>
      <td>
        <c:if test="${pageable.sortBy== 'desc'}">
          <a href="employees?page=${pageable.page}&search=${pageable.search}&sortby=asc&nameField=employee.id">
            Id
          </a>
        </c:if>
        <c:if test="${pageable.sortBy== 'asc'}">
          <a href="employees?page=${pageable.page}&search=${pageable.search}&sortby=desc&nameField=employee.id">
            Id
          </a>
        </c:if>
      </td>
      <td> <c:if test="${pageable.sortBy== 'desc'}">
        <a href="employees?page=${pageable.page}&search=${pageable.search}&sortby=asc&nameField=employee.name">
          Name
        </a>
      </c:if>
        <c:if test="${pageable.sortBy== 'asc'}">
          <a href="employees?page=${pageable.page}&search=${pageable.search}&sortby=desc&nameField=employee.name">
            Name
          </a>
        </c:if></td>
      <td>
        <c:if test="${pageable.sortBy== 'desc'}">
        <a href="employees?page=${pageable.page}&search=${pageable.search}&sortby=asc&nameField=employee.dob">
          DoB
        </a>
      </c:if>
        <c:if test="${pageable.sortBy== 'asc'}">
          <a href="employees?page=${pageable.page}&search=${pageable.search}&sortby=desc&nameField=employee.dob">
            DoB
          </a>
        </c:if></td>
      <td>
        <c:if test="${pageable.sortBy== 'desc'}">
        <a href="employees?page=${pageable.page}&search=${pageable.search}&sortby=asc&nameField=employee.gender">
          Gender
        </a>
      </c:if>
        <c:if test="${pageable.sortBy== 'asc'}">
          <a href="employees?page=${pageable.page}&search=${pageable.search}&sortby=desc&nameField=employee.gender">
            Gender
          </a>
        </c:if>
      </td>
      <td>
        <c:if test="${pageable.sortBy== 'desc'}">
          <a href="employees?page=${pageable.page}&search=${pageable.search}&sortby=asc&nameField=employee.salary">
            Salary
          </a>
        </c:if>
        <c:if test="${pageable.sortBy== 'asc'}">
          <a href="employees?page=${pageable.page}&search=${pageable.search}&sortby=desc&nameField=employee.salary">
            Salary
          </a>
        </c:if>
      </td>
    <td>
      <c:if test="${pageable.sortBy== 'desc'}">
        <a href="employees?page=${pageable.page}&search=${pageable.search}&sortby=asc&nameField=employee.department_id">
          Department
        </a>
      </c:if>
      <c:if test="${pageable.sortBy== 'asc'}">
        <a href="employees?page=${pageable.page}&search=${pageable.search}&sortby=desc&nameField=employee.department_id">
          Department
        </a>
      </c:if>
    </td>
      <td>Action</td>
    </tr>
    <c:forEach items="${employee}" var="employee">
      <tr>
        <td>${employee.id}</td>
        <td>${employee.name}</td>
        <td>${employee.dob}</td>
        <td>${employee.gender}</td>
        <td>${employee.salary}</td>
        <td>${employee.department.name}</td>
        <td><a href="employees?action=edit&id=${employee.id}">Edit</a> </td>
        <td><a href="employees?action=delete&id=${employee.id}" onclick="return confirm('Do you want to remove ${employee.name}?')">Delete</a> </td>
      </tr>
    </c:forEach>
  </table>
  <ul>
    <c:forEach begin="1" end="${pageable.totalPage}" var="page">
      <li><a href="employees?page=${page}&search=${pageable.search}&sortby=${pageable.sortBy}&nameField=${pageable.nameField}">${page}</a> </li>
    </c:forEach>

  </ul>
</c:if>
<script>
  function onClearSearch(){
    searchButton.click();
  }
</script>
</body>
</html>




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
<a href="employees">Back</a>
<form action="employees?action=create" method="post">
    <label for="name">Name</label>
    <input type="text" name="name" id="name" value="${employee.name}"/>
    <label for="dob">Dob</label>
    <input type="date" name="dob" id="dob" value="${employee.dob}"/>
    <label for="gender">Gender</label>
    <select name="gender" id="gender">
        <c:forEach items="${genders}" var="gender">
            <option value="${gender}"
                    <c:if test="${gender == employee.gender}">
                        selected
                    </c:if>
            >${gender}
            </option>
        </c:forEach>

    </select>
    <label for="salary">Salary</label>
    <input type="number" name="salary" id="salary" value="${employee.salary}"/>
    <label for="department">Department</label>

    <select name="department_id" id="department">
        <option value="">--None--</option>
        <c:forEach items="${department}" var="classT">
            <option value="${classT.id}">${classT.name}</option>
        </c:forEach>
    </select>

    <button type="submit">Create</button>
</form>
</body>
</html>
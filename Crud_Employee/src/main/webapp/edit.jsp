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

<form action="employees?action=edit" method="post">
    <input type="hidden" name="id" value="${employee.id}">
    <label for="name">Name</label>
    <input type="text" name="name" id="name" value="${employee.name}"/>
    <label for="dob">Dob</label>
    <input type="date" name="dob" id="dob" value="${employee.dob}"/>
    <label for="gender">Gender</label>
    <select name="gender" id="gender">
        <%--        <option--%>
        <%--                <c:if test="${employee.gender == 'MALE'}">--%>
        <%--                    selected--%>
        <%--                </c:if>--%>
        <%--                value="MALE">MALE</option>--%>
        <%--        <option--%>
        <%--                <c:if test="${employee.gender == 'FEMALE'}">--%>
        <%--                    selected--%>
        <%--                </c:if>--%>
        <%--                value="FEMALE">FEMALE</option>--%>

        <%--        <option--%>
        <%--                <c:if test="${employee.gender == 'OTHER'}">--%>
        <%--                    selected--%>
        <%--                </c:if>--%>
        <%--                value="OTHER">OTHER</option>--%>
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
    ${employee.department.id}
    <select name="department" id="department">
        <option value="">--None--</option>
        <c:forEach items="${department}" var="departments">
            <c:if test="${employee.department.id == departments.id}">
                <option selected value="${departments.id}">${departments.name}</option>
            </c:if>
            <c:if test="${employee.department.id != departments.id}">
                <option value="${departments.id}">${departments.name}</option>
            </c:if>
        </c:forEach>
    </select>
    <button type="submit">Edit</button>
    <a href="employee">Back</a><br>
</form>
</body>
</html>

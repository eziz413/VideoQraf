<%@ page import="java.util.List" %>
<%@ page import="az.apeiron.model.Student" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 06.12.18
  Time: 2:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%--<% List<Student> students = (List<Student>) request.getAttribute("students");--%>

<script type="text/javascript">
    $(function () {


        $('#studentTable').DataTable();
    });
</script>

<table id="studentTable" class="display" style="...">
    <thead>
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>SURNAME</th>
        <th>DATE OF BIRTH</th>
        <th> SECTOR </th>
        <th> EDIT </th>
    </tr>
    </thead>
    <tbody>
        <c:forEach var="student" items="${students}">
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.surname}</td>
                <td>${student.dob}</td>
                <td>${student.sectorName}</td>
                <td><a href="javascript: getStudent(${student.id})">Edit</a></td>

            </tr>
        </c:forEach>



    </tbody>
    <tfoot>
    <%--<tr>--%>
        <%--<th>ID</th>--%>
        <%--<th>NAME</th>--%>
        <%--<th>SURNAME</th>--%>
        <%--<th>DATE OF BIRTH</th>--%>
        <%--<th> SECTOR </th>--%>
        <%--<th> EDIT </th>--%>
    <%--</tr>--%>
    </tfoot>
</table>

<%@ page import="az.apeiron.model.Student" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 09.12.18
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% List<Student> students = (List<Student>) request.getAttribute("students"); %>
<option value="0">Secin</option>
<% for (Student student : students) {%>
<option value="<%=student.getId()%>"><%=student.getName()%></option>
<%}%>

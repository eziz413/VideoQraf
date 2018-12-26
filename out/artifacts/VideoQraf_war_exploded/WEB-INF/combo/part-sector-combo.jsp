<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="az.apeiron.model.Sector" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 08.12.18
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<option value="0">Secin</option>
<c:forEach var="sector" items="${sectors}">
    <option value="${sector.id}">
        ${sector.name}
    </option>
</c:forEach>


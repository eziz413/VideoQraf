<%@ page import="az.apeiron.model.Student" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 09.12.18
  Time: 0:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% Student student= (Student) request.getAttribute("student");
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
%>

<script>
    $(function () {
        $( "#dob" ).datepicker();
        $( "#dob" ).datepicker( "option", "showAnim", 'slide' );
        getSelectedSectorComboList(<% student.getSectorId();%>);
    });
</script>


<div class="inputlabel"> Ad:</div>
<input id="name" type="text" name="name" value="<%=student.getName()%>"/> <br>
<div class="inputlabel">Soyad:</div>
<input id="surname" type="text" name="surname" value="<%=student.getSurname()%>"/> <br>
<div class="inputlabel">Date of Birth:</div>
<input id="dob" type="text" name="dob" value="<%= dateFormat.format(student.getDob()) %>"/> <br>
<div class="inputlabel">Sector:</div>
<select style="width: 200px" id="sector2"></select>


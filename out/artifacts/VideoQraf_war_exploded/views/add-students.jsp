<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 08.12.18
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    $(function () {
        $( "#dob" ).datepicker();
        $( "#dob" ).datepicker( "option", "showAnim", 'slide' );
        $( "#student-tabs" ).tabs();
    });
</script>

<div id="student-tabs" style="width: 363px">
    <ul>
        <li><a href="#tabs-1">Shexsi melumatlar</a></li>
        <li><a href="#tabs-2">Istifadeci melumatlar</a></li>
    </ul>
    <div id="tabs-1">

        <div class="inputlabel"> Ad:</div>
        <input id="name" type="text" name="name"/> <br>
        <div class="inputlabel">Soyad:</div>
        <input id="surname" type="text" name="surname"/> <br>
        <div class="inputlabel">Date of Birth:</div>
        <input id="dob" type="text" name="dob"/> <br>
        <div class="inputlabel">Sector:</div>
        <select style="width: 200px" id="sector1"></select>


    </div>
    <div id="tabs-2">

        <div class="inputlabel"> Email:</div>
        <input id="email" type="text" name="email"/> <br>
        <div class="inputlabel">Istifadeci adi:</div>
        <input id="username" type="text" name="username"/> <br>

    </div>

</div>
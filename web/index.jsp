<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 04.12.18
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyWeb</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="resources/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="resources/css/jquery-ui.css">


    <script type="text/javascript" src="resources/jsp/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="resources/jsp/custom.js"></script>
    <script type="text/javascript" src="resources/jsp/combo.js"></script>
    <script type="text/javascript" src="resources/jsp/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="resources/jsp/jquery-ui.js"></script>

    <script type="text/javascript">

        $(function () {

            getStudentList();
            getSectorComboList();
            getStudentComboListBySector(0);
            $('#teacherTable').DataTable();

            $( "#advancedSearchAccordion" ).accordion({
                collapsible: true,
                active: false,
            });


            $( "#addStudentDialog" ).dialog({
                autoOpen : false,
                resizable: false,
                height: "auto",
                width: 400,
                modal: true,
                buttons: {
                    "Save": function() {
                        addStudent();
                        getStudentList();
                        $( this ).dialog( "close" );
                    },
                    "Cancel": function() {
                        $( this ).dialog( "close" );
                    }
                }
            });

            $( "#editStudentDialog" ).dialog({
                autoOpen : false,
                resizable: false,
                height: "auto",
                width: 400,
                modal: true,
                buttons: {
                    "Save": function() {
                        updateStudent();
                        getStudentList();
                        $( this ).dialog( "close" );
                    },
                    "Cancel": function() {
                        $( this ).dialog( "close" );
                    }
                }
            });

            var globalButtonId;

            $('.menubutton').click(function () {
                globalButtonId=$(this).attr("id");
            });

            $('#addBtn').click(function () {
                switch (globalButtonId) {
                    case "menuStudentsBtn" :
                        $('#addStudentDialog').load('views/add-students.jsp',function () {
                            getSectorComboList();
                            $( "#addStudentDialog" ).dialog('open');

                        });
                        break;
                    case "menuTeachersBtn" :
                        alert(globalButtonId);
                        break;
                }

            });


            $('#simpleSearchInput').keyup(function () {
                switch (globalButtonId) {
                    case "menuStudentsBtn" :
                        getStudentBySimpleSearch();
                        break;
                    case "menuTeachersBtn" :
                        alert(globalButtonId);
                        break;
                }
            });


            $('#menuStudentsBtn').click(function () {
                $('#studentTablePanel').show();
                $('#addStudentPanel').hide();
                $('#teacherTablePanel').hide();
            });
            $('#menuTeachersBtn').click(function () {
                $('#studentTablePanel').hide();
                $('#addStudentPanel').hide();
                $('#teacherTablePanel').show();
            });
            $('#menuAddStudentBtn').click(function () {
                $('#studentTablePanel').hide();
                $('#addStudentPanel').show();
                $('#teacherTablePanel').hide();
            });

        });

    </script>
</head>
<body>
<div id="container" class="container">
    <div id="header" class="header">
        <h1 style="margin-bottom:0;">Proyektin adi</h1>
    </div>
    <div id="menu" class="menu">

        <input class="menubutton" type="button" value="Students" id="menuStudentsBtn" /> <br/>
        <input class="menubutton" type="button" value="Teachers" id="menuTeachersBtn" /> <br/>

    </div>
    <div id="content" class="content" >

        <div id="content-menu" class="content-menu">
            <input id="addBtn" type="button" value="New"/>
            <input id="simpleSearchInput" type="text" placeholder="Search..." style="float: right;height: 26px"/>
        </div>


        <div id="advancedSearchAccordion">
            <h3>Advanced Search</h3>
            <div>
                <select id="sector" style="width: 200px"></select>&nbsp;
                <select id="studentCombo" style="width: 200px"></select>
            </div>

        </div>

        <div id="studentTablePanel" >

        </div>

        <div id="teacherTablePanel" style="display: none">
            <table id="teacherTable" class="display" style="width:100%">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Position</th>
                    <th>Office</th>
                    <th>Age</th>
                    <th>Start date</th>
                    <th>Salary</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>Tiger Nixon</td>
                    <td>System Architect</td>
                    <td>Edinburgh</td>
                    <td>61</td>
                    <td>2011/04/25</td>
                    <td>$320,800</td>
                </tr>

                <tr>
                    <td>Michael Bruce</td>
                    <td>Javascript Developer</td>
                    <td>Singapore</td>
                    <td>29</td>
                    <td>2011/06/27</td>
                    <td>$183,000</td>
                </tr>
                <tr>
                    <td>Donna Snider</td>
                    <td>Customer Support</td>
                    <td>New York</td>
                    <td>27</td>
                    <td>2011/01/25</td>
                    <td>$112,000</td>
                </tr>
                </tbody>
                <tfoot>
                <tr>
                    <th>Name</th>
                    <th>Position</th>
                    <th>Office</th>
                    <th>Age</th>
                    <th>Start date</th>
                    <th>Salary</th>
                </tr>
                </tfoot>
            </table>
        </div>

        <div id="addStudentPanel" style="display: none">


        </div>

        <div id="addStudentDialog" title="Add Student">

        </div>

        <div id="editStudentDialog" title="Edit Student">

        </div>

    </div>
    <div id="footer" class="footer">
        Əziz Heydərov eziz.heydrov@mail.ru
    </div>
</div>
</body>
</html>

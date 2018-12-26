function addStudent() {
    var name = $('#name').val();
    var surname = $('#surname').val();
    var dob = $('#dob').val();
    var sectorId = $('#sector').val();
    var email = $('#email').val();
    var username = $('#username').val();

    $.ajax({
        url: 'controller?action=studentRegister',
        type: 'post',
        dataType: 'text',
        data: 'name=' + name + '&surname=' + surname + '&dob=' + dob + '&sectorId=' + sectorId +'&email='+email + '&username='+ username,
        success: function (data) {
            alert('Ugurla elave olundu');
        },
        error: function (data) {
            alert('Xeta bas verdi');
        },
        complete: function (data) {
            alert('Complete');
            getStudentList();
        }

    });


}

function getStudentList() {
    $.ajax({
        url: 'controller?action=getStudentList',
        type: 'get',
        dataType: 'html',
        success: function (data) {
            $('#studentTablePanel').html(data);

        }
    });
}

var globalStudentId;

function getStudent(studentId) {
    globalStudentId = studentId;
    $.ajax({
        url: 'controller?action=getStudent',
        type: 'get',
        data: 'studentId=' + studentId,
        dataType: 'html',
        success: function (data) {
            $('#editStudentDialog').html(data);
            $('#editStudentDialog').dialog('open');
        }

    });
}

function updateStudent() {
    var name = $('#name').val();
    var surname = $('#surname').val();
    var dob = $('#dob').val();
    var sectorId = $('#sector2').val();

    $.ajax({
        url: 'controller?action=updateStudent',
        type: 'get',
        dataType: 'text',
        data: 'name=' + name + '&surname=' + surname + '&dob=' + dob + '&sectorId=' + sectorId + 'studentId=' + globalStudentId,
        success: function (data) {
            alert('Ugurla elave olundu');
        },
        error: function (data) {
            alert('Xeta bas verdi');
        },
        complete: function (data) {
            alert('Complete');
        }

    });
}

function getStudentBySimpleSearch() {
    var searchValue = $('#simpleSearchInput').val();
    $.ajax({
        url: 'controller?action=getStudentBySimpleSearch',
        type: 'get',
        dataType: 'html',
        data: 'searchValue=' + searchValue,
        success: function (data) {

            $('#studentTablePanel').html(data);

        }
    })
}
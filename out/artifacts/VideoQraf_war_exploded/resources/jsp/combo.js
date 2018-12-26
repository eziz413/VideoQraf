function getSectorComboList() {
    $.ajax({
        url :'controller?action=getSectorComboList',
        type :'get',
        dataType : 'html',
        success : function (data) {
            $('#sector').html(data);
            $('#sector1').html(data);
        }
    });
}

function getSelectedSectorComboList(sectorId) {
    $.ajax({
        url :'controller?action=getSectorComboList',
        type :'get',
        dataType : 'html',
        success : function (data) {
            $('#sector2').html(data);
            $('#sector').val(sectorId);
        }
    });
}

function getStudentComboListBySector(sectorId) {

    $.ajax({
        url :'controller?action=getStudentComboListBySector',
        type :'get',
        dataType : 'html',
        data : 'sectorId='+sectorId,
        success : function (data) {
            $('#studentCombo').html(data);
        }
    });

}
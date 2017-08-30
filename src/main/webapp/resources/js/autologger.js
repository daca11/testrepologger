function reformatTable() {
    $("td span[class='danger']").parent().parent().addClass('danger');
}
$(function () {
    reformatTable();
});
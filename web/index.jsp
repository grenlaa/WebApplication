
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <div class="container" >
            <div style="width:40%; float: left">
                <h2>Контакты</h2>
                <div style="margin-bottom: 5px; ">
                    <button id="create">Create</button>
                </div>
                <div style="margin-bottom: 5px; ">
                    <button id="editBtn">Edit</button>
                    <input type="text" id="add_id_user">
                </div>
                <div style="margin-bottom: 5px; ">
                    <button id="delete">Delete</button>
                    <input type="text" id="delete_id_user">
                </div>
                <div style="margin-bottom: 5px; ">
                    <button id="btn1">Список</button>
                    <button id="close" style="margin-left: 5px;">Закрыть все окна</button>
                </div>
                <div  style="overflow:auto">
                    <table id="users">
                    </table>
                </div>
            </div>
            <div style="width:40%; padding-left: 10px;  float: left" >
                <br>
                <form method="post" id="edit1">
                </form>

                <form method="post" id="createF">
                </form>
            </div>
        </div>
        <script type="text/javascript">
            $("#btn1").click(function () {
                $.getJSON('./list', function (data) {
                    $('#users').html("<tr><td>Id</td><td>ФИО</td><td>Адрес</td><td>Номер</td><tr>");
                    for (var i = 0; i < data.length; i++) {
                        $('#users').append('<tr><td>' + data[i].id + '</td><td>' + data[i].FIO +
                                '</td><td>' + data[i].address + '</td><td>' + data[i].number + '</td><tr>');
                    }
                });
            });
            $("#editBtn").click(function () {
                $.getJSON('./edit?id=' + document.getElementById('add_id_user').value, function (data) {
                    $('#edit1').html(
                            '<h3 for="fname">Изменение</h3><label for="fname">id</label><br><input type="id" id="id-edit" value="' + data.id + '"/><br>' +
                            '<label for="fname">ФИО</label><br><input type="text" id="FIO-edit"  value="' + data.FIO + '"/><br>' +
                            '<label for="fname">Адрес</label><br><input type="text" id="address-edit"  value="' + data.address + '"/><br>' +
                            '<label for="fname">Номер</label><br><input type="text" id="number-edit"  value="' + data.number + '"/><br>' +
                            '<button type="submit">Изменить</button>');
                });
            });
            $("#edit1").submit(function (event) {
                // Предотвращаем обычную отправку формы
                event.preventDefault();
                $.post('./edit', {'id': $('#id-edit').val(), 'FIO': $('#FIO-edit').val(), 'address': $('#address-edit').val()
                    , 'number': $('#number-edit').val()}, function (data) {
                    $('#result').html(data);
                }
                );
                $('#btn1').click();
            });
            $("#create").click(function () {
                $('#createF').html(
                        '<h3 for="fname">Создание</h3><label for="fname">ФИО</label><br><input type="text" id="FIO-create"  /><br>' +
                        '<label for="fname">Адрес</label><br><input type="text" id="address-create" /><br>' +
                        '<label for="fname">Номер</label><br><input type="text" id="number-create"/>' +
                        '<br><button type="submit">Добавить</button><br>');

            });
            $("#createF").submit(function (event) {
                // Предотвращаем обычную отправку формы
                event.preventDefault();
                $.post('./add', {'FIO': $('#FIO-create').val(), 'address': $('#address-create').val()
                    , 'number': $('#number-create').val()}, function (data) {
                    $('#result').html(data);
                }
                );
                $('#btn1').click();
            });
            $("#close").click(function () {
                $('#createF').html("");
                $('#edit1').html("");
                $('#users').html("");

            });
            $("#delete").click(function () {
                $.post('./delete', {'id': $('#delete_id_user').val()}, function (data) {
                    $('#result').html(data);
                }
                );
                $('#btn1').click();
            });
        </script>

        <!--this.getAttribute("data-parameter")-->
    </body>
</html>

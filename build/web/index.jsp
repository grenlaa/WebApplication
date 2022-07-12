
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
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

        <div class="container" >
            <div style="width:40%; float: left">
                <table id="users">

                </table>
            </div>
            <div style="width:40%; left:50%;  float: left" >

                <form method="post" id="edit1">
                </form>

                <form method="post" id="createF">
                </form>
            </div>
        </div>
        <script type="text/javascript">

            $("#btn1").click(function () {
                $.getJSON('http://localhost:8084/contacts/list', function (data) {
                    $('#users').html("<tr><td>Id</td><td>ФИО</td><td>Адрес</td><td>Номер</td><tr>");
                    for (var i = 0; i < data.length; i++) {
                        $('#users').append('<tr><td>' + data[i].id + '</td><td>' + data[i].FIO +
                                '</td><td>' + data[i].address + '</td><td>' + data[i].number + '</td><tr>');
                    }
                });
            });


            $("#editBtn").click(function () {
                $.getJSON('http://localhost:8084/contacts/edit?id=' + document.getElementById('add_id_user').value, function (data) {
                    $('#edit1').html(
                            '<label for="fname">Изменение</label><br><label for="fname">id</label><br><input type="id" id="id-edit" value="' + data.id + '"/><br>' +
                            '<label for="fname">ФИО</label><br><input type="text" id="FIO-edit"  value="' + data.FIO + '"/><br>' +
                            '<label for="fname">Адрес</label><br><input type="text" id="address-edit"  value="' + data.address + '"/><br>' +
                            '<label for="fname">Номер</label><br><input type="text" id="number-edit"  value="' + data.number + '"/><br>' +
                            '<button type="submit">Изменить</button>');
                });
            });

            $("#edit1").submit(function (event) {
                // Предотвращаем обычную отправку формы
                event.preventDefault();
                $.post('http://localhost:8084/contacts/edit', {'id': $('#id-edit').val(), 'FIO': $('#FIO-edit').val(), 'address': $('#address-edit').val()
                    , 'number': $('#number-edit').val()}, function (data) {
                    $('#result').html(data);
                }
                );
                $('#btn1').click();
            });
            $("#create").click(function () {
                $('#createF').html(
                        '<label for="fname">Создание</label><br><label for="fname">ФИО</label><br><input type="text" id="FIO-create"  /><br>' +
                        '<label for="fname">Адрес</label><br><input type="text" id="address-create" /><br>' +
                        '<label for="fname">Номер</label><br><input type="text" id="number-create"/><br>' +
                        '<label for="fname">id</label><br><button type="submit">Добавить</button><br>');

            });
            $("#createF").submit(function (event) {
                // Предотвращаем обычную отправку формы
                event.preventDefault();
                $.post('http://localhost:8084/contacts/add', {'FIO': $('#FIO-create').val(), 'address': $('#address-create').val()
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
                $.post('http://localhost:8084/contacts/delete', {'id': $('#delete_id_user').val()}, function (data) {
                    $('#result').html(data);
                }
                );
                $('#btn1').click();
            });
        </script>

        <!--this.getAttribute("data-parameter")-->
    </body>
</html>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form name="frmIndex" action="svlPreguntas" method="POST">
         <h1>BIENVENIDOS ESTUDIANTES!</h1>
            <p>INGRESEN SUS NOMBRES Y APELLIDOS POR FAVOR</p>
            <input type="text" name="txtNombreCliente" value="" size="50" />
            <br>
            <input type="button" value="INGRESAR AL EXAMEN" name="btnIngresarExamen" />
        </form>
    </body>
</html>

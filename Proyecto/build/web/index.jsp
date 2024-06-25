<html>
    <head>
        <title>BIENVENIDOS A NUESTRO VIDEOJUEGO!</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            body {
                background-color: #f7dc6f;
                font-family: 'Comic Sans MS', Arial, sans-serif;
            }
            form {
                width: 80%;
                margin: 40px auto;
                padding: 20px;
                border: 1px solid #ccc; 
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                background-color: #fff;
                background-image: url('https://www.transparentpng.com/thumb/smiley-face/smiley-face-png-25.png');
                background-size: 50px; 
                background-position: 95% 95%; 
            }
            h1 {
                color: #00698f; 
                font-size: 36px; 
                margin-bottom: 10px;
                text-shadow: 2px 2px #ff69b4; 
            }
            input[type="text"] {
                width: 100%; 
                height: 30px; 
                padding: 10px; 
                border: 1px solid #ccc; 
                border-radius: 5px; 
                background-color: #f2f2f2; 
                font-size: 18px; 
            }
            input[type="button"] {
                background-color: #4CAF50; 
                color: #fff; 
                width: 100%; 
                height: 30px; 
                padding: 10px; 
                border: none; 
                border-radius: 5px; 
                cursor: pointer; 
                font-size: 18px; 
            }
            input[type="button"]:hover {
                background-color: #3e8e41; 
            }
        </style>
    </head>
    <body>
        <form name="frmIndex" action="${pageContext.request.contextPath}/svlPreguntas" method="POST">
         <h1>GRACIAS POR DARNOS LA OPORTUNIDAD DE ESTAR AQUI!</h1>
            <p>INGRESEN SUS NOMBRES Y APELLIDOS POR FAVOR</p>
            <input type="text" name="txtNombreCliente" value="" size="50" />
            <br>
            <input type="submit" value="INGRESAR AL EXAMEN" name="btnIngresarExamen" />
        </form>
    </body>
</html>
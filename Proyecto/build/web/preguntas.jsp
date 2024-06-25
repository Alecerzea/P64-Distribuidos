<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Preguntas</title>
</head>
<body>
    <h1>Preguntas</h1>
    <%
        String[] preguntas = {
            "CUANTO ES 2 + 2?",
            "CUANTO ES 2 x 10?",
            "CUANTO ES 3 + 2?",
            "Cuanto es el resultado de 2x5?",
            "El resultado de 2x5 es 7, Verdadero o falso?",
            "Si Laura tiene 15 manzanas, y Pepe tiene 7, cuantas manzanas tienen en total?",
            "Si tu papa tiene 127 dolaress, y tu mama tiene 68, cuantos dolares tienen en total?"
        };

        String[] respuestas = {
            "4",
            "20",
            "5",
            "10",
            "FALSO",
            "22",
            "193"
        };
        
        String[] aleatoria = new String [7]; 
                
        // Mezclar las preguntas y respuestas de manera aleatoria
        int[] indices = new int[preguntas.length];
        for (int i = 0; i < indices.length; i++) {
            indices[i] = i;
        }
        for (int i = indices.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            int temp = indices[i];
            indices[i] = indices[j];
            indices[j] = temp;
        }

        // Mostrar las preguntas y respuestas
        for (int i = 0; i < preguntas.length; i++) {
            int indice = indices[i];
            aleatoria[i]=preguntas[i];
            out.println("<p>" + preguntas[indice] + "</p>");
            out.println("<p>Respuesta: " + respuestas[indice] + "</p>");
            out.println("<hr>");
        }
    %>
</body>
</html>
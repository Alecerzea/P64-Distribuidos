package Logica;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class pregunta {

    static int cRCorrecta = 0, cRIncorrecta = 0;
    static String[] preguntas = {
        "CUANTO ES 2 + 2 ?",
        "CUANTO ES 2 x 10 ?",
        "CUANTO ES 3 + 2 ?",
        "Cuanto es el resultado de 2x5?",
        "El resultado de 2x5 es 7, Verdadero o falso?",
        "Si Laura tiene 15 manzanas, y Pepe tiene 7, cuantas manzanas tienen en total?",
        "Si tu papa tiene 127 dolaress, y tu mama tiene 68, cuantos dolares tienen en total?"};

    static String[] respuesta = {
        "4",
        "20",
        "5",
        "10",
        "FALSO",
        "22",
        "193"};

    public void EstablecerPreguntas() {

    }

    public static ArrayList calcularAleatorios(int inicio, int fin) {
        ArrayList numerosAleatorios = new ArrayList();
        int numero;
        while (numerosAleatorios.size() < (fin - inicio) + 1) {
            //Aún no se han generado todos los números
            numero = generarAleatorio(inicio, fin);
            if (numerosAleatorios.isEmpty()) {
                //Si la lista esta vacía, se añade
                numerosAleatorios.add(numero);
            } else {
                //Si no, se comprueba que no esté ya en la lista
                if (!numerosAleatorios.contains(numero)) {
                    numerosAleatorios.add(numero);
                }
            }
        }
        return numerosAleatorios;
    }

    public static int generarAleatorio(int inicio, int fin) {
        Random ran = new Random();
        return ran.nextInt((fin - inicio) + 1) + inicio;
    }

    public static ArrayList GenerarPreguntas() {
        ArrayList aleatorios = new ArrayList();
        aleatorios = calcularAleatorios(0, 6);
        System.out.println("INICIANDO PREGUNTAS\n");
        for (int i = 0; i < aleatorios.size(); i++) {
            System.out.println(i + ": " + preguntas[(Integer) aleatorios.get(i)]);
        }
        return aleatorios;
    }

    public static void VerificarRespuestas(Object seleccion) {

        for (int i = 0; i < preguntas.length; i++) {
            if (respuesta[i] == seleccion) {
                cRCorrecta++;
            } else {
                cRIncorrecta++;
            }
        }
        System.out.println("RESPUESTA CORRECTAR: "+cRCorrecta);
        System.out.println("RESPUESTA CORRECTAR: "+cRIncorrecta);
    }

    public static void main(String[] args) {
        System.out.println("INICIANDO PROGRAMA");
        
    }
}

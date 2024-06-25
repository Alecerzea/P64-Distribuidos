

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Katherina Zea
 */
@WebServlet(name = "svlPreguntas", urlPatterns = {"/svlPreguntas"})
public class svlPreguntas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    static String[] aleatoriaP;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet svlPreguntas</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet svlPreguntas at " + request.getContextPath() + "</h1>");
            aleatoriaP = aleatorioWeb();

            for (int i = 0; i < aleatoriaP.length; i++) {
                out.println("<h1>" + aleatoriaP[i] + "</h1>");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private static String[] aleatorioWeb() {
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

        String[] aleatoria = new String[7];

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
            aleatoria[i] = preguntas[i];
        }
        return aleatoria;
    }

    private static void aleatorio() {
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

        String[] aleatoria = new String[7];

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
            aleatoria[i] = preguntas[i];
            System.out.println(preguntas[indice]);
            System.out.println(respuestas[indice]);
        }
    }

   // public static void main(String[] args) {
  //      aleatorio();
  //  }
}

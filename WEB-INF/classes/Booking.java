import java.io.*;
import java.util.regex.*;    
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/Booking"})
public class Booking extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Booking</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<form action='/Booking' method='POST'>");
        out.println("Name: <input type='text' name='name'/> <br/>");
        out.println("Phone: <input type='text' name='phone'/> <br/>");
        out.println("Address: <input type='text' name='address'/> <br/>");
        out.println("Email: <input type='text' name='email'/> <br/>");
        out.println("<input type='submit'/>");
        out.println("<input type='clear'/>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpResponse response){
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
    }

    public boolean validateName(String name){
        return false;
    }

    public boolean validateEmail(String email){
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        return false;
    }
}  
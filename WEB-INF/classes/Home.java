import java.io.*;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import javax.servlet.*;
import java.time.LocalDate;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/Home"})
public class Home extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int currWeek = 1;
        ServletContext ctx = this.getServletContext();
        File bookingFile = new File(ctx.getRealPath("/WEB-INF/bookings.txt"));

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Home</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>Home</h1>");

        out.println(generateNewTable(currWeek, bookingFile));

        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    // Sample Booking Input = week, day, time, name
    public String generateNewTable(int currWeek, File bookingFile){
        String out  = "";
        ArrayList<String> bookings = new ArrayList<>();

        try{
            Scanner scanner = new Scanner(bookingFile);
            String booking = "";
            while(scanner.hasNext()){
                booking = scanner.nextLine();
                if(Character.getNumericValue(booking.charAt(0)) == currWeek){
                    bookings.add(booking);
                }
            }   
        }
        catch(Exception e){}

        String[] dayNames = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for(int i = 0; i < 7; i++){
            out += "<div style='display: inline-grid;'>" + dayNames[i] + "  " + bookings.size() + "</div>";
        }
        return out;
    }
}   
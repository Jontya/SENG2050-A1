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
                out.println("<link rel='stylesheet' href='" + request.getContextPath() + "/styling.css' type='text/css'>");
            out.println("</head>");
            out.println("<body class='home-container'>");
                out.println("<h1 class='web-header'>Home</h1>");
                out.println(generateNewTable(currWeek, bookingFile));
            out.println("</body>");
        out.println("</html>");
        out.close();
    }

    // Sample Booking Input = week, day, time, name
    public String generateNewTable(int currWeek, File bookingFile){
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

        String out = "<div class='calender-cols'>";
        out  += "<div class='calender-time' id='blank'>T</div>";
        int hour = 6;
        String currTime = "";
        for(int i = 0; i < 25; i++){
            if(i%2 == 0){
                // even
                currTime = hour + ":00";
            }
            else{
                // odd
                currTime = hour + ":30";
                hour++;
            }
     
            out += "<div class='calender-time'>" + currTime + "</div>";
        }
        out += "</div>";

        String[] dayNames = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for(int i = 0; i < 7; i++){
            out += "<div class='calender-cols'>";
            out += "<div class='calender-days'>" + dayNames[i] + "</div>";
            for(int x = 0; x < 25; x++){
                out += "<div class='calender-apt'></div>";
            }
            out += "</div>";
        }
        return out;
    }
}   
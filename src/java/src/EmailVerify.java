package src;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hisham
 */

public class EmailVerify extends HttpServlet 
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) 
        {
            String Name = request.getParameter("fName");
            String Email = request.getParameter("email");
            
            UserInfo user = new UserInfo (Name, Email); //to be checked
            
               SendEmail sm = new SendEmail();
            boolean test = sm.sendEmail(user);
            
            //check if the email send successfully
            if(test)
            {
                HttpSession session = request.getSession();
                response.sendRedirect("index.html");
                
            }
            else 
            {
                out.println("Faild to send confirmation email");
            }
                
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}

package src;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HishamR
 */
public class Registration extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Registration</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Registration at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        
        int count = 0;
        resp.setContentType("text/html");
        
        UserInfo userInfo = new UserInfo();
        userInfo.setfName(req.getParameter("fName"));
        userInfo.setEmail(req.getParameter("email"));
        userInfo.setPassword(req.getParameter("password"));
        String packageType = req.getParameter("Packages");
        userInfo.setPackage(packageType);
        userInfo.setAge(Integer.parseInt(req.getParameter("age")));
        userInfo.setWeight(Integer.parseInt(req.getParameter("weight")));
        userInfo.setHeight(Integer.parseInt(req.getParameter("height")));
        userInfo.setpNum(req.getParameter("pNum"));
        
        PrintWriter Out = resp.getWriter();
        
        if("".equals(userInfo.getfName()) || "".equals(userInfo.getEmail()) || "".equals(userInfo.getPassword()) || "".equals(userInfo.getAge()) 
                || "".equals(userInfo.getWeight()) || "".equals(userInfo.getHeight()) || "".equals(userInfo.getpNum()))
        {

            Out.println("<center><pre><b>Complete all data required</b></pre></center>");  //If data was left empty, even if all filled but one is not //Will be changed
            count = 1; 
        }
        
        if(userInfo.getpNum().length() != 11)
        {
            Out.println("<center>ERROR!</center>"); //Will be changed
            count = 1;
        }
        if(count == 1) //law error 7asal 
        {
            req.getRequestDispatcher("JoinNow.html").include(req, resp);
        }
        else if(count == 0 ) //law mfesh error
        {
            UserDAO userDAO = new UserDAO();
            int numberofpackages = userDAO.getNoOfLimitedPackages(packageType);
            System.out.println("==================== Number of packages = " + numberofpackages);
            int result;
            if(packageType.equalsIgnoreCase("Package3") && numberofpackages > 10){
                result = -1;
            }else{
                result = userDAO.register(userInfo);
            }
            
            System.out.println("=======================Result: "+result);
            if (result == 1)
            {
                req.getRequestDispatcher("/Confirmation.html").include(req, resp);
              
            }
            else if (result == -1)
            {
                Out.println("<center>Offer Expired, try Another Package!</center>"); //Will be changed
                req.getRequestDispatcher("JoinNow.html").include(req, resp);
              
            }
            else  
            {
                Out.println("<center>DATABASE ERROR!</center>"); //Will be changed
                req.getRequestDispatcher("JoinNow.html").include(req, resp);

            }
        }
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

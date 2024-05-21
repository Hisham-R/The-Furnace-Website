<%-- 
    Document   : AllUsersInfo
    Created on : Dec 20, 2021, 1:47:42 AM
    Author     : HishamR
--%>

<%@page import="src.UserInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <body>
        <jsp:useBean id="JSP1" scope="session" class="src.UserInfo">
            <jsp:setProperty property="*" name="JSP1"/>
        </jsp:useBean>
        <%
            int O = JSP1.Register();
            if( O == 1) //If registeration is accepted
            {
                request.getRequestDispatcher("Confirmation.html").include(request, response);
            }
            else 
            {
                out.println("<center><em>Wrong</em></center>");
                request.getRequestDispatcher("JoinNow.html").include(request, response);
                
            }
        %>
    </body>
</html>

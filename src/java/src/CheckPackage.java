package src;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author HishamR
 */
public class CheckPackage 
{
    public static void main(String[] args) throws Exception
    {
       
        Connection con = 
           DriverManager.getConnection("jdbc:derby://localhost:1527/Gym", "Gym", "1234");
        
        String query = "select count(*) from USERINFO where PACKAGE = 'Package3' ";
        
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        rs.next();
        
        int countpackages = rs.getInt(1);
        System.out.println("====================Number of packages = " +countpackages);
        con.close();
            
    }
    
}
    


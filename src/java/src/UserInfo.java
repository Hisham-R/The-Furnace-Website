/**
 *
 * @author HishamR
 */

package src;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserInfo implements Serializable
{
    
    private String fName;
    private String email;
    private String password;
    private String Package;
    private int age;
    private int weight;
    private int height;
    private String pNum;
    
    public UserInfo()
    {
    }

    
    public UserInfo(String fName, String email) {
        this.fName = fName;
        this.email = email;
    }
    
    public UserInfo(String fName, String email, String password, String Package, int age, int weight, int height, String pNum) {
        this.fName = fName;
        this.email = email;
        this.password = password;
        this.Package = Package;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.pNum = pNum;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPackage() {
        return Package;
    }

    public void setPackage(String Package) {
        this.Package = Package;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getpNum() {
        return pNum;
    }

    public void setpNum(String pNum) {
        this.pNum = pNum;
    }

    
     public int Register() throws SQLException 
    {
        Connection con = null;
        int result = 0;
        try{
        String sql = "insert into USERINFO" + "(FULLNAME,EMAIL,PASSWORD,PACKAGE,AGE,WEIGHT,HEIGHT,PHONENUMBER) values" + "(?,?,?,?,?,?,?)";
        
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/Gym","Gym","1234");
        PreparedStatement prepStat = con.prepareStatement(sql);
        prepStat.setString(1,fName);
        prepStat.setString(2,email);
        prepStat.setString(3,password);
        prepStat.setString(4,Package);
        prepStat.setInt(5,age);
        prepStat.setInt(6,weight);
        prepStat.setInt(7,height);
        prepStat.setString(8,pNum);
        
        result = prepStat.executeUpdate(); 
        }
        catch(ClassNotFoundException  e){
        
            e.printStackTrace();
        
        }
        
        /*finally{
            try
            {
                if(con != null)
                {
                    con.close();
                }
                else
                {

                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }*/
        return result;
    }
}

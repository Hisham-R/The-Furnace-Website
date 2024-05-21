/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author HishamR
 */
public class UserDAO {

    public int register(UserInfo userInfo) {
        Connection con = null;
        int result = 0;
        try {
            String sql = "insert into USERINFO" + "(FULLNAME,EMAIL,PASSWORD,PACKAGE,AGE,WEIGHT,HEIGHT,PHONENUMBER) values" + "(?,?,?,?,?,?,?,?)";
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/Gym", "Gym", "1234");
            PreparedStatement prepStat = con.prepareStatement(sql);
            prepStat.setString(1, userInfo.getfName());
            prepStat.setString(2, userInfo.getEmail());
            prepStat.setString(3, userInfo.getPassword());
            prepStat.setString(4, userInfo.getPackage());
            prepStat.setInt(5, userInfo.getAge());
            prepStat.setInt(6, userInfo.getWeight());
            prepStat.setInt(7, userInfo.getHeight());
            prepStat.setString(8, userInfo.getpNum());
            result = prepStat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean login(String email, String password) {
        Connection con = null;
        boolean result = false;
        try {
            String sql = "select * from USERINFO where EMAIL=? and PASSWORD=?";
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/Gym", "Gym", "1234");
            PreparedStatement prepStat = con.prepareStatement(sql);
            prepStat.setString(1, email);
            prepStat.setString(2, password);
            ResultSet rs = prepStat.executeQuery();
            while (rs.next()) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getNoOfLimitedPackages(String packageType) {
        try {
            Connection con = null;
            String sql = "select * from USERINFO where PACKAGE = ?";
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/Gym", "Gym", "1234");
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, packageType);
            ResultSet rs = ps.executeQuery();
            rs.last();
            int countpackages;
            if(packageType.equalsIgnoreCase("Package3")){
                countpackages=rs.getRow()+1;
            }else{
                countpackages=0;
            }
            return countpackages;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}

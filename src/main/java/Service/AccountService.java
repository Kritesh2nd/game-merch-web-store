package Service;

import DBConnection.DBConnection;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountService {
    public void createNewUser(User user){
        String query = "insert into user(name,email,password,type)" + "values(?,?,?,?)";
        PreparedStatement ps = new DBConnection().getStatement(query);
        try{
            ps.setString(1,user.getName());
            ps.setString(2,user.getEmail());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getType());
            ps.execute();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public User loginUser(User user){
        User u = null;
        String query = "select * from user where email=? and password=?";
        PreparedStatement ps = new DBConnection().getStatement(query);
        try{
            ps.setString(1,user.getEmail());
            ps.setString(2,user.getPassword());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setType(rs.getString("type"));
            }
        }
         catch(SQLException e){
            e.printStackTrace();
        }
        return u;
    }
}

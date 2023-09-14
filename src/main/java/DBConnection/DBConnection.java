package DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DBConnection{
    Connection con = null;
    public DBConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String dbUrl = "jdbc:mysql://localhost:3306/pixel";
            String dbName = "kritesh";
            String dbPassword = "kritesh";
            con = DriverManager.getConnection(dbUrl, dbName, dbPassword);
            System.out.println("DB connnected");
        }
        catch (SQLException | ClassNotFoundException e){
            System.out.println("ERROR: "+e);
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        DBConnection dbc = new DBConnection();
    }
    public PreparedStatement getStatement(String query){
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(query);
        }
        catch (SQLException e){
            System.out.println("ERROR: "+e);
            e.printStackTrace();
        }
        return ps;
    }
}

/*

sudo systemctl start tomcat9

sudo systemctl stop

sudo systemctl start mysqld

mysql -u kritesh -p
kritesh


==========================================================

drop database pixel;

show databases;

create database pixel;

use pixel;

CREATE TABLE test1 (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    email varchar(255),
    password varchar(255),
    PRIMARY KEY (id)
);

select*from test1;

insert into test1 (name,email,password) values
("apple","apple@email.com","applepass"),
("ball","ball@email.com","ballpass"),
("cat","cat@email.com","catpass"),
("dog","dog@email.com","dogpass"),
("elk","elk@email.com","elkpass")
;

select*from test1;




*/
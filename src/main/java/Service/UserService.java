package Service;
import DBConnection.DBConnection;
import Model.Basket;
import Model.Merch;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UserService {
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
    public List<Merch> merchList(){
        String query = "select * from merch where featured='on';";
        // UPDATE merch SET featured='on' where id <= 10;
        List<Merch> merchList = merchListNoQuery(query);
        System.out.println("Merch List sizexx: "+merchList.size());
        for(Merch m:merchList){
            System.out.println(m.getId()+" "+m.getTitle()+" "+m.getGenre()+" "+m.getPrice()+" "+m.getFeatured()+" " );
        }
       return merchList;
    }
    public void addNewBasket(Basket basket){
        // to make sure same product is not added to cart again by same user
        String query = "insert into basket(id,user_id,product_id,quantity,type) values (?,?,?,?,?);";
        List<Basket> basketList = basketCartListByUserId(basket.getUser_id());
        boolean addToCart = true;
        System.out.println("basketList size: "+basketList.size());
        for(Basket b: basketList){
            System.out.println("data, "+b.getProduct_id()+", "+basket.getProduct_id()+", "+b.getUser_id()+", "+basket.getUser_id());
            if(b.getProduct_id() == basket.getProduct_id() && b.getUser_id() == basket.getUser_id()){
                addToCart = false;
                break;
            }
        }
        System.out.println("addToCart: "+addToCart);
        if(addToCart){
            inputBasketNoQuery(query, basket);
        }
    }
    
    public List<Basket> basketCartListByUserId(int userId){
        String query="select * from basket where user_id = "+userId+" and type = 'cart';";
        List<Basket> basketList = basketListNoQuery(query);
        return basketList;
    }
    public List<Merch> getCartListByUserId(int userId){
        String query = "select * from basket as B inner join merch as M on B.product_id = M.id where B.type = 'cart' and B.user_id = "+userId+";";
        List<Merch> merchList = merchListNoQuery(query);
        System.out.println("getCartListByUserId");
        for(Merch m:merchList){
            System.out.println(m.getId()+" "+m.getTitle()+" "+m.getGenre()+" "+m.getPrice()+" "+m.getFeatured()+" " );
        }
        return merchList;
    }
//    public void getCartListByListOfCartIds(List<Basket> cartIdList){
//        try{
//        String query="";
//        System.out.println("getCartListByListOfCartIds");
//        for(Basket b : cartIdList){
//            System.out.println(""+b.getProduct_id());
//            query="select * from basket where user_id = 5 and product_id = 1;";
//            List<Merch> merchList = merchListNoQuery(query);
//            System.out.println("0 pos data "+merchList.get(0));
////            if(merchList!=null){
////                
////            }
//        }
//        
//        query="select * from basket where user_id = 5 and type = 'cart';";
//        List<Basket> basketList = basketListNoQuery(query);
//        }
//        catch(Exception e){
//            System.out.println("Err: "+e);
//        }
//    }
//    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
        UserService us = new UserService();
        us.merchList();
    } 
    public List<Merch> merchListNoQuery(String query){
        List<Merch> merchList = new ArrayList<>();
        PreparedStatement ps = new DBConnection().getStatement(query);
        try{
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Merch m = new Merch();
                m.setId(rs.getInt("id"));
                m.setPrice(rs.getInt("price"));
                m.setQuantity(rs.getInt("quantity"));
                m.setDiscount(rs.getInt("discount"));
                m.setSold_amount(rs.getInt("sold_amount"));
                m.setCode(rs.getString("code"));
                m.setType(rs.getString("type"));
                m.setTitle(rs.getString("title"));
                m.setDescription(rs.getString("description"));
                m.setGame(rs.getString("game"));
                m.setGenre(rs.getString("genre"));
                m.setImage(rs.getString("image"));
                m.setFeatured(rs.getString("featured"));
                m.setLatest(rs.getString("latest"));
                merchList.add(m);
            }
        }
         catch(SQLException e){
            e.printStackTrace();
        }
        return merchList;
    }
    public void inputBasketNoQuery(String query, Basket b){
        PreparedStatement ps = new DBConnection().getStatement(query);
        try{
            ps.setInt(1,b.getId());
            ps.setInt(2,b.getUser_id());
            ps.setInt(3,b.getProduct_id());
            ps.setInt(4,b.getQuantity());
            ps.setString(5,b.getType());
            ps.execute();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public List<Basket> basketListNoQuery(String query){
        List<Basket> basketList = new ArrayList<>();
        PreparedStatement ps = new DBConnection().getStatement(query);
        try{
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Basket b = new Basket();
                b.setId(rs.getInt("id"));
                b.setUser_id(rs.getInt("user_id"));
                b.setProduct_id(rs.getInt("product_id"));
                b.setQuantity(rs.getInt("quantity"));
                b.setType(rs.getString("type"));
                basketList.add(b);
            }
        }
         catch(SQLException e){
            e.printStackTrace();
        }
        return basketList;
    }
}

/*
//select*from


select * from cart as A inner join productinfo as B on A.pid = B.id where A.uid=?
select * from merch as M inner join cart as C on 
select title, game, price from basket as B inner join merch as M on B.product_id = M.id where B.type = 'cart' and B.id = 1;
select M.id, B.id, B.user_id, title, game, price from basket as B inner join merch as M on B.product_id = M.id where B.type = 'cart';

select M.id, B.id, B.user_id, title, game, price from basket as B inner join merch as M on B.product_id = M.id where B.type = 'cart' and B.user_id = 4;

select * from basket as B inner join merch as M on B.product_id = M.id where B.type = 'cart' and B.user_id = 4;
select * from basket as B inner join merch as M on B.product_id = M.id where B.type = 'cart' and B.user_id = 5;


*/
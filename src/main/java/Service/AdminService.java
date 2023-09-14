package Service;

import DBConnection.DBConnection;
import Model.Merch;
import Model.User;
import Model.ViewMerch;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminService {
    public void addNewMerch(Merch merch){
        merch.setCode(getProductCode(merch.getType()));
        String query = "insert into merch(title,description,game,type,genre,code,image,featured,latest,price,quantity,discount,sold_amount)" 
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        inputMerchNoQuery(query, merch);
//        System.out.println("Add merch =======================");
//        System.out.println("title: "+merch.getTitle()+"");
//        System.out.println("title: "+merch.getPrice()+"");
//        System.out.println("title: "+merch.getGenre()+"");
//        System.out.println("title: "+merch.getType()+"");

    }
    public void updateMerchById(Merch merch) throws SQLException{
        try{
        System.out.println("updateMerchById");
        System.out.println(""+merch.getId());
        System.out.println(""+merch.getTitle());
        System.out.println(""+merch.getPrice());
        if(merch.getFeatured()==null){merch.setFeatured("null");}
        if(merch.getLatest()==null){merch.setLatest("null");}
        String query = "UPDATE merch SET title=?, description=?, game=?, type=?, genre=?, code=?, image=?, featured=?, latest=?, price=?, quantity=?, discount=?, sold_amount=? WHERE id="+merch.getId()+";";
        inputMerchNoQuery(query, merch);
        }
        catch(Exception e){
            System.out.println("ERR: "+e);
        }
    }
    public List<Merch> merchList(){
        String query = "select * from merch";
        List<Merch> merchList = merchListNoQuery(query);
        return merchList;
    }
    public Merch merchListOne(int id){
        String query = "select * from merch where id="+id+";";
        Merch merch=null;
        List<Merch> merchList = merchListNoQuery(query);
        for(Merch m:merchList){merch = m;}
        return merch;
    }
    public List<Merch> merchList(ViewMerch vm){
        String query = "select * from merch";
        if(vm.getCategory().equalsIgnoreCase("ascending"))
           {query = "select * from merch order by title asc;";}
        else if(vm.getCategory().equalsIgnoreCase("descending"))
            {query = "select * from merch order by title desc;";}
        else if(vm.getCategory().equalsIgnoreCase("byProductId"))
            {query = "select * from merch";}
        else if(vm.getCategory().equalsIgnoreCase("byProductType"))
            {query = "select * from merch order by type asc;";}
        else if(vm.getCategory().equalsIgnoreCase("mostPopular"))
            {query = "select * from merch";}
        else if(vm.getCategory().equalsIgnoreCase("featured"))
            {query = "select * from merch";}
        else if(vm.getCategory().equalsIgnoreCase("highestPrice"))
            {query = "select * from merch order by price desc;";}
        else if(vm.getCategory().equalsIgnoreCase("lowestPrice"))
            {query = "select * from merch order by price asc;";}
        List<Merch> merchList = merchListNoQuery(query);
        return merchList;
    }  
    public List<Merch> merchList(String s){
        String query = "SELECT * FROM merch WHERE LOWER(title) LIKE '%"+s+"%';";
        List<Merch> merchList = merchListNoQuery(query);
        return merchList;
    }
    public List<Merch> merchList(String option, String data){
    // option = type, genre
    // type = tshirt, hoodie, hat, bag, figure, poster
    // genre = action, adventure, rpg, simulation, strategy, puzzle
        String query = "select * from merch where "+option+"=\""+data+"\"";
        List<Merch> merchList = merchListNoQuery(query);
        return merchList;
    }
//    public void updateMerchData(){
//        String query = "select * from merch";
//        List<Merch> merchList = merchListNoQuery(query);
//    }
    public void deleteMerchById(int id){
        String query = "delete from merch where id = ?";
        deleteDataNoQuery(query, id);
    }
    
    
    
    
    public List<User> userList(){
        String query = "select * from user where type = 'customer';";
        List<User> userList = userListNoQuery(query);
        return userList;
    }
    public List<User> userListOne(int id){
        String query = "select * from user where type = 'customer';";
        List<User> userList = userListNoQuery(query);
        return userList;
    }
    public void deleteUserById(int id){
        String query = "delete from user where id = ?";
        deleteDataNoQuery(query, id);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void inputMerchNoQuery(String query, Merch m){
        PreparedStatement ps = new DBConnection().getStatement(query);
        try{
            ps.setString(1,m.getTitle());
            ps.setString(2,m.getDescription());
            ps.setString(3,m.getGame());
            ps.setString(4,m.getType());
            ps.setString(5,m.getGenre());
            ps.setString(6,m.getCode());
            ps.setString(7,m.getImage());
            ps.setString(8,m.getFeatured());
            ps.setString(9,m.getLatest());
            ps.setInt(10,m.getPrice());
            ps.setInt(11,m.getQuantity());
            ps.setInt(12,m.getDiscount());
            ps.setInt(13,m.getSold_amount());
            ps.execute();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
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
    public List<User> userListNoQuery(String query){
        List<User> userList = new ArrayList<>();
        PreparedStatement ps = new DBConnection().getStatement(query);
        try{
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                u.setPassword(rs.getString("password"));
                userList.add(u);
            }
        }
         catch(SQLException e){
            e.printStackTrace();
        }
        return userList;
    }
    public void deleteDataNoQuery(String query, int id){
        PreparedStatement ps = new DBConnection().getStatement(query);
        try{
            ps.setInt(1,id);
            ps.execute();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public String getProductCode(String type){
        // to auto add product code to item based on product type
//        type = manageCodeName(type);
        String code="";
        List<Merch> merchList = merchList("type",type);
        Merch merch = null;
        for(Merch m : merchList){merch = m;}
        if(merch != null){code = getNewCode(merch.getCode());}
        else{code = getFirstCode(type);}
        return code;
    }
    public String getFirstCode(String type){
        String firstCode="";
        if(type.equalsIgnoreCase("figure")){firstCode = "FIG001";}
        else if(type.equalsIgnoreCase("poster")){firstCode = "POS001";}
        else if(type.equalsIgnoreCase("tshirt")){firstCode = "TS001";}
        else if(type.equalsIgnoreCase("hat")){firstCode = "HAT001";}
        else if(type.equalsIgnoreCase("bag")){firstCode = "BAG001";}
        else if(type.equalsIgnoreCase("hoodie")){firstCode = "HD001";}
        return firstCode;
    }
    public String getNewCode(String str){
        char ch;
        String newStr="",word="";
        int newCode,codeLen,len = str.length();
        for(int a=len-1;a>=0;a--){
            ch = str.charAt(a);
            if(a<len-3){word = ch + word;}
            else{newStr = ch + newStr;}
        }
        newCode = (Integer.parseInt(newStr))+1;
        codeLen = Integer.toString(newCode).length();
        for(int a=0;a<3-codeLen;a++){word += "0";}
        newStr = word+newCode;
        return newStr;
    }
    public String manageCodeName(String name){
        String newName="";
        if(name.equalsIgnoreCase("figure")){newName="Figure";}
        else if(name.equalsIgnoreCase("poster")){newName="Poster";}
        else if(name.equalsIgnoreCase("tshirt")){newName="T-Shirt";}
        else if(name.equalsIgnoreCase("hat")){newName="Hat";}
        else if(name.equalsIgnoreCase("bag")){newName="Bag";}
        else if(name.equalsIgnoreCase("hoodie")){newName="Hoodie";}
        return newName;
    }
    public String reverseString(String str){
        String newStr="";
        char ch;
        for (int i=0; i<str.length(); i++){
            ch = str.charAt(i); //extracts each character
            newStr = ch + newStr; //adds each character in front of the existing string
        }
        return newStr;
    }
    public static void main(String[] args) {
        AdminService as = new AdminService();
        List<Merch> merchList = as.merchList();
        for(Merch m : merchList){
            System.out.println(""+m.getId()+", "+m.getTitle()+", "+m.getFeatured()+", "+m.getLatest());
        }
    }
}

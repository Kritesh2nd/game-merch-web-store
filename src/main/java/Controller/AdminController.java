package Controller;
import Model.Merch;
import Model.User;
import Model.ViewMerch;
import Service.AdminService;
import Service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "AdminController", urlPatterns = {"/admin"})
public class AdminController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String page = request.getParameter("page");
        out.print("Admin Controller, Page: "+page+"</br></br>");
        
//        Navigation Start
        if(page.equalsIgnoreCase("gotoAddMerch")){
            // [||][inventory] side dashboard link [part: inventory], to get add merch page
            request.setAttribute("jspMainDash", "inventory");
            request.setAttribute("jspDash", "gotoAddMerch");
            request.getRequestDispatcher("pages/dashboard.jsp").forward(request,response);
        }
        else if(page.equalsIgnoreCase("gotoViewMerch")){
            // [||][inventory] side dashboard link [part: inventory], to get edit merch page
            request.setAttribute("jspMainDash", "inventory");
            request.setAttribute("jspDash", "gotoViewMerch");
            request.getRequestDispatcher("admin?page=merchView").forward(request,response);
        }
        else if(page.equalsIgnoreCase("gotoEditMerch")){
            // [||][inventory] get list of merch from db where admin can edit the data
            try{
            String id = request.getParameter("id");
            int intId=0;
            if(id!=null){intId=Integer.parseInt(id);}
            Merch merch = new AdminService().merchListOne(intId);
            out.print("id: "+id+"<br/>");
            out.print("merch: "+merch.getTitle()+"<br/>");
            request.setAttribute("jspMainDash", "inventory");
            request.setAttribute("jspDash", "gotoEditMerch");
            request.setAttribute("singleMerch", merch);
            request.getRequestDispatcher("pages/dashboard.jsp").forward(request,response);
            }
            catch(Exception e){
                out.print("Err: "+e);
            }
        }
        else if(page.equalsIgnoreCase("gotoOrderView")){
            // [||][order] side dashboard link [part: order], to get list of order
            request.setAttribute("jspMainDash", "order");
            request.setAttribute("jspDash", "gotoOrderView");
            request.getRequestDispatcher("admin?page=orderView").forward(request,response);
        }
        else if(page.equalsIgnoreCase("gotoCustomerView")){
            // [||][customer] side dashboard link [part: order], to get list of order
            request.setAttribute("jspMainDash", "order");
            request.setAttribute("jspDash", "gotoCustomerView");
            request.getRequestDispatcher("admin?page=orderView").forward(request,response);
        }
//        Navigation End        
        
        
        
        else if(page.equalsIgnoreCase("merchAdd")){
            // [inventory], get data of merch to add in data base
            Merch m = new Merch();
            m.setTitle(request.getParameter("title"));
            m.setDescription(request.getParameter("description"));
            m.setGame(request.getParameter("game"));
            m.setPrice(Integer.parseInt(request.getParameter("price")));
            m.setQuantity(Integer.parseInt(request.getParameter("quantity")));
            m.setType(request.getParameter("type"));
            m.setGenre(request.getParameter("genre"));
            m.setFeatured(request.getParameter("featured"));
            m.setLatest(request.getParameter("latest"));
            out.print("featured: "+m.getFeatured()+"<br/>");
            out.print("latest: "+m.getLatest()+"<br/>");
            
            new AdminService().addNewMerch(m);
            request.setAttribute("productAdded", "true");
            out.print("hello");
            request.getRequestDispatcher("admin?page=gotoAddMerch").forward(request,response);
        }
        else if(page.equalsIgnoreCase("merchView")){
            // [inventory], get list of merch from db to display in web page
            try{
            out.print("page : merchView<br/>");
            ViewMerch viewMerch = new ViewMerch();
            viewMerch.setSearch(request.getParameter("search"));
            viewMerch.setCategory(request.getParameter("category"));
            viewMerch.setType(request.getParameter("type"));
            viewMerch.setGenre(request.getParameter("genre"));
            viewMerch.setAll(request.getParameter("all"));
            viewMerch.setDiscounted(request.getParameter("discounted"));
            
            List<Merch> merchList=null;
            if(viewMerch.getSearch()==null && viewMerch.getCategory()==null){
                out.print("Search & Category Null<br/>");
                merchList = new AdminService().merchList();
            }
            else if(viewMerch.getSearch()!=null && !viewMerch.getSearch().equals("")){
                out.print("Not Null search, serch:#"+viewMerch.getSearch()+"#+<br/>");
                viewMerch.setSearch(viewMerch.getSearch().toLowerCase());
                merchList = new AdminService().merchList(viewMerch.getSearch());
                
                for(Merch m:merchList){
                    out.print(""+m.getId()+", "+m.getTitle()+"<br/>");
                }
                out.print("merchList.size():"+merchList.size());
                request.setAttribute("merchSearch", request.getParameter("search"));
            }
            else if(viewMerch.getCategory()!=null){
                out.print("Not Null category<br/>");
                merchList = new AdminService().merchList(viewMerch);
                request.setAttribute("merchCategory", viewMerch.getCategory());
            }
            request.setAttribute("merchList", merchList);
            request.setAttribute("merchListSize",merchList.size());
            request.getRequestDispatcher("pages/dashboard.jsp").forward(request,response);
            }
            catch(Exception e){
                out.print("ERRzz: "+e);
            }
        }
        else if(page.equalsIgnoreCase("merchEdit")){
            // [inventory], this will take data from update form and update data in db
            out.print("merch edit<br/>");
            Merch m = new Merch();
            
            m.setTitle(request.getParameter("title"));
            m.setDescription(request.getParameter("description"));
            m.setGame(request.getParameter("game"));
            m.setPrice(Integer.parseInt(request.getParameter("price")));
            m.setQuantity(Integer.parseInt(request.getParameter("quantity")));
            m.setType(request.getParameter("type"));
            m.setGenre(request.getParameter("genre"));
            m.setFeatured(request.getParameter("featured"));
            m.setLatest(request.getParameter("latest"));
            
            m.setId(Integer.parseInt(request.getParameter("id")));
            m.setCode(request.getParameter("code"));
            m.setImage(request.getParameter("image"));
            m.setDiscount(Integer.parseInt(request.getParameter("discount")));
            m.setSold_amount(Integer.parseInt(request.getParameter("sold_amount")));
            
            out.print("title: "+m.getTitle()+"<br/>");
            out.print("getDescription: "+m.getDescription()+"<br/>");
            out.print("getGame: "+m.getGame()+"<br/>");
            out.print("getPrice: "+m.getPrice()+"<br/>");
            out.print("getQuantity: "+m.getQuantity()+"<br/>");
            out.print("getType: "+m.getType()+"<br/>");
            out.print("getGenre: "+m.getGenre()+"<br/>");
            out.print("getFeatured: "+m.getFeatured()+"<br/>");
            out.print("getLatest: "+m.getLatest()+"<br/>");
            out.print("getImage: "+m.getImage()+"<br/>");
            
            try{
                System.out.println("Trying to update data");
                new AdminService().updateMerchById(m);
                System.out.println("data updated");
                out.print("data updated<br/>");
            }
            catch(SQLException e){
//                e.printStackTrace();
                out.print("ERRORR: "+e+"<br/>");
                System.out.print("ERRORR: "+e);
            }
            
            
            
            
            out.print("<a href='admin?page=gotoViewMerch'>go to view merch</a>");
            
        }
        else if(page.equalsIgnoreCase("merchDelete")){
            // [inventory], this will delete request from user and delete data from db
            String id = request.getParameter("id");
            int intId=0;
            if(id!=null){intId=Integer.parseInt(id);}
            new AdminService().deleteMerchById(intId);
            request.getRequestDispatcher("admin?page=gotoViewMerch").forward(request,response);
            //deleteMerchById
        }
        
        
        
        else if(page.equalsIgnoreCase("orderView")){
            // [customer], get list of order from db to display in web page
            
        }
        else if(page.equalsIgnoreCase("customerView")){
            // [customer], get list of customer from db to display in web page
            List<User> customerList = new AdminService().userList();
            request.setAttribute("customerList", customerList);
            
            request.getRequestDispatcher("pages/dashboard.jsp").forward(request,response);
        }
        
        
        
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}

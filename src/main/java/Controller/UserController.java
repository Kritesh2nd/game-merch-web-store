package Controller;
import Model.Basket;
import Model.Merch;
import Model.User;
import Service.AdminService;
import Service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "UserController", urlPatterns = {"/user"})
public class UserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String page = request.getParameter("page");
        out.print("User Controller, Page "+page+"</br>");
        
        //navigation start
        if(page.equalsIgnoreCase("cart")){
            String uid = request.getParameter("uid");
            out.print("uid: "+uid+"<br/>");
            if(uid==null || uid.equals("")){
                out.print("NO user<br/>");
                request.getRequestDispatcher("./index.jsp").forward(request,response);
            }
            else{
                out.print("user exists<br/>");
                List<Merch> basketCartList = new UserService().getCartListByUserId(Integer.parseInt(uid));
                
                out.print("basketList size:"+basketCartList.size()+"<br/>");
                request.setAttribute("zz: ", basketCartList);
                for(Merch m:basketCartList){
                    out.print(m.getId()+" "+m.getTitle()+" "+m.getGenre()+" "+m.getPrice()+" "+m.getFeatured()+"<br/>" );
                }
//                request.getRequestDispatcher("pages/cart.jsp").forward(request,response);
            }
            out.print("this is cart, basketCartList size");
        }
        else if(page.equalsIgnoreCase("purchased")){
            
        }
        
        
        
        //navigation end
        
        
        
        
        else if(page.equalsIgnoreCase("index")){
            out.print("inside index");
            List<Merch> merchList = new UserService().merchList();
            out.print("Merch List size: "+merchList.size()+"<br/>");
            for(Merch m:merchList){
                out.print(m.getId()+" "+m.getTitle()+", "+m.getGenre()+", "+m.getPrice()+", "+m.getFeatured()+"<br/>");
            }
            out.print("Data fetch sucessfull<br/>");
            request.setAttribute("prdFetched:", "yes");
            request.setAttribute("merchList", merchList);
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }
        else if(page.equalsIgnoreCase("category")){
            String category = request.getParameter("category");
            out.print("category "+category+"</br>");
            List<Merch> merchList = new AdminService().merchList("type",category);
            System.out.println("typed data fetching =======================");
            for(Merch merch:merchList){
                System.out.println("id: "+merch.getId()+"");
                System.out.println("title: "+merch.getTitle()+"");
                System.out.println("price: "+merch.getPrice()+"");
                System.out.println("genre: "+merch.getGenre()+"");
                System.out.println("type: "+merch.getType()+"");
                System.out.println("--------------------------------");
            }
            System.out.println("typed data fetched ===================");
            request.setAttribute("prdCategoryFetched:", "yes");
            request.setAttribute("prdCategoryMerchList", merchList);
            request.getRequestDispatcher("pages/result.jsp").forward(request,response);
        }
        //addToCart
        else if(page.equalsIgnoreCase("addToCart")){
            try{
            String id = request.getParameter("id");
            String uid = request.getParameter("uid");
            if(id!=null && uid!=null){
                Basket basket = new Basket();
                basket.setUser_id(Integer.parseInt(uid));
                basket.setProduct_id(Integer.parseInt(id));
                basket.setQuantity(1);
                basket.setType("cart");
                new UserService().addNewBasket(basket);
            
            out.print("Product added to the cart");
            }
            else{
                out.print("NO USER<br/>");
            }
            
            }
            catch(Exception e){
                out.print("ERR_ADD_TO_CART: "+e);
            }
        }

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    
    
}

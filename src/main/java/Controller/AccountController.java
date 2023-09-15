package Controller;
import Model.User;
import Hashing.HashPassword;
import Model.Merch;
import Service.AccountService;
import Service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AccountController", urlPatterns = {"/account"})
public class AccountController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String page = request.getParameter("page");
        String link = request.getParameter("link");
        out.print("Account Controller, Page: "+page+", link: "+link+"</br>");
        
        
        
        if(page.equalsIgnoreCase("account")){
            
            RequestDispatcher rd = request.getRequestDispatcher("pages/account.jsp");
            rd.forward(request,response);
        }
        if(page.equalsIgnoreCase("gotoSignIn")){
            request.setAttribute("type", "gotoSignIn");
            RequestDispatcher rd = request.getRequestDispatcher("pages/account.jsp");
            rd.forward(request,response);
        }
        else if(page.equalsIgnoreCase("gotoSignUp")){
            request.setAttribute("type", "gotoSignUp");
            RequestDispatcher rd = request.getRequestDispatcher("pages/account.jsp");
            rd.forward(request,response);
        }
        if(page.equalsIgnoreCase("signUp")){
            // Creating new account
            // Taking name, email & password from user to insert into database
            User user = new User();
            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            String hashPassword = new HashPassword().hashThisPass(request.getParameter("password"));
            user.setPassword(hashPassword);
            
            out.println("name: "+user.getName()+"<br/>");
            out.println("email: "+user.getEmail()+"<br/>");
            out.println("password: "+user.getPassword()+"<br/>");
            // it will only do sign up if name, email & password is not null
            if(user.getName()!=null && user.getEmail()!=null && user.getPassword()!=null){
                new AccountService().createNewUser(user);
                user = new AccountService().loginUser(user);            
                request.setAttribute("userData", user);
            }
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }
        else if(page.equalsIgnoreCase("signIn")){
            // Loggin In
            // Taking email & password from user to check in database
            User user = new User();
            user.setEmail(request.getParameter("email"));
            String hashPassword = new HashPassword().hashThisPass(request.getParameter("password"));
            user.setPassword(hashPassword);
            
            out.println("id: "+user.getId()+"<br/>");
            out.println("email: "+user.getEmail()+"<br/>");
            out.println("password: "+user.getPassword()+"<br/>");
            // it will only do sign in if email & password is not null
            try{
                if(user.getEmail()!=null && user.getPassword()!=null){
                    user = new AccountService().loginUser(user);
                    request.setAttribute("userData", user);
//                    out.print("Login : "+user.getId());
                    if(user == null){
                        request.getRequestDispatcher("account?page=gotoSignIn").forward(request,response);
                    }
                    else if(user.getType().equalsIgnoreCase("admin")){
                        request.getRequestDispatcher("admin?page=gotoViewMerch").forward(request,response);
                        HttpSession sess = request.getSession();
                        sess.setAttribute("sessUserData",user);
                    }
                    else{
                        List<Merch> basketCartList = new UserService().getCartListByUserId(user.getId());
                        out.print("basketList size:"+basketCartList.size()+"<br/>");
                        HttpSession sess1 = request.getSession();
                        sess1.setAttribute("sessCartSize",basketCartList.size());
                        
                        HttpSession sess = request.getSession();
                        sess.setAttribute("sessUserData",user);
                        request.getRequestDispatcher("/index.jsp").forward(request,response);
                    }
                }
            
            }
            catch(Exception e){
                out.print("errs: "+e);
            }
            
        }
        else if(page.equalsIgnoreCase("logout")){
            try{
            HttpSession sess1 = request.getSession(false);
            sess1.removeAttribute("sessCartSize");
            
            HttpSession sess2 = request.getSession(false);
            sess2.removeAttribute("sessCartData");
            
            HttpSession sess = request.getSession(false);
            sess.removeAttribute("sessUserData");
            
            request.setAttribute("userData", null);
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request,response);
            }
            catch(Exception e){
                out.print("err: "+e);
            }
        }
//        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    
    
}

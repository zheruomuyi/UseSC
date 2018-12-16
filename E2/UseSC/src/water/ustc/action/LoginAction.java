package water.ustc.action;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginAction extends HttpServlet  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
    private String password;

//    public String handleLogin(){
//      //  System.out.println(this.userName + "|" + this.password);
//    	userName = this.getRequest().getParameter("userName");
//    	password = this.getRequest().getParameter("password");
//		this.getRequest().setAttribute("tip", "execute");
//		System.out.println("execute已调用。。。。");
//        if( !userName.isEmpty()&& !password.isEmpty()) {
//            return "success";
//        }else{
//            return "failure";
//        }
        public String handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        	userName = request.getParameter("userName");
            password = request.getParameter("password");

            if (!userName.isEmpty() && !password.isEmpty()) {
                return "success";
            } else {
                return "failure";
            }
        

    }

    //设置get和set方法

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

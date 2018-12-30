package water.ustc.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction extends HttpServlet  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7069283475845530826L;
    private UserBean ub ;
    public UserBean getUb() {
		return ub;
	}
	public void setUb(UserBean ub) {
		this.ub = ub;
	}
		public String handleLogin(HttpServletRequest request, HttpServletResponse response) 
        		throws ServletException, IOException, SQLException {
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
            if (!userName.isEmpty() && !password.isEmpty()) {
            	//UserBean ub = new UserBean(userName,password);
            	ub.setUserName(userName);
            	ub.setUserPass(password);
            	if(ub.signIn())  {
            		System.out.println("µÇÂ¼³É¹¦£¡");
            		return "success";
            	}         		
            	else
            		return "failure";
            } else {
                return "failure";
            }
    }



}

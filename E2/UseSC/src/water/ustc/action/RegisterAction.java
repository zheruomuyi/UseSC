package water.ustc.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterAction    extends HttpServlet {
    	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String userName;
        private String password;

        public String handleRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        	userName = request.getParameter("userName");
            password = request.getParameter("password");

            if (!userName.isEmpty() && !password.isEmpty()) {

              
                return "success";
            } else {
               
                return "failure";
            }
        }


	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}





}

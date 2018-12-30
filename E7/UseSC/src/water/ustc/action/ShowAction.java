package water.ustc.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAction extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7546179287959350999L;
	private String userId;
	private UserBean ub;
        public UserBean getUb() {
		return ub;
	}
	public void setUb(UserBean ub) {
		this.ub = ub;
	}
		public String handleShow(HttpServletRequest request, HttpServletResponse response) 
        		throws ServletException, IOException, SQLException {
        	userId = request.getParameter("userId");
        	ub.setUserId(userId);
            if (!userId.isEmpty()) {
            	if(ub.showObj())  {
            		System.out.println("¥Ú”°≥…π¶£°");
            		return "success";
            	}         		
            	else
            		return "failure";
            } else {
                return "failure";
            }
        }
}

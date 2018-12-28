package water.ustc.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sc.ustc.controller.confInfo;

public class ShowAction extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7546179287959350999L;
	private String userId;
        public String handleShow(HttpServletRequest request, HttpServletResponse response,confInfo confi) 
        		throws ServletException, IOException, SQLException {
        	userId = request.getParameter("userId");

            if (!userId.isEmpty()) {
            	UserBean ub = new UserBean(userId);
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

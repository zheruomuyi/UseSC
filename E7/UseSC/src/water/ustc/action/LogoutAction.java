package water.ustc.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

public class LogoutAction extends HttpServlet  {

	private static final long serialVersionUID = 2L;
	private String userName;
	private String userId;
    private String password;
    private UserBean ub = new UserBean();
    public UserBean getUb() {
		return ub;
	}

	public void setUb(UserBean ub) {
		this.ub = ub;
	}
        public String handleLogout(HttpServletRequest request, HttpServletResponse response) 
        		throws ServletException, IOException {
        	userName = request.getParameter("userName");
        	password = request.getParameter("userPass");
        	userId = request.getParameter("userId");
        	Map<String,String> sermap= new HashMap<String, String>();
        	sermap.put("userId", userId);
        	sermap.put("userPass", password);
        	sermap.put("userName", userName);
            if (!userId.isEmpty() && !password.isEmpty()&& !userId.isEmpty()) {
            	//BeanUtils.setProperty(UserBean, name, value);
            	
            	try {
					BeanUtils.populate(ub, sermap);
				} catch (IllegalAccessException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	if(ub.logout())  {
            		System.out.println("×¢Ïú³É¹¦£¡");
            		return "success";
            	}         		
            	else
            		return "failure";
            } else {
                return "failure";
            }
    }

}

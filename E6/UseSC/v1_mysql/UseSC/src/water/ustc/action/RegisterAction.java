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

import sc.ustc.controller.confInfo;

public class RegisterAction    extends HttpServlet {

	private static final long serialVersionUID = 2L;
        public String handleRegister(HttpServletRequest request, HttpServletResponse response,confInfo confi) 
        		throws ServletException, IOException{
        	String userName = request.getParameter("userName");
        	String  password = request.getParameter("password");
        	String useremail = request.getParameter("userEmail");
        	String userAddress = request.getParameter("userAddress");
        	int userAge = Integer.parseInt(request.getParameter("userAge"));

            if (!userName.isEmpty() && !password.isEmpty()&& !useremail.isEmpty()) {   
            	//UserBean ub = new UserBean(userName,password,useremail);
            	UserInform um = new UserInform();
            	UserBean ub = new UserBean();
                um.setUserEmail(useremail);
                um.setUserAddress(userAddress);
                um.setUserAge(userAge);
                Map<String,String> sermap= new HashMap<String, String>();
            	sermap.put("userPass", password);
            	sermap.put("userName", userName);

                	try {
    					BeanUtils.populate(ub, sermap);
    					ub.setUserInfrom(um);
    				} catch (IllegalAccessException | InvocationTargetException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
                
            	if(ub.regIn())
            		return "success";
            	else
            		return "failure";
            } else {           
                return "failure";
            }
        }


}

package water.ustc.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import sc.ustc.controller.confInfo;

public class LoginAction extends HttpServlet  {

	private static final long serialVersionUID = 2L;
	private String userName;
    private String password;
        public String handleLogin(HttpServletRequest request, HttpServletResponse response,confInfo confi) 
        		throws ServletException, IOException, SQLException {
        	userName = request.getParameter("userName");
            password = request.getParameter("password");
            if (!userName.isEmpty() && !password.isEmpty()) {
            	//UserBean ub = new UserBean(userName,password);
            	UserBean ub = new UserBean();
                Map<String,String> sermap= new HashMap<String, String>();
            	sermap.put("userPass", password);
            	sermap.put("userName", userName);                	
            	try {
					BeanUtils.populate(ub, sermap);
				} catch (IllegalAccessException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	if(ub.signIn())  {
            		System.out.println("登录成功！");
            		return "success";
            	}         		
            	else
            		return "failure";
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

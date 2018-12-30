package water.ustc.action;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.sf.cglib.proxy.Enhancer;

public class UserBean {
	private String userId;
	private String userName;
	private String userPass;
	private UserInform userInform ;


	@SuppressWarnings("static-access")
	UserInform informProxy() {
		System.out.println("开始懒加载>>>>>>>>>");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserInform.class);
        return (UserInform) enhancer.create(UserInform.class, new LazyLoadProxy(this));
    }

	
	public boolean signIn() throws SQLException{		//负责处理登录业务		
		UserDAO ud = new UserDAO();
        ResultSet rs = ud.selectObj(this);	
        if (rs.next()) {
        	ud.closeDBConnection();
            return true;
        }
        return false;
		
	}
	public boolean showObj(){		//负责处理注册业务	
		UserDAO ud = new UserDAO();
		//试用懒加载
		boolean bl = false;
		try {
			bl = ud.showObj(this);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserInform um = informProxy();
		this.setUserInfrom(um);
		System.out.println(um.toString());
		System.out.println("懒加载完毕>>>>>>>>>");
		ud.closeDBConnection();
        return bl;
	}
	public boolean regIn(){		//负责处理注册业务	
		UserDAO ud = new UserDAO();
		boolean bl =ud.insertObj(this);
        return bl;
	}
	
	public boolean logout(){		//负责处理注册业务	
		UserDAO ud = new UserDAO();
		boolean bl = ud.deleteObj(this);
        return bl;
        
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserBean(String userId) throws SQLException{	
		this.userId = userId;
	}



	public  UserBean(String userName,String userPass){
		this.userName = userName;
		this.userPass = userPass;
	}
	
	public UserBean() {
		// TODO Auto-generated constructor stub
	}


	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}


	public UserInform getUserInfrom() {
		return userInform;
	}


	public void setUserInfrom(UserInform userInfrom) {
		this.userInform = userInfrom;
	}



}

package water.ustc.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class UserBean {
	private String userId;
	private String userName;
	private String userPass;
	public boolean signIn() throws SQLException{		//�������¼ҵ��
		
		boolean isValid = false;
		UserDAO db = new UserDAO();
		db.openDBConnection();
		String usern = null;
		String useri = null;
		String passw = null;	
		String sql = "select * from p_user where userName='"+this.userName+"' and password='"+this.userPass+"';";
		System.out.println(sql);
		ResultSet res = db.query(sql);	
		while (res.next()) {									
			useri = res.getString("userId");
			usern = res.getString("userName");
			passw = res.getString("password");
			System.out.println("�û�Id��"+useri+"�û�����"+usern+"���룺"+passw+"�ѵ�¼��֤��");
			isValid = true;
		}		
		new UserBean(usern,passw);
		System.out.println(isValid);		
		db.closeDBConnection();
		return isValid;
		
	}
	
	public boolean regIn(){		//������ע��ҵ��	
		boolean isValid = false;
		UserDAO db = new UserDAO();
		db.openDBConnection();
		try {
			this.setUserId(UserIdVerify());		
			String sql ="insert into p_user(userId,userName,password) values('"+this.userId+"','"+this.userName+"','"+this.userPass+"');";			
			isValid = db.insert(sql);		
			System.out.println(sql+isValid);
        }finally{
        	db.closeDBConnection();
		}
			return isValid;
	}
	public String UserIdVerify(){		//������ע��ҵ��	
		//Connection connection = null;
		UserDAO db = new UserDAO();
		db.openDBConnection();
		String sql1 = "select max(userId) from p_user;";
		ResultSet ui = db.query(sql1);
			int i;
			try {
				while (ui.next()) {			
					i = Integer.parseInt( ui.getString(1).substring(6));
					String usi = new DecimalFormat("0000").format(i+1);
					System.out.println(usi);
					setUserId("SA2018"+usi);
				}
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				db.closeDBConnection();
				
			}
		System.out.println(userId);
		return userId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public  UserBean(String userName,String userPass){
		this.userName = userName;
		this.userPass = userPass;
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
}

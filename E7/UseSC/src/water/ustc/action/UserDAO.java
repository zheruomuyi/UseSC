package water.ustc.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sc.ustc.dao.BaseDAO;
import sc.ustc.dao.Conversation;


public class UserDAO extends BaseDAO {
//	public UserDAO(){
//		this.setDriver("com.mysql.cj.jdbc.Driver");
//		this.setUrl("jdbc:mysql://127.0.0.1:3306/login?serverTimezone=GMT%2B8&characterEncoding=GBK");
//		this.setUserName("root");
//		this.setUserPassword("zzzzzz");
//	}
	public ResultSet selectObj(UserBean ub){
		rs = (ResultSet) Conversation.getObject(ub) ;
		return rs;
	}
	
	public boolean deleteObj(UserBean ub){
		return Conversation.deleteObject(ub);
	}
	
	public boolean insertObj(UserBean ub){
		return Conversation.insertObject(ub);
	}
	public boolean showObj(UserBean ub) {
		rs = (ResultSet) Conversation.showObject(ub) ;
		try {
			while(rs.next()){        		
				System.out.println("查找ID："+rs.getString(1));	
				System.out.println("对应的用户名："+rs.getString(2)+"密码："+rs.getString(3));	
//			Blob blob = rs.getBlob(4);
//			if(blob!=null){
//				byte[] getBytes = blob.getBytes(1, (int)blob.length());   			
//    			ObjectInputStream objInput = new ObjectInputStream(new ByteArrayInputStream(getBytes)); 			
//    			try{
//    				UserInform returnObj = (UserInform) objInput.readObject();
//    				System.out.println("邮箱："+returnObj.getUserEmail());
//    	            System.out.println("地址："+returnObj.getUserAddress());
//    	            System.out.println("年龄："+returnObj.getUserAge());
//					 }catch(EOFException e){
//				  }
			        
//			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public boolean update(String sql){			// boolean, 负责执行 sql 语句，并返回执行结果
		boolean b = false;
		try {
			Statement stm = con.createStatement();
			stm.execute(sql);
			b = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;		
	}
//	public ResultSet query(String sql){				//	: Object, 负责执行 sql 语句，并返回结果对象
//	try {
//		PreparedStatement stm = con.prepareStatement(sql);
//		rs = stm.executeQuery();
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//		return rs;			
//}

//	public boolean insert(String sql){				//: boolean, 负责执行 sql 语句，并返回执行结果
//		boolean b = false;
//		try {
//			Statement stm = con.createStatement();
//			stm.execute(sql);
//			b = true;
//			System.out.println(b);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return b;	
//	}
//	public boolean delete(String sql){				//: boolean, 负责执行 sql 语句，并返回执行结果
//		boolean b = false;
//		try {
//			Statement stm = con.createStatement();
//			stm.execute(sql);
//			b = true;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return b;	
//	}

//	@Override
//	public boolean insert(String arg0) {
//		// TODO Auto-generated method stub
//		return false;
//	}
}

package water.ustc.action;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sc.ustc.dao.BaseDAO;


public class UserDAO extends BaseDAO {
	public UserDAO(){
		this.setDriver("com.mysql.cj.jdbc.Driver");
		this.setUrl("jdbc:mysql://127.0.0.1:3306/login?serverTimezone=GMT%2B8&characterEncoding=GBK");
		this.setUserName("root");
		this.setUserPassword("zzzzzz");
	}

	public ResultSet query(String sql){				//	: Object, ����ִ�� sql ��䣬�����ؽ������
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			rs = stm.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return rs;			
	}
	
	public boolean insert(String sql){				//: boolean, ����ִ�� sql ��䣬������ִ�н��
		boolean b = false;
		try {
			stm = con.createStatement();
			stm.execute(sql);
			b = true;
			System.out.println(b);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;	
	}
	
	public boolean update(String sql){			// boolean, ����ִ�� sql ��䣬������ִ�н��
		boolean b = false;
		try {
			stm = con.createStatement();
			stm.execute(sql);
			b = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;		
	}
	
	public boolean delete(String sql){				//: boolean, ����ִ�� sql ��䣬������ִ�н��
		boolean b = false;
		try {
			stm = con.createStatement();
			stm.execute(sql);
			b = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;	
	}
}

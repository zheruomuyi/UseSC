package sc.ustc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseDAO {
	protected String driver;//�����ݿ������ࣩ
	protected String url;//�����ݿ����·������
	protected String userName;//�����ݿ��û�������
	protected String userPassword;//�����ݿ��û����룩
	protected Connection con;
	protected PreparedStatement stm;
	protected ResultSet rs;
//	public abstract  ResultSet query(String sql);				//	: Object, ����ִ�� sql ��䣬�����ؽ������
//	public abstract boolean insert(String sql);				//: boolean, ����ִ�� sql ��䣬������ִ�н��
//	public abstract boolean update(String sql);			// boolean, ����ִ�� sql ��䣬������ִ�н��
//	public abstract boolean delete(String sql);				//: boolean, ����ִ�� sql ��䣬������ִ�н��
	public Connection openDBConnection(){		//:Connection, ��������ݿ�����
		try {
			Class.forName(driver); 
			con = DriverManager.getConnection(url, userName, userPassword);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return con;                      //����ֵ��һ��Connection����	
	}
	public boolean closeDBConnection(){ 		//: boolean, ����ر����ݿ�����
		
		if (rs != null) {                      //closeConnection�������ã��ر��ѽ��������ӣ������Դ     
			try {                              //�����(ResultSet)�������в�ѯ������ص�һ�ֶ��󣬿���˵�������һ���洢��ѯ����Ķ���
				rs.close();                    //��Statement()���� �� Java ִ�����ݿ������һ����Ҫ�������������Ѿ��������ݿ����ӵĻ����ϣ������ݿⷢ��Ҫִ�е�SQL��䡣
				                                //     Statement��������ִ�в��������ļ�SQL��䡣
			} catch (SQLException e) {                 
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (stm != null) {
			try {
				stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;	
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}

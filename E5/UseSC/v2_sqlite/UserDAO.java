package water.ustc.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserDAO  {
	protected Connection con;
	protected Statement stm;
	protected ResultSet rs;
	public static Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection("jdbc:sqlite:C:/Users/Administrator/login.db");
        
    }
	public ResultSet query(String sql){				//	: Object, ����ִ�� sql ��䣬�����ؽ������
		
		ResultSet rs = null;	
		Connection connection = null;
		try {
			connection = createConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			 statement.setQueryTimeout(30);
			 rs = statement.executeQuery();
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return rs;			
	}
	

	public boolean insert(String sql){				//: boolean, ����ִ�� sql ��䣬������ִ�н��
		Connection connection = null;
		int b=0;
		try {
			connection = createConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(connection!=null){
			try {	
				PreparedStatement statement1 = connection.prepareStatement(sql);
				b=statement1.executeUpdate();	
				 System.out.println("yanzheng"+b);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			 
		}
		if(b!=0)
			return true;
		else
			return false;
			 
		
			
	}
	
	public boolean update(String sql){			// boolean, ����ִ�� sql ��䣬������ִ�н��
		Connection connection = null;
		boolean b = false;
		try {
			connection = createConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			b = statement.execute();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;	
	}
	
	public boolean delete(String sql){				//: boolean, ����ִ�� sql ��䣬������ִ�н��
		Connection connection = null;
		boolean b = false;
		try {
			connection = createConnection();
			 Statement statement = connection.createStatement();
			b = statement.execute(sql);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;	
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
}

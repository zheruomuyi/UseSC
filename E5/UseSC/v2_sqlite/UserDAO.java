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
	public ResultSet query(String sql){				//	: Object, 负责执行 sql 语句，并返回结果对象
		
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
	

	public boolean insert(String sql){				//: boolean, 负责执行 sql 语句，并返回执行结果
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
	
	public boolean update(String sql){			// boolean, 负责执行 sql 语句，并返回执行结果
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
	
	public boolean delete(String sql){				//: boolean, 负责执行 sql 语句，并返回执行结果
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
	public boolean closeDBConnection(){ 		//: boolean, 负责关闭数据库连接
		
		if (rs != null) {                      //closeConnection方法作用，关闭已建立的链接，清空资源     
			try {                              //结果集(ResultSet)是数据中查询结果返回的一种对象，可以说结果集是一个存储查询结果的对象
				rs.close();                    //　Statement()方法 是 Java 执行数据库操作的一个重要方法，用于在已经建立数据库连接的基础上，向数据库发送要执行的SQL语句。
				                                //     Statement对象，用于执行不带参数的简单SQL语句。
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

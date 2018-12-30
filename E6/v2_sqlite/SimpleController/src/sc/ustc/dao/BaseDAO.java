package sc.ustc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseDAO {
	protected String driver;//（数据库驱动类）
	protected String url;//（数据库访问路径），
	protected String userName;//（数据库用户名），
	protected String userPassword;//（数据库用户密码）
	protected Connection con;
	protected PreparedStatement stm;
	protected ResultSet rs;
//	public abstract  ResultSet query(String sql);				//	: Object, 负责执行 sql 语句，并返回结果对象
//	public abstract boolean insert(String sql);				//: boolean, 负责执行 sql 语句，并返回执行结果
//	public abstract boolean update(String sql);			// boolean, 负责执行 sql 语句，并返回执行结果
//	public abstract boolean delete(String sql);				//: boolean, 负责执行 sql 语句，并返回执行结果
	public Connection openDBConnection(){		//:Connection, 负责打开数据库连接
		try {
			Class.forName(driver); 
			con = DriverManager.getConnection(url, userName, userPassword);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return con;                      //返回值是一个Connection对象	
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

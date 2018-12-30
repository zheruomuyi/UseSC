package sc.ustc.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Conversation {
	protected static Connection con;
	protected static PreparedStatement stm;
	protected static ResultSet rs;
	static proInfo pf = Configuration.getProConfig();
	static Map<String, String> jpros = new HashMap<String, String>();
	static class_pro_table cpro_table = pf.getCpro_table();
	static Map<String, class_pro_colu> cpro_colus = pf.getCpro_colus();	
	static String tab = cpro_table.getTable();
	
	public static ResultSet loadObject(Object  obj) throws Exception {
		// TODO Auto-generated method stub
		Class<?> cla = obj.getClass();
		con = openDBConnection();        
    	Field field;
		field = cla.getDeclaredField("userId");
		field.setAccessible(true);
        String fieldString = (String) field.get(obj);
		String sqlString = "select * from p_user where UserId = '"+fieldString+"'";
        PreparedStatement ps = con.prepareStatement(sqlString);
        System.out.println(sqlString); 
        ResultSet rs = ps.executeQuery();
        
        	
        	return rs;
        
	
        
        
	}
	public static Object  getObject(Object  obj) {
		Class<?> cla = obj.getClass();
		
        String str = "select * from "+tab+" where userName = ? and userPass = ?";
        
		Field usern;
		Field userp;
		try {
			// 连接DB
			con = openDBConnection();
			stm = con.prepareStatement(str);
			usern = cla.getDeclaredField("userName");
			usern.setAccessible(true);
            String usernString = (String) usern.get(obj);
            userp = cla.getDeclaredField("userPass");
            userp.setAccessible(true);
            String userpString = (String) userp.get(obj);
			stm.setString(1, usernString);
			stm.setString(2, userpString);
			return stm.executeQuery();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        closeDBConnection();
		return null;
				
	}
	public static Object  showObject(Object  obj) {
		Class<?> cla = obj.getClass();
		
        String str = "select * from "+tab+" where userId = ?";        
		Field usern;
		try {
			// 连接DB
			con = openDBConnection();
			PreparedStatement stm = con.prepareStatement(str);
			usern = cla.getDeclaredField("userId");
			usern.setAccessible(true);
            String usernString = (String) usern.get(obj);
			stm.setString(1, usernString);
			return stm.executeQuery();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        closeDBConnection();
		return null;
				
	}
	public static boolean  deleteObject(Object  obj) {
		Class<?> cla = obj.getClass();
        Field id;
		try {
			id = cla.getDeclaredField("userId");
			id.setAccessible(true);
		    String idString = (String) id.get(obj);   
		    con = openDBConnection();
            // delete
            String sql = "delete from " + tab + " where " + cpro_colus.get("userId").getColumn() + " = "+"'"+idString+"'";
            System.out.println(sql);
            PreparedStatement ps = con.prepareStatement(sql);
            return ps.executeUpdate() == 1;

		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return true;
	}
	public Object  updateObject(Object obj){
		
		
		return obj;
		
	}
	public static boolean  insertObject(Object obj){
		Class<?> cla = obj.getClass();
		String idString = null;
        Field name;
        Field pass;
        Field inform;
		try {
			String sql = "select max(userId) from p_user";
			//PreparedStatement ps = con.prepareStatement(sql);
			con = openDBConnection();
			stm = con.prepareStatement(sql);
			ResultSet rs =  stm.executeQuery();
		//	(ResultSet) getObject(obj) ;
			while (rs.next()) {			
		
				int i;
				if(rs.getString(1)!=null){
					String str= rs.getString(1).substring(6);
					i = Integer.parseInt(str);
				}			
				else					
					i = 0;	
				String usi = new DecimalFormat("0000").format(i+1);		
				idString = "SA2018"+usi;
		//		System.out.println(idString);
			}
			
		    name = cla.getDeclaredField("userName");
		    name.setAccessible(true);
		    String nameString = (String) name.get(obj);   
		    pass = cla.getDeclaredField("userPass");
		    pass.setAccessible(true);
		    String passString = (String) pass.get(obj); 
		    inform = cla.getDeclaredField("userInform");
		    inform.setAccessible(true);
		    Object informF = (Object) inform.get(obj);  
//		    ByteArrayOutputStream out = new ByteArrayOutputStream();
//			ObjectOutputStream objOuts = new ObjectOutputStream(out);
//			objOuts.writeObject(informF);
//			final byte[] objBytes = out.toByteArray();
//			ByteArrayInputStream input = new ByteArrayInputStream(objBytes);
		    ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(arrayOutputStream);
            objectOutputStream.writeObject(informF);
            objectOutputStream.flush();
            byte[] data=arrayOutputStream.toByteArray();
 


            String sql1 = "insert into " + tab + "(" + cpro_colus.get("userId").getColumn() + ", "+cpro_colus.get("userName").getColumn()
            		+ ", "+cpro_colus.get("userPass").getColumn()+ ", "+cpro_colus.get("userInform").getColumn()+") values('"+idString+"','"
            		+nameString+"','"+passString+"', ?);";	
            System.out.println(sql1);
            PreparedStatement ps1 = con.prepareStatement(sql1);
            
        //    ps1.setObject(1, informF);
            ps1.setBytes(1, data);
            arrayOutputStream.close();
            objectOutputStream.close();
        //    ps1.setBinaryStream(1, input, objBytes.length);
            
            return ps1.executeUpdate()  ==1;

		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
		
	}
//	public static Connection openDBConnection(){		//:Connection, 负责打开数据库连接
//
//		jpros = pf.getJpros();
//
//		try {
//			Class.forName(jpros.get("driver_class")); 
//			//System.out.println("这是连接"+jpros.get("driver_class"));
//			con = DriverManager.getConnection(jpros.get("url_path"), 
//					jpros.get("db_username"), jpros.get("db_userpassword"));
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return con;                      //返回值是一个Connection对象	
//	}
	public static Connection openDBConnection(){		//:Connection, 负责打开数据库连接

		jpros = pf.getJpros();

		try {
			Class.forName("org.sqlite.JDBC"); 
			//System.out.println("这是连接"+jpros.get("driver_class"));
			con = DriverManager.getConnection("jdbc:sqlite:C:/Users/Administrator/login.db");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return con;                      //返回值是一个Connection对象	
	}
	public static boolean closeDBConnection(){ 		//: boolean, 负责关闭数据库连接
		
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

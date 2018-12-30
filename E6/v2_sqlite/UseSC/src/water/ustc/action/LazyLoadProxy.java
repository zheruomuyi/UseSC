package water.ustc.action;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.sql.ResultSet;

import net.sf.cglib.proxy.LazyLoader;
import sc.ustc.dao.Conversation;


public class LazyLoadProxy implements LazyLoader {
	private UserBean userBean;
	@Override
	public Object loadObject() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("正在进行懒加载>>>>>>>>>");
		
//		byte[] blob = (byte[]) Conversation.loadObject(userBean);
		ResultSet rs = Conversation.loadObject(userBean);
		while(rs.next()){
			
			byte[] blob = rs.getBytes("userInform");
			ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(blob);
            ObjectInputStream objectInputStream=new ObjectInputStream(byteArrayInputStream);
            
            UserInform returnObj =(UserInform)(objectInputStream.readObject());
            byteArrayInputStream.close();
            objectInputStream.close();

//			if(blob!=null){
//				//byte[] getBytes = blob.getBytes(1, (int)blob.length());   			
//    			ObjectInputStream objInput = new ObjectInputStream(new ByteArrayInputStream(blob)); 			
//    			try{
//    				UserInform returnObj = (UserInform) objInput.readObject();
//    				System.out.println("邮箱："+returnObj.getUserEmail());
//    	            System.out.println("地址："+returnObj.getUserAddress());
//    	            System.out.println("年龄："+returnObj.getUserAge());
//					 }catch(EOFException e){
//				  }
//	            
//			}

			return returnObj;
		  
		}
		return null;

	}
	 public LazyLoadProxy(UserBean userBean) {

	        this.userBean = userBean;
	    }

}

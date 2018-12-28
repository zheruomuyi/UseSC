package water.ustc.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Blob;

import net.sf.cglib.proxy.LazyLoader;
import sc.ustc.dao.Conversation;


public class LazyLoadProxy implements LazyLoader {
	private UserBean userBean;
	@Override
	public Object loadObject() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("正在进行懒加载>>>>>>>>>");
		UserInform um = null;
		Blob blob = Conversation.loadObject(userBean);
		if(blob!=null){			
			InputStream is=blob.getBinaryStream();                //获取二进制流对象
			BufferedInputStream bis=new BufferedInputStream(is);    //带缓冲区的流对象			
			byte[] buff=new byte[(int) blob.length()];
			while(-1!=(bis.read(buff, 0, buff.length))){            //一次性全部读到buff中
				ObjectInputStream in=new ObjectInputStream(new ByteArrayInputStream(buff));
			//	System.out.println(in.readObject());
				                 //读出对象
				try{
					um=(UserInform)in.readObject();  
					 }catch(EOFException e){
				  }
				return um;
			}

		}
		
		return null;
	}
	 public LazyLoadProxy(UserBean userBean) {

	        this.userBean = userBean;
	    }

}

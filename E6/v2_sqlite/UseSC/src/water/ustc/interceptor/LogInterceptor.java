package water.ustc.interceptor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import sc.ustc.controller.confInfo;

public class LogInterceptor extends HttpServlet{
	
	/**
	 * 拦截器
	 */
	private static final long serialVersionUID = 2L;
	private static String actionName;
	private static String responseName;
	private static String preTime;
	private static String afterTime;


	
	public void preAction(HttpServletRequest request, HttpServletResponse response,confInfo confi) 
    		throws ServletException, IOException{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
  		this.setPreTime(df.format(new Date()));// new Date()为获取当前系统时间
  		String actionN = request.getServletPath().toString();
        String[] actionUrl = actionN.split("/");
        actionN = actionUrl[actionUrl.length - 1].substring(0,actionUrl[actionUrl.length - 1].lastIndexOf(".") );
        this.setActionName(actionN);
        System.out.println( "这是定位1"+LogInterceptor.actionName);
	}

	public void afterAction(HttpServletRequest request, HttpServletResponse response,confInfo confi,String result)
			throws ServletException, IOException{
		this.setResponseName(result);
		System.out.println(this.getResponseName());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
  		this.setAfterTime(df2.format(new Date())); // new Date()为获取当前系统时间 		
  		String str = "F:/PDF/J2EE/Experiment/UseSC/WebRoot/log.xml";
  		try {
  			 System.out.println( "这是定位2"+LogInterceptor.actionName);
			writer(str,this.getActionName(),this.getPreTime(),this.getAfterTime(),this.getResponseName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
    public void writer(String path,String actionName,String stime,String etime,String result) throws Exception {
        File file=new File(path);
        Document read=null;
        Element root=null;
        //如果文件存在  就获得它的根节点 
        if(file.exists()){
            SAXReader reader=new SAXReader();
            read = reader.read(path);
            //得到根节点
            root = read.getRootElement();
        }else {//如果文件不存在
            read = DocumentHelper.createDocument();
            //创建一个根节点
            root=read.addElement("log");
        }
        //在根节点下创建一个新节点action节点
        Element action = root.addElement("action");
        //在student下创建节点  并给它们赋值
        Element name = action.addElement("name");
        name.setText(actionName);
        Element sTime = action.addElement("s-time");
        sTime.setText(stime);
        Element eTime = action.addElement("e-time");
        eTime.setText(etime);
        Element res = action.addElement("result");
        res.setText(result);
        //        创建一种输出格式    每个节点元素可自动换行
        OutputFormat outputFormat=OutputFormat.createPrettyPrint();
        outputFormat.setEncoding("UTF-8");
        XMLWriter xmlWriter=new XMLWriter(new FileWriter(file),outputFormat);//写入XML文件的位置 以及指定的格式
        xmlWriter.write(read);//开始写入XML文件   写入Document对象
        xmlWriter.close();
        System.out.println("写入成功！");
    }
	
	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		LogInterceptor.actionName = actionName;
	}

	public String getResponseName() {
		return responseName;
	}

	public void setResponseName(String responseName) {
		LogInterceptor.responseName = responseName;
	}

	public String getPreTime() {
		return preTime;
	}

	public void setPreTime(String preTime) {
		LogInterceptor.preTime = preTime;
	}

	public String getAfterTime() {
		return afterTime;
	}

	public void setAfterTime(String afterTime) {
		LogInterceptor.afterTime = afterTime;
	}
	
}

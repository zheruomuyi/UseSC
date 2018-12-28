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
	 * ������
	 */
	private static final long serialVersionUID = 2L;
	private static String actionName;
	private static String responseName;
	private static String preTime;
	private static String afterTime;


	
	public void preAction(HttpServletRequest request, HttpServletResponse response,confInfo confi) 
    		throws ServletException, IOException{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
  		this.setPreTime(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
  		String actionN = request.getServletPath().toString();
        String[] actionUrl = actionN.split("/");
        actionN = actionUrl[actionUrl.length - 1].substring(0,actionUrl[actionUrl.length - 1].lastIndexOf(".") );
        this.setActionName(actionN);
        System.out.println( "���Ƕ�λ1"+LogInterceptor.actionName);
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
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
  		this.setAfterTime(df2.format(new Date())); // new Date()Ϊ��ȡ��ǰϵͳʱ�� 		
  		String str = "F:/PDF/J2EE/Experiment/UseSC/WebRoot/log.xml";
  		try {
  			 System.out.println( "���Ƕ�λ2"+LogInterceptor.actionName);
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
        //����ļ�����  �ͻ�����ĸ��ڵ� 
        if(file.exists()){
            SAXReader reader=new SAXReader();
            read = reader.read(path);
            //�õ����ڵ�
            root = read.getRootElement();
        }else {//����ļ�������
            read = DocumentHelper.createDocument();
            //����һ�����ڵ�
            root=read.addElement("log");
        }
        //�ڸ��ڵ��´���һ���½ڵ�action�ڵ�
        Element action = root.addElement("action");
        //��student�´����ڵ�  �������Ǹ�ֵ
        Element name = action.addElement("name");
        name.setText(actionName);
        Element sTime = action.addElement("s-time");
        sTime.setText(stime);
        Element eTime = action.addElement("e-time");
        eTime.setText(etime);
        Element res = action.addElement("result");
        res.setText(result);
        //        ����һ�������ʽ    ÿ���ڵ�Ԫ�ؿ��Զ�����
        OutputFormat outputFormat=OutputFormat.createPrettyPrint();
        outputFormat.setEncoding("UTF-8");
        XMLWriter xmlWriter=new XMLWriter(new FileWriter(file),outputFormat);//д��XML�ļ���λ�� �Լ�ָ���ĸ�ʽ
        xmlWriter.write(read);//��ʼд��XML�ļ�   д��Document����
        xmlWriter.close();
        System.out.println("д��ɹ���");
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

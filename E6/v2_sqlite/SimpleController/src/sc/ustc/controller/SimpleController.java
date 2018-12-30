package sc.ustc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SimpleController")
public class SimpleController extends HttpServlet{	
	/** @Title: SimpleController 
	* @Package:  sc.ustc.controller
	* @Description: working as struts' filter
	* @author 刘瑾瑾 SA18225235 
	* @date 2018-12-12
	* @version V1.0 
		 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	response.setContentType("text/html,charset=utf-8");
    	String xmlPath = this.getServletContext().getRealPath("WEB-INF/classes/controller.xml");
    	String actionName = request.getServletPath().toString();
        String[] actionUrl = actionName.split("/");
        actionName = actionUrl[actionUrl.length - 1].substring(0,actionUrl[actionUrl.length - 1].lastIndexOf(".") );
       // System.out.println(actionName);
        confInfo confi = configAnalysis.getConfig(actionName, xmlPath);
    	Map<String, actionInfo> actionMap = confi.getActionInfos();
        //利用其 class 属性实例化所指向的类（Java 反射机制，Reflection），并执行指定的 method 方法。
        if(actionMap!=null&&!actionMap.isEmpty()){
        	actionInfo actionI = actionMap.get(actionName);
        	String classN = actionI.getActionClass();
            String methodN = actionI.getActionMethod();
            Map<String, interceptorInfo> interInfoMap = actionI.getInterInfos();
			
            try {
            	Class<?> cl = Class.forName(classN);
                Method m = cl.getDeclaredMethod(methodN, HttpServletRequest.class, HttpServletResponse.class,confInfo.class);
                String result = null;
            	if(interInfoMap!=null&&!interInfoMap.isEmpty()){
            		// 创建代理实例
                    Object clc = cglibProxy.getProxy(cl);
                    // 调用代理方法
                    result = (String) m.invoke(clc, request, response,confi);
    			}else {
                    result = (String) m.invoke(cl, request, response,confi);
                } 
                Map<String, resultInfo> resultI =actionI.getResultInfos();
                resultInfo resI = resultI.get(result);	
           //     String resultN = resI.getName();         
                String resT = resI.getType();
                String resV = resI.getValue();

                if (resT.equals("foward")) {
                	if(resV.endsWith("_view.xml")){
                		String resVs = resV.substring(0, resV.lastIndexOf("."));
                		
                		//生成浏览器可以执行的 html 页面 （可以参考 XSLT ）
                		 String xmlPathS = "F:/PDF/J2EE/Experiment/UseSC/WebRoot/"+resV;
                		 String xslPath = "F:/PDF/J2EE/Experiment/UseSC/WebRoot/"+resVs+".xsl";
                		 String htmlPath = "F:/PDF/J2EE/Experiment/UseSC/WebRoot/"+resVs+".html";
                		 String htmlFile = resVs+".html";
                	//	 System.out.println(xmlPathS+"/n"+xslPath+"/n"+htmlPath);
                		try {               			
                			xmlToHTML.translate(xmlPathS, xslPath, htmlPath);
                			System.out.println("XML转换成HTML成功！");
                		} catch (Exception e) {
                			System.out.println("XML转换成HTML失败："+e.getMessage());
                		}
                		request.getRequestDispatcher(htmlFile).forward(request, response);
                	}else
                    request.getRequestDispatcher(resV).forward(request, response);
                } else if (resT.equals("redirect")) {
                    response.sendRedirect(resV);
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else {
        	response.setContentType("text/html;charset=GBK");
            PrintWriter out = response.getWriter();
            out.println("action错误，没有响应！");
        }

	}

}

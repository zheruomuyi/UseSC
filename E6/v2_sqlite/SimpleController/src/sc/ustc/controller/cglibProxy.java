package sc.ustc.controller;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class cglibProxy {
	public static Object getProxy(Class<?> clazz) {

        CglibProxyX proxy = new CglibProxyX();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(proxy);
        return enhancer.create();
    }
}
class CglibProxyX implements MethodInterceptor {

    public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
    	//
    	
    	confInfo confi = (confInfo)args[2];
    	Map<String, interceptorInfo> interInfosMap = confi.getInterInfos();
     
    		String acName = confi.getActionName1();
    		actionInfo actioni = confi.getActionInfos().get(acName);
    	
    Map<String, interceptorInfo> interInfoMap = actioni.getInterInfos();
    	
    	for (String key : interInfoMap.keySet()) { 

			  interceptorInfo inf = interInfosMap.get(key);
			  String classN = inf.getIntercepterClass();
			  String predo = inf.getPredo();
			//  System.out.println(predo);
			  Class<?> cl = Class.forName(classN);
		      Method preMethod = cl.getDeclaredMethod(predo, HttpServletRequest.class, HttpServletResponse.class,confInfo.class);
		      preMethod.invoke(cl.newInstance(), (HttpServletRequest) args[0], (HttpServletResponse) args[1], confi);
		      
		
    	} 
    	String result = (String) proxy.invokeSuper(object, args);
    	for (String key : interInfoMap.keySet()) { 
			  interceptorInfo inf = interInfosMap.get(key);
			  String classN = inf.getIntercepterClass();
			  String afterdo = inf.getAfterdo();
			  Class<?> cl = Class.forName(classN);
		      Method afterMethod = cl.getDeclaredMethod(afterdo, HttpServletRequest.class, HttpServletResponse.class,confInfo.class,String.class);

		      
		      afterMethod.invoke(cl.newInstance(), (HttpServletRequest) args[0], (HttpServletResponse) args[1], confi,result);
  	} 
        return result;
    }
}

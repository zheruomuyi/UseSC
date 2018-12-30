package sc.ustc.controller;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class configAnalysis {
	/**
	 * 用DOM4J方法读取解析xml文件
	 * @author 刘瑾瑾SA18225235
	 */

	
    
    public static confInfo getConfig( String actionName1, String path)throws FileNotFoundException{
     	String xmlPath = path;
        String actionName = actionName1;
        confInfo confI = new confInfo();
        Map<String, actionInfo> actionMap = new HashMap<String, actionInfo>();
    	Map<String, resultInfo> resultInfos = new HashMap<String, resultInfo>();
    	Map<String, interceptorInfo> interInfos = new HashMap<String, interceptorInfo>();
    	Map<String, interceptorInfo> interRefInfos = new HashMap<String, interceptorInfo>();
        //dom4j 解析XML文件
        try{
    		SAXReader reader = new SAXReader();
    		Document doct = reader.read(xmlPath);	
    		
    		@SuppressWarnings("unchecked")
			List<Element>interList = doct.selectNodes("//interceptor");
            
            for (Element element2 : interList) {
            	interceptorInfo ii = new interceptorInfo();
            	String interN = element2.attributeValue("name");
          //  	System.out.println(interN);
            	ii.setIntercepterName(interN);
            	ii.setIntercepterClass(element2.attributeValue("class"));
            	ii.setPredo(element2.attributeValue("predo"));
            	ii.setAfterdo(element2.attributeValue("afterdo"));
            	interInfos.put(interN, ii);
            	
            }
            confI.setInterInfos(interInfos);
			@SuppressWarnings("unchecked")
			List<Element> actionList = doct.selectNodes("//action");
         //   System.out.println(actionList.isEmpty());
            for (Element element : actionList) {
            	actionInfo ai = new actionInfo();
            	String actionN = element.attributeValue("name");
            	if(actionN.equals(actionName)){
            		confI.setActionName1(actionN);
            		ai.setActionName(actionN);
    				ai.setActionMethod(element.attributeValue("method"));
    				ai.setActionClass(element.attributeValue("class"));
    				
    				@SuppressWarnings("unchecked")
					List<Element> interRefList = element.selectNodes("interceptor_ref");
                	
                	for (Element element3 : interRefList) {	
                		interceptorInfo iri = new interceptorInfo();
                		String interRefN = element3.attributeValue("name");
                    	iri.setIntercepterName(interRefN);
                    	interRefInfos.put(interRefN, iri);
                	}
                	ai.setInterInfos(interRefInfos);
    				@SuppressWarnings("unchecked")
    				
    				List<Element> resultList = element.selectNodes("result");
                	
                	for (Element element1 : resultList) {	
                		resultInfo ri = new resultInfo();
                		String resultName = element1.attributeValue("name");         		
    					ri.setName(resultName);
    					ri.setValue(element1.attributeValue("value"));
    					ri.setType(element1.attributeValue("type"));
    				
    					resultInfos.put(resultName, ri);
                	}
                	ai.setResultInfos(resultInfos);
    				actionMap.put(actionN, ai);
    				
            	}
            	confI.setActionInfos(actionMap);
            }
    	} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

        return confI;
    }

}

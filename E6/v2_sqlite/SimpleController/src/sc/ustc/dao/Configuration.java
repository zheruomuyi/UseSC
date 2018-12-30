package sc.ustc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Configuration {
	public static proInfo getProConfig(){
		proInfo proi = new proInfo();
		Map<String, String> jpros = new HashMap<String, String>();;
		//class_pro_table cpro_tables = null;
		Map<String, class_pro_colu> cpro_colus = new HashMap<>();;
		String str  ="F:/PDF/J2EE/Experiment/UseSC/WebRoot/or_mapping.xml";
	       try{
	    		SAXReader reader = new SAXReader();
	    		Document doct = reader.read(str);	
	    		Element root = doct.getRootElement();
	    		//Element student = (Element) root.elements("student").get(1);
				//Node jp = doct.selectSingleNode("/OR-Mapping/jdbc/property");
	    		Node ip = root.selectSingleNode("/OR-Mapping/jdbc");
				@SuppressWarnings("unchecked")
				List<Element>jpList =  ip.selectNodes("property");
	            for (Element element : jpList) {
	            	String jpN = null;
	            	jpN=	element.elementText("name");
	            	String jpV = element.elementText("value");	            	
	            	//System.out.println(jpN+jpV);            	
	            	jpros.put(jpN, jpV);
	          //  	put(jpN, jpV);
	            	proi.setJpros(jpros);
	            }
	            Node ctList = root.selectSingleNode("/OR-Mapping/class");    
	            	class_pro_table ct = new class_pro_table();
	            	String ctN = ((Element) ctList).attributeValue("name");
	            	ct.setName(ctN);
	            	ct.setTable(((Element) ctList).attributeValue("table"));
	          //  	System.out.println("classname"+ctN);
	            	proi.setCpro_table(ct);
	            	 @SuppressWarnings("unchecked")
					List<Element>ccList = ctList.selectNodes("property");
	            	 for (Element element1 : ccList) {
	 	            	class_pro_colu cc = new class_pro_colu();
	 	            	String ccN = element1.elementText("name");
	 	            //	System.out.println(" Ù–‘name"+ccN);
	 	            	cc.setName(ccN);
	 	            	cc.setColumn(element1.elementText("column"));
	 	            	cc.setType(element1.elementText("type"));
	 	            	cc.setLazy(Boolean.parseBoolean(element1.elementText("lazy")));
	 	            	cpro_colus.put(ccN, cc);
	 	         }    
	            proi.setCpro_colus(cpro_colus);         
	            
	    	} catch (DocumentException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
	       
		return proi;
		
	}
}

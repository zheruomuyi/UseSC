package sc.ustc.controller;



import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
 
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;


public class xmlToHTML {
	/**
	 * 将XML转换成HTML
	 * @throws Exception
	 */
	public static void translate(String xmlPath,String xslPath,String htmlPath) throws Exception{
		//创建XML的文件输入流
		FileInputStream fis=new FileInputStream(xmlPath);
		Source source=new StreamSource(fis);
		
		//创建XSL文件的输入流
		FileInputStream fis1=new FileInputStream(xslPath);
		Source template=new StreamSource(fis1);
		
		PrintStream stm=new PrintStream(new File(htmlPath));
		//讲转换后的结果输出到 stm 中即 F:\123.html
		Result result=new StreamResult(stm);
		//根据XSL文件创建准个转换对象
		Transformer transformer=TransformerFactory.newInstance().newTransformer(template);
		//处理xml进行交换
		transformer.transform(source, result); 
		//关闭文件流
		fis1.close();
		fis.close();
	}

}

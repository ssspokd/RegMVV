/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;

/**
 *
 * @author nikolaev
 */
public class edidConnect_db_cfg 
{
    private static final Logger LOG = Logger.getLogger(edidConnect_db_cfg.class.getName());

    private static final File files = null;
    public static String replaceAll(String src, String find, String replace) {
		StringBuilder sb = new StringBuilder(src);
		int from = 0;
		int start;
		while ((start = sb.indexOf(find, from)) != -1) {
			sb.replace(start, start + find.length(), replace);
			from = start + replace.length();
		}
 
		return sb.toString();
	}
  
    public static  synchronized void EditXmlFile(String url,String login, String password) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException
    {
        File xmlFile = new File("connect_db.cfg.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            //document.getDocumentElement().normalize();
            
            
            Node blog = document.getElementsByTagName("session-factory").item(0);    
            NodeList list = blog.getChildNodes();
            for(int i = 0; i < list.getLength(); i++)
            {
                Node node = list.item(i);
                if("property".equals(node.getNodeName()))
                {
                    Element element  = (Element)node;
                    int j =  element.getAttributes().getLength();
                    for(int k = 0; k < j; k++)
                    {                     
                        if("hibernate.connection.url".equals(element.getAttribute("name")))
                        {
                           node.setTextContent(url);
                        }
                        if("hibernate.connection.username".equals(element.getAttribute("name")))
                        {
                           node.setTextContent(login);
                        }
                        if("hibernate.connection.password".equals(element.getAttribute("name")))
                        {
                           node.setTextContent(password);
                        }
                        
                    }
                }   
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            DOMImplementation domImpl = document.getImplementation();
            DocumentType doctype = domImpl.createDocumentType("doctype","-//Hibernate/Hibernate Configuration DTD 3.0//EN", "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, doctype.getPublicId());
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
            StreamResult streamResult = new StreamResult(new File("connect_db.cfg.xml"));
            transformer.transform(domSource, streamResult);
            
    }
    
    public static void main(String[] args) throws IOException, FileNotFoundException, XMLStreamException, SAXException, ParserConfigurationException, TransformerConfigurationException, TransformerException
    {	
            File xmlFile = new File("connect_db.cfg.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
          
            
            
            Node blog = document.getElementsByTagName("session-factory").item(0);    
            
            NodeList list = blog.getChildNodes();
            for(int i = 0; i < list.getLength(); i++)
            {
                Node node = list.item(i);
                if("property".equals(node.getNodeName()))
                {
                    Element element  = (Element)node;
                    int j =  element.getAttributes().getLength();
                    for(int k = 0; k < j; k++)
                    {                     
                        if("hibernate.connection.url".equals(element.getAttribute("name")))
                        {
                           node.setTextContent("asd");
                        }
                        if("hibernate.connection.username".equals(element.getAttribute("name")))
                        {
                           node.setTextContent("asasd");
                        }
                        if("hibernate.connection.password".equals(element.getAttribute("name")))
                        {
                           node.setTextContent("asd");
                        }
                        
                    }
                }   
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMImplementation domImpl = document.getImplementation();
            DocumentType doctype = domImpl.createDocumentType("doctype","-//Hibernate/Hibernate Configuration DTD 3.0//EN", "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, doctype.getPublicId());
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("connect_db.cfg.xml"));
            transformer.transform(domSource, streamResult);
        }       
    
}
	 
   

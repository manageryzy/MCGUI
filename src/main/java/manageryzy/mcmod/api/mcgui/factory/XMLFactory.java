package manageryzy.mcmod.api.mcgui.factory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import java.nio.charset.Charset;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import manageryzy.mcmod.api.mcgui.dom.DOM;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import akini.unicode.UnicodeReader;

public abstract class XMLFactory {
	
	//Generate a node
	public abstract DOM GenerateDOM(String type);
	
	public DOM root;
	
	public DOM LoadXML(File file) throws DOMException, Throwable
	{
		String XML = "";
		String str;
		
		FileInputStream in = new FileInputStream(file);   
		
        BufferedReader br = new BufferedReader(new UnicodeReader(in, Charset.defaultCharset().name()));
        while ((str = br.readLine()) != null) {
        	XML+=str;
        }
        
        br.close();
        
		return this.LoadXML(XML);
	}
	
	public DOM LoadXML(String XmlString) throws DOMException, Throwable
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		StringReader stringReader = new StringReader(XmlString);
		InputSource is = new InputSource(stringReader);
		
		Document doc = db.parse(is);
		
		Node element = doc.getDocumentElement();
		
		return GenerateDOMTree(element,null);
	}
	
	
	private DOM GenerateDOMTree(Node e,DOM father) throws DOMException, Throwable
	{
		DOM d = GenerateDOM(e.getNodeName());
		
		if(e.getNodeName().equals("#text"))
		{
			d.addAttr("text", e.getTextContent());
		}
		
		NamedNodeMap attrs = e.getAttributes();
		
		if(attrs == null) return null;
		
		if(father != null)
		{
			father.addChild(d);
		}
		
		
		
		for(int i = 0;i<attrs.getLength();i++)
		{
			Node attr = attrs.item(i);
			
			d.addAttr(attr.getNodeName(), attr.getNodeValue());
		}
		
		for(int i = 0;i<e.getChildNodes().getLength();i++)
		{
			GenerateDOMTree(e.getChildNodes().item(i),d);
		}
		
		
		return d;
	}


}

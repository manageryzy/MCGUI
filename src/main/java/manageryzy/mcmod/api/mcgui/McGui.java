package manageryzy.mcmod.api.mcgui;

import org.w3c.dom.DOMException;

import manageryzy.mcmod.api.mcgui.debugger.Debugger;
import manageryzy.mcmod.api.mcgui.factory.XMLFactory;

public class McGui {
	
	public static XMLFactory xmlFactory = null;
	
	/**
	 * this is the entrance for preview
	 * @throws Throwable 
	 * @throws DOMException 
	 */
	public static void main(String[] args) throws DOMException, Throwable {
		new Debugger("Preview","test.xml");
	}
	
	public static int WindowHeight = 480,WindowWidth = 480;
}

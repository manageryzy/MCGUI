package manageryzy.mcmod.api.mcgui.debugger;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import manageryzy.mcmod.api.mcgui.McGui;
import manageryzy.mcmod.api.mcgui.dom.DOM;
import manageryzy.mcmod.api.mcgui.factory.XMLFactoryDebug;

import org.w3c.dom.DOMException;

public class Debugger extends Frame {


	private static final long serialVersionUID = 1L;
	
	DOM WindowDom = null;
	static Debugger This;
	
	
	public Debugger(String str,String file) throws DOMException, Throwable {
		super(str);
		This = this;
		
		this.setSize(400, 300);
		this.setLayout(null);

		this.setBackground(Color.black);

		this.setVisible(true);
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
			}
		});
		
		this.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				if(WindowDom!=null)
					WindowDom.OnResize(null);
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});
		
		McGui.xmlFactory = new XMLFactoryDebug();
		WindowDom = McGui.xmlFactory.LoadXML(new File(file));
		WindowDom.OnResize(null);
		
		This.repaint(1000);
		
	}
	
	
	@Override
	public void paint(Graphics g) {
		
		if(this.WindowDom != null)
			this.WindowDom.OnDraw((Object)g);
		
		super.paint(g);
	}
}

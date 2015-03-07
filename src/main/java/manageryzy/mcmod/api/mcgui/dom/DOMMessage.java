package manageryzy.mcmod.api.mcgui.dom;

/**
 * Message class for DOM
 * @author manageryzy@gmail.com
 */
public class DOMMessage {
	
	String messageType;
	long timestamp;
	int mouseX,mouseY;
	/**
	 * this field is used by specific message , usually to be null
	 */
	Object param;
	
	public DOMMessage(String MessageType,Object param,int MouseX,int MouseY) {
		this.messageType =  MessageType;
		this.timestamp = System.currentTimeMillis();
		this.mouseX = MouseX;
		this.mouseY = MouseY;
		this.param = param;
	}
	
	/**
	 * @return true if the message could be received no matter the element is focused 
	 */
	public boolean isGlobalMessage()
	{
		switch(this.messageType)
		{
		case "OnMouseDown":
		case "OnMouseUp":
		case "OnResize":
			return true;
		}
		return false;
	}
	
	public String getMessageType() {
		return messageType;
	}
	
	public int getMouseX() {
		return mouseX;
	}
	
	public int getMouseY() {
		return mouseY;
	}
	
	public Object getParam() {
		return param;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	/**
	 * OnKeyDown message
	 * @author manageryzy@gmail.com
	 */
	public class OnKeyDown extends DOMMessage
	{
		public OnKeyDown(int keycode,int MouseX,int MouseY) {
			super("OnKeyDown", keycode, MouseX, MouseY);
		}	
	}
	
	/**
	 * OnKeyUp message
	 * @author manageryzy@gmail.com
	 */
	public class OnKeyUp extends DOMMessage
	{
		public OnKeyUp(int keycode,int MouseX,int MouseY) {
			super("OnKeyUp", keycode, MouseX, MouseY);
		}
	}
	
	/**
	 * OnMouseDown message
	 * @author manageryzy@gmail.com
	 */
	public class OnMouseDown extends DOMMessage
	{
		public OnMouseDown(int MouseX,int MouseY) {
			super("OnMouseDown", null, MouseX, MouseY);
		}
	}
	
	/**
	 * OnMouseUp message
	 * @author manageryzy@gmail.com
	 */
	public class OnMouseUp extends DOMMessage
	{
		public OnMouseUp(int MouseX,int MouseY){
			super("OnMouseUp", null, MouseX, MouseY);
		}
	}
	
	/**
	 * OnResize event
	 * fired when calculating DOM size , not when the window size change!
	 * @author manageryzy@gmail.com
	 */
	public class OnResize extends DOMMessage
	{
		public OnResize(int MouseX,int MouseY)
		{
			super("OnResize", null, MouseX, MouseY);
		}
	}
}

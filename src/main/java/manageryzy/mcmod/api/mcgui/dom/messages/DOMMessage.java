package manageryzy.mcmod.api.mcgui.dom.messages;

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
	
	
}

package manageryzy.mcmod.api.mcgui.dom.messages;
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
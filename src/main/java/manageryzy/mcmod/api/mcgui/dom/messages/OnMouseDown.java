package manageryzy.mcmod.api.mcgui.dom.messages;
/**
 * OnMouseDown message
 * @author manageryzy@gmail.com
 */
public class OnMouseDown extends DOMMessage
{
	public OnMouseDown(int MouseX,int MouseY,int mouseButton) {
		super("OnMouseDown", mouseButton, MouseX, MouseY);
	}
}
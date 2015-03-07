package manageryzy.mcmod.api.mcgui.dom.messages;

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

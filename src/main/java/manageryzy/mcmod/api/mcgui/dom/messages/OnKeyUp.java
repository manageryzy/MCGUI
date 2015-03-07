package manageryzy.mcmod.api.mcgui.dom.messages;

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
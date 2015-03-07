package manageryzy.mcmod.api.mcgui.dom.messages;

/**
 * OnKeyType message
 * @author manageryzy@gmail.com
 */
public class OnKeyType extends DOMMessage
{
	public OnKeyType(int keycode,int MouseX,int MouseY) {
		super("OnKeyType", keycode, MouseX, MouseY);
	}
}
package manageryzy.mcmod.api.mcgui.dom.messages;
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
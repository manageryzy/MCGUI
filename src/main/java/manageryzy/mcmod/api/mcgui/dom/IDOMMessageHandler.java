package manageryzy.mcmod.api.mcgui.dom;

import manageryzy.mcmod.api.mcgui.dom.messages.DOMMessage;

public interface IDOMMessageHandler {
	/**
	 * called when the message or event fired
	 * @param msg  
	 * @param This just like this in javascript :D
	 * @return true if the message should distribute to the father layer
	 */
	public boolean onMessage(DOMMessage msg,DOM This);
}

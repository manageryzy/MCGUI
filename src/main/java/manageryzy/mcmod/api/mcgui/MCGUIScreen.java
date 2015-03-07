package manageryzy.mcmod.api.mcgui;

import java.io.IOException;
import java.util.HashMap;

import manageryzy.mcmod.api.mcgui.dom.DOM;
import manageryzy.mcmod.api.mcgui.dom.messages.OnKeyType;
import manageryzy.mcmod.api.mcgui.dom.messages.OnMouseUp;
import manageryzy.mcmod.api.mcgui.dom.messages.OnMouseDown;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.inventory.Container;

public class MCGUIScreen extends GuiScreen {

	HashMap <String,Container> ContainerMap; 
	
	DOM layout = null;
	
	public MCGUIScreen(DOM dom) {
		super();
		
		this.ContainerMap = new HashMap<String, Container>();
		layout = dom;

	}
	
	public void setLayout(DOM dom)
	{
		layout = dom;
	}
	
	public void addContainer(String key,Container c)
	{
		
		this.ContainerMap.put(key, c);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		layout.OnDraw(null);
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
		layout.postMessage((new OnKeyType(keyCode, 0, 0)));
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton)
			throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		
		layout.postMessage(new OnMouseDown(mouseX, mouseY, mouseButton));
	}
	
	@Override
	protected void mouseClickMove(int mouseX, int mouseY,
			int clickedMouseButton, long timeSinceLastClick) {
		super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
		//TODO: mouse click move ; key down ;key up
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		
		layout.postMessage(new OnMouseUp(mouseX, mouseY));
	}
	
}

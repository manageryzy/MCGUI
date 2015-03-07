package manageryzy.mcmod.api.mcgui;

import java.io.IOException;
import java.util.HashMap;

import manageryzy.mcmod.api.mcgui.dom.DOM;
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
		
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton)
			throws IOException {
		// TODO Auto-generated method stub
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	@Override
	protected void mouseClickMove(int mouseX, int mouseY,
			int clickedMouseButton, long timeSinceLastClick) {
		// TODO Auto-generated method stub
		super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		// TODO Auto-generated method stub
		super.mouseReleased(mouseX, mouseY, state);
	}
}

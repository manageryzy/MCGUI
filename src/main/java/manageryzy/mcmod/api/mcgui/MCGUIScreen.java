package manageryzy.mcmod.api.mcgui;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import manageryzy.mcmod.api.mcgui.dom.DOM;
import manageryzy.mcmod.api.mcgui.dom.messages.OnKeyType;
import manageryzy.mcmod.api.mcgui.dom.messages.OnMouseDown;
import manageryzy.mcmod.api.mcgui.dom.messages.OnMouseUp;
import manageryzy.mcmod.api.mcgui.draw.BitmapManager;
import manageryzy.mcmod.api.mcgui.factory.XMLFactoryRelease;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.w3c.dom.DOMException;

public class MCGUIScreen extends GuiScreen {

	HashMap <String,Container> ContainerMap; 
	
	DOM layout = null;
	boolean doesPauseGame;
	
	public MCGUIScreen(String LayoutFilename,boolean doesPauseGame) throws DOMException, Throwable {
		super();
		this.doesPauseGame = doesPauseGame;
		
		if(McGui.xmlFactory==null)
			McGui.xmlFactory = new XMLFactoryRelease();
		
		this.ContainerMap = new HashMap<String, Container>();
	
		layout = McGui.xmlFactory.LoadXML(new File(LayoutFilename));
		
		McGui.WindowHeight = this.height;
		McGui.WindowWidth = this.width;
		
		layout.OnResize(null);
		
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return doesPauseGame;
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
	
		layout.OnDraw(this);
		super.drawScreen(mouseX, mouseY, partialTicks);
		
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
	
	public void drawRect(int x,int y, int w,int h,int r,int g,int b,int a)
	{
		int color = a & 0xFF;
		color = color << 8;
		color = color | (r & 0xFF);
		color = color << 8;
		color = color | (g & 0xFF);
		color = color << 8;
		color = color | (b & 0xFF);
		Gui.drawRect(x, y, x + w , y + h, color);
	}
	
	public void drawText(int x,int y, int w,int r,int g,int b,int a,String text)
	{
		int color = a & 0xFF;
		color = color << 8;
		color = color | (r & 0xFF);
		color = color << 8;
		color = color | (g & 0xFF);
		color = color << 8;
		color = color | (b & 0xFF);
		this.fontRendererObj.drawSplitString(text, x , y , w , color);
	}
	
	public void drawBitmap(int x,int y,int w,int h,int textureX,int textureY,String bitmap)
	{		
		ResourceLocation rl = BitmapManager.bitmapMap.get(bitmap);
		if(rl!=null)
		{
			this.mc.getTextureManager().bindTexture(rl);
			this.drawTexturedModalRect(x, y, textureX, textureY, w, h);
		}

	}
	
}

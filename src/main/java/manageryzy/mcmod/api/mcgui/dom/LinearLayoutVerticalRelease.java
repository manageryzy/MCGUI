package manageryzy.mcmod.api.mcgui.dom;

import java.io.IOException;

import manageryzy.mcmod.api.mcgui.MCGUIScreen;

public class LinearLayoutVerticalRelease extends LinearLayoutVertical {

	@Override
	public void Rect(Object obj, int x, int y, int w, int h, int r, int g,
			int b, int a) {
		MCGUIScreen src = (MCGUIScreen) obj;
		src.drawRect(x, y, w, h, r, g, b, a);
	}

	@Override
	public void RectBitmap(Object obj, int x, int y, int w, int h, String bitmap)
			throws IOException {
		MCGUIScreen src = (MCGUIScreen) obj;
		src.drawBitmap(x, y, w, h, x, y,bitmap);
	}

	@Override
	public void DrawText(Object obj, String str, int x, int y, int size, int r,
			int g, int b, int a) {
		MCGUIScreen src = (MCGUIScreen) obj;
		src.drawText(x, y, size, r, g, b, a, str);
	}

}

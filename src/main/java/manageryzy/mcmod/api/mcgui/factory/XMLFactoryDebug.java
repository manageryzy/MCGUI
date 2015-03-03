package manageryzy.mcmod.api.mcgui.factory;

import manageryzy.mcmod.api.mcgui.dom.DOM;
import manageryzy.mcmod.api.mcgui.dom.DOMDebug;
import manageryzy.mcmod.api.mcgui.dom.LinearLayoutHorizontalDebug;
import manageryzy.mcmod.api.mcgui.dom.LinearLayoutVerticalDebug;
import manageryzy.mcmod.api.mcgui.dom.RelativeLayoutDebug;
import manageryzy.mcmod.api.mcgui.logger.Logger;

public class XMLFactoryDebug extends XMLFactory{
	@Override
	public DOM GenerateDOM(String type) {
		switch(type)
		{
		case "RelativeLayout":
			return new RelativeLayoutDebug();
		case "LinearLayoutVertical":
			return new LinearLayoutVerticalDebug();
		case "LinearLayoutHorizontal":
			return new LinearLayoutHorizontalDebug();
		}
		
		Logger.Log("Element "+ type + " can not be resolved!");
		
		return new DOMDebug();
	}
}

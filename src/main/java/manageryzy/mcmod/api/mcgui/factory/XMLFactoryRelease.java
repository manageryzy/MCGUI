package manageryzy.mcmod.api.mcgui.factory;

import manageryzy.mcmod.api.mcgui.dom.DOM;
import manageryzy.mcmod.api.mcgui.dom.DOMRelease;
import manageryzy.mcmod.api.mcgui.dom.LableRelease;
import manageryzy.mcmod.api.mcgui.dom.LinearLayoutHorizontalRelease;
import manageryzy.mcmod.api.mcgui.dom.LinearLayoutVerticalRelease;
import manageryzy.mcmod.api.mcgui.dom.RelativeLayoutRelease;
import manageryzy.mcmod.api.mcgui.logger.Logger;

public class XMLFactoryRelease extends XMLFactory {

	@Override
	public DOM GenerateDOM(String type) {
		switch(type)
		{
		case "RelativeLayout":
			return new RelativeLayoutRelease();
		case "LinearLayoutVertical":
			return new LinearLayoutVerticalRelease();
		case "LinearLayoutHorizontal":
			return new LinearLayoutHorizontalRelease();
		case "#text":
		case "Lable":
			return new LableRelease(); 
		}
		
		Logger.Log("Element "+ type + " can not be resolved!");
		
		return new DOMRelease();
	}

}

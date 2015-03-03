package manageryzy.mcmod.api.mcgui.factory;

import manageryzy.mcmod.api.mcgui.dom.DOM;
import manageryzy.mcmod.api.mcgui.dom.DOMDebug;
import manageryzy.mcmod.api.mcgui.dom.RelativeLayoutDebug;

public class XMLFactoryDebug extends XMLFactory{
	@Override
	public DOM GenerateDOM(String type) {
		switch(type)
		{
		case "RelativeLayout":
			return new RelativeLayoutDebug();
		}
		return new DOMDebug();
	}
}

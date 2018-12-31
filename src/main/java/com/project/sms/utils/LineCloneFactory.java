package com.project.sms.utils;

import com.project.sms.entities.order.ILine;

public class LineCloneFactory {
	
	public ILine getClone(ILine cartLine) {
		return cartLine.makeCopy();
	}
}

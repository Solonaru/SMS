package com.project.sms.utils;

import com.project.sms.entities.lines.ILine;

public class LineCloneFactory {

	public ILine getClone(ILine line) {
		return line.makeCopy();
	}
}

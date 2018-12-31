package com.project.sms.entities.lines;

import org.springframework.stereotype.Component;

@Component
public class LineFactory {

	public ILine createLine(ILineAbstractFactory laf) {
		return laf.createLine();
	}

}

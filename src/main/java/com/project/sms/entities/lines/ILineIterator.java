package com.project.sms.entities.lines;

import java.util.Iterator;

public interface ILineIterator {

	public Iterator<? extends ILine> createIterator();

}

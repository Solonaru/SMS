package com.project.sms.utils;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

@Component
public class DisplayData {
	private static final Logger LOGGER = Logger.getLogger(DisplayData.class.getName());

	public DisplayData() {

	}

	public void printInfo(String messagge) {
		LOGGER.info(messagge);
	}

	public void printError(String messagge) {
		LOGGER.severe(messagge);
	}

	public void printCrudInfo() {
		String methodName = getMethodName();

		switch (methodName.substring(0, 3)) {
		case "get":
			LOGGER.info("[" + methodName + "] Find all " + methodName.substring(3).toLowerCase());
			break;
		case "upd":
			LOGGER.info("[" + methodName + "] Update " + methodName.substring(6).toLowerCase());
			break;
		case "ins":
			LOGGER.info("[" + methodName + "] Add " + methodName.substring(6).toLowerCase());
		default:
			LOGGER.warning("Unknown CRUD operation.");
		}
	}

	public void printCrudInfo(Integer id) {
		String methodName = getMethodName();

		switch (methodName.substring(0, 3)) {
		case "fin":
			LOGGER.info("[" + methodName + "] Find " + methodName.substring(4, methodName.indexOf("ById")).toLowerCase()
					+ " with id " + id);
			break;
		case "del":
			LOGGER.info(
					"[" + methodName + "] Delete " + methodName.substring(6).toLowerCase() + " with id " + id);
			break;
		default:
			LOGGER.warning("Unknown CRUD operation.");
		}
	}

	private String getMethodName() {
		return Thread.currentThread().getStackTrace()[3].getMethodName();
	}
}

package com.project.sms.entities.employee;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class RightDeserializer extends JsonDeserializer<IRight> {

	public IRight deserialize(JsonParser jp, DeserializationContext context) throws IOException {
		ObjectMapper mapper = (ObjectMapper) jp.getCodec();
		ObjectNode root = mapper.readTree(jp);
		/* write you own condition */
//        if (true) {
//            return mapper.readValue(root.toString(), Admin.class);
//        }

		return mapper.readValue(root.toString(), OperatorProducts.class);
	}
}
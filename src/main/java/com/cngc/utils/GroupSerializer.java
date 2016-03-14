package com.cngc.utils;

import java.io.IOException;

import com.cngc.pm.model.Group;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class GroupSerializer extends JsonSerializer<Group> {

	@Override
	public void serialize(Group group, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		// TODO Auto-generated method stub
		jgen.writeStartObject();  
        jgen.writeNumberField("groupid", group.getId());  
        jgen.writeStringField("groupName", group.getGroupName());
        if(group.getParentGroup() ==null) {
        	jgen.writeStringField("parentName", "");
       	 	jgen.writeNumberField("parentId", 0);
        } else {
        	 jgen.writeStringField("parentName", group.getParentGroup().getGroupName());
        	 jgen.writeNumberField("parentId", group.getParentGroup().getId());
        }
        jgen.writeStringField("explain", group.getExplain());
        jgen.writeNumberField("sort", group.getOrder());
        jgen.writeEndObject(); 
	}

}

package com.cngc.utils;

import java.io.IOException;

import com.cngc.pm.model.SysUser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class UserSerializer extends JsonSerializer<SysUser> {

	@Override
	public void serialize(SysUser user, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		// TODO Auto-generated method stub
		jgen.writeStartObject();  
        jgen.writeNumberField("userid", user.getId());  
        jgen.writeStringField("userName", user.getUsername());
        jgen.writeStringField("name", user.getName());
        if(user.getGroup()==null) {
        	jgen.writeNumberField("groupId", 0);
	        jgen.writeStringField("groupName", "");
        } else {
	        jgen.writeNumberField("groupId", user.getGroup().getId());
	        jgen.writeStringField("groupName", user.getGroup().getGroupName());
        }
        jgen.writeNumberField("sort", user.getDepId());
        
        if(user.getMechId() == null) {
        	jgen.writeNumberField("priority", 20);
        	jgen.writeStringField("priorityCH", "低");
        } else {
        	jgen.writeNumberField("priority", user.getMechId());
        	if(user.getMechId() == 0) {
        		jgen.writeStringField("priorityCH", "高");
        	} else if(user.getMechId() == 10) {
        		jgen.writeStringField("priorityCH", "中");
        	} else if(user.getMechId() == 20) {
        		jgen.writeStringField("priorityCH", "低");
        	}
        }
        jgen.writeStringField("tel", user.getDepName());
        jgen.writeStringField("mechName", user.getMechName());
        jgen.writeEndObject(); 
	}

}

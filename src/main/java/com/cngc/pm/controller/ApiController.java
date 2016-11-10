package com.cngc.pm.controller;

import java.text.ParseException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class ApiController {

	@RequestMapping(value="/test",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String test() throws ParseException {
		System.out.println("/api/test");
		return "sucess";
	}
}

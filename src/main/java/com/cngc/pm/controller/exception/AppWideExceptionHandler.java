package com.cngc.pm.controller.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import static com.cngc.utils.Constants.STREAM_OPERATE_LOG;

@ControllerAdvice
public class AppWideExceptionHandler {

	@ExceptionHandler(Exception.class)//可以直接写@EceptionHandler，IOExeption继承于Exception
	public ModelAndView allExceptionHandler(Exception ex){
		ModelAndView modelAndView = new ModelAndView("generic_exception");
		
		STREAM_OPERATE_LOG.info("系统出现异常：",ex);
	    modelAndView.addObject("errMsg", ex.getMessage());
	    return modelAndView;
	}
}

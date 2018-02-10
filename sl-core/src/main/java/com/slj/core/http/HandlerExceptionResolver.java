package com.slj.core.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.slj.core.base.Model;

/**
 * @author tingis13
 * @date 2014-2-22
 * @version 1.0
 */
public interface HandlerExceptionResolver {
	ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Model model, Exception e);
}

package com.slj.core.http;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.slj.core.base.Model;
import com.slj.core.exception.MarkException;


/**
 * @author tingis13
 * @date 2013-10-16
 * @version 1.0
 */
public interface ModelResolver {
	public abstract Model resolveModel(
			HttpServletRequest httpservletrequest, HttpServletResponse response, Locale locale,
			IdResolver idResolver) throws MarkException;
}

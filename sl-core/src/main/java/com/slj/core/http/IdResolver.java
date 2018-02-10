package com.slj.core.http;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tingis13
 * @date 2013-10-17
 * @version 1.0
 */
public interface IdResolver {
	public abstract String getId(HttpServletRequest httpservletrequest, Map map);
}

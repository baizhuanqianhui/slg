package com.slj.core.http;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.slj.core.base.Model;
import com.slj.core.exception.MarkException;

/**
 * @author tingis13
 * @date 2013-10-17
 * @version 1.0
 */
public class DefaultModelResolver implements ModelResolver{
	private RequestResolver requestResolver;
    
	public DefaultModelResolver()
    {
    }

    public Model resolveModel(HttpServletRequest request, HttpServletResponse response, Locale locale, IdResolver idResolver)
        throws MarkException
    {
        Map map = new HashMap();
        String actionId = idResolver.getId(request, map);
        String headAccept = request.getHeader("accept");
        
        Map data = null;
        if(requestResolver.accept(request))
        	data = requestResolver.resolve(request, response, locale, actionId);
        
        ServletModel model = data == null ? new ServletModel(actionId, request, response, locale) : new ServletModel(actionId, data, request, response, locale);
        if(map.size() > 0)
        	model.setMap(map);
        return model;
    }

	public void setRequestResolver(RequestResolver requestResolver) {
		this.requestResolver = requestResolver;
	}

}

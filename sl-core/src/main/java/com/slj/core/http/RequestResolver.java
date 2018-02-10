package com.slj.core.http;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.slj.core.exception.MarkException;


/**
 * @author tingis13
 * @date 2014-2-8
 * @version 1.0
 */
public interface RequestResolver {
    public abstract boolean accept(HttpServletRequest httpservletrequest);

    public abstract Map resolve(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse, Locale locale, String actionId)
        throws MarkException;
}

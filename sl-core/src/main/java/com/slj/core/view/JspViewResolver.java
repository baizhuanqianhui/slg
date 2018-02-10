package com.slj.core.view;

import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author tg
 * @date 2014-7-30
 * @version 1.0
 */
public class JspViewResolver extends InternalResourceViewResolver {

	protected Class requiredViewClass() {
		return com.slj.core.view.JspView.class;
	}

}

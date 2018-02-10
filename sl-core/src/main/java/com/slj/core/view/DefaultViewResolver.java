package com.slj.core.view;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

/**
 * @author tg
 * @date 2014-2-20
 * @version 1.0
 */
public class DefaultViewResolver extends ContentNegotiatingViewResolver{
	private Map viewMapping = new HashMap(2);
	private ViewResolver jspViewResolver;
	
	public View resolveViewName(String viewName, Locale locale)
			throws Exception {
		if(viewName != null){
			String[] viewNameArray = viewName.split(",");
			if(viewNameArray.length == 2){
				String v = viewNameArray[0].trim();
				if("jsp".equals(v)){
					return jspViewResolver.resolveViewName(viewNameArray[1].trim(), locale);
				}
			}
		}
		
		Object o = viewMapping.get(viewName);
		if(o == null){
			return (View)viewMapping.get("json");
		}else{
			return (View)o;
		}
		
	}

	public void setViewMapping(Map viewMapping) {
		this.viewMapping = viewMapping;
	}

	public void setJspViewResolver(ViewResolver jspViewResolver) {
		this.jspViewResolver = jspViewResolver;
	}

}

package com.slj.core.view;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;

import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.web.servlet.view.JstlView;

import com.slj.core.base.Model;
import com.slj.core.utils.Constants;

/**
 * @author tg
 * @date 2014-7-29
 * @version 1.0
 */
public class JspView extends JstlView{

	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Model m = (Model)request.getAttribute("_MarkModel");
		Locale l = m.getLocale();
		MessageSource messageSource = (MessageSource)request.getAttribute(Constants.SERVLET_APPLICATION_CONTEXT_ATTRIBUTE);
		//exposeLocalizationContext(request, messageSource, l);
		super.renderMergedOutputModel(model, request, response);
	}

	private static void exposeLocalizationContext(HttpServletRequest request, MessageSource messageSource, Locale jstlLocale)
    {
        java.util.ResourceBundle bundle = new MessageSourceResourceBundle(messageSource, jstlLocale);
        LocalizationContext jstlContext = new LocalizationContext(bundle, jstlLocale);
        request.setAttribute("javax.servlet.jsp.jstl.fmt.localizationContext", jstlContext);
        request.setAttribute("javax.servlet.jsp.jstl.fmt.locale", jstlLocale);
        request.setAttribute("javax.servlet.jsp.jstl.fmt.localizationContext.request", jstlContext);
        request.setAttribute("javax.servlet.jsp.jstl.fmt.locale.request", jstlLocale);
    }
}

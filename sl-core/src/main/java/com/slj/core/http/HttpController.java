package com.slj.core.http;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.slj.core.base.BaseController;
import com.slj.core.base.Model;
import com.slj.core.exception.MarkException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

/**
 * @author tingis13
 * @date 2016-10-17
 * @version 1.0
 */
@Controller
public class HttpController {
    protected final Log log = LogFactory.getLog(getClass());
    private IdResolver idResolver;
    private ModelResolver modelResolver;
    private BaseController baseController;
    private LocaleResolver localeResolver;
    private HandlerExceptionResolver handlerExceptionResolver;
    
    public HttpController()
    {
        idResolver = new ActionIdResolver();
        modelResolver = new DefaultModelResolver();
        localeResolver = new CookieLocaleResolver();
        ((CookieLocaleResolver)localeResolver).setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        handlerExceptionResolver = new DefaultHandlerExceptionResolver();
    }
	
	@RequestMapping(value="/*")
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws MarkException{
		Locale locale = null;
		Model model = null;
		Map dataMap = new HashMap();
		ModelAndView mv = new ModelAndView();
		try{
			locale = localeResolver.resolveLocale(request);
			model = modelResolver.resolveModel(request, response, locale, idResolver);
			request.setAttribute("_MarkModel", model);
			baseController.handle(model);
			dataMap = model.getMap();
			
			String outputStr = (String)model.getValue("_OutputStr");
			if(outputStr != null){
				String[] outputArray = outputStr.split(",");
				if(outputArray != null){
					for(int i = 0; i < outputArray.length; i++){
						outputArray[i] = outputArray[i].trim();
						mv.addObject(outputArray[i], model.getValue(outputArray[i]));
					}
				}
			}else{
				for(Iterator iterator = dataMap.entrySet().iterator(); iterator.hasNext();){
					Map.Entry entry = (Map.Entry)iterator.next();
					mv.addObject((String)entry.getKey(), entry.getValue());
				}
			}
			String viewStr = (String)model.getValue("_ViewStr");
			if(viewStr != null){
				mv.setViewName(viewStr);
			}
		}catch(Exception e){
			log.debug("handle exception", e);
			mv = handlerExceptionResolver.resolveException(request, response, model, e);
		}finally{
			
		}
		
        return mv;
	}

	public void setIdResolver(IdResolver idResolver) {
		this.idResolver = idResolver;
	}

	public void setModelResolver(ModelResolver modelResolver) {
		this.modelResolver = modelResolver;
	}

	public void setBaseController(BaseController baseController) {
		this.baseController = baseController;
	}

	public void setHandlerExceptionResolver(
			HandlerExceptionResolver handlerExceptionResolver) {
		this.handlerExceptionResolver = handlerExceptionResolver;
	}

}

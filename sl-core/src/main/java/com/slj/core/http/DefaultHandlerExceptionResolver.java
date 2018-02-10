package com.slj.core.http;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.slj.core.base.Model;
import com.slj.core.exception.Message;

/**
 * @author tingis13
 * @date 2014-2-22
 * @version 1.0
 */
public class DefaultHandlerExceptionResolver implements
		HandlerExceptionResolver{
	protected Log log = LogFactory.getLog(getClass());
	private String defaultErrorCode = "mark.default.error";

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Model model, Exception e) {
		ApplicationContext applicationContext = model.getApplicationContext();
		Locale locale = model.getLocale();
		ModelAndView mv = new ModelAndView();
		String errorCode;
		String errorMessage;
		Object[] args = null;
		if(e instanceof Message){
			errorCode = ((Message)e).getMessageKey();
			args = ((Message)e).getArgs();
			if(args != null){
				for(int i = 0; i < args.length; i++){
					args[i] = applicationContext.getMessage((String)args[i], null, (String)args[i], locale);
				}
			}
		}else{
			errorCode = e.getMessage();
		}
		try{
			errorMessage = applicationContext.getMessage(errorCode, args, locale);
		}catch(Exception e2){
			log.error("undefined.errorCode", e2);
			errorMessage = applicationContext.getMessage(defaultErrorCode, null, defaultErrorCode, locale);
		}
//		mv.addObject("ErrorCode", errorCode);
//		mv.addObject("ErrorMessage", errorMessage);
		//2016-1-25 yxf 平台的变量名尽量以下划线_为前缀，避免被覆盖   
		//_ReturnCode|_ReturnMessage,_ErrorCode|_ErrorMessage,_RemoteCode|_RemoteMessage,_RejCode|_RejMessage
		mv.addObject("_ErrorCode", errorCode);
		mv.addObject("_ErrorMessage", errorMessage);
		return mv;
	}

}

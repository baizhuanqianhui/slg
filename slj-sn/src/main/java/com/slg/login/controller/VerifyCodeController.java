package com.slg.login.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.slj.core.base.Business;
import com.slj.core.base.Model;
import com.slj.core.exception.MarkException;

public class VerifyCodeController implements Business {
	protected Log log = LogFactory.getLog(getClass());
	@Override
	public void execute(Model model) throws MarkException {
		HttpServletResponse response = (HttpServletResponse)model.getResponse();
		
		//生成随机字串 
		String verifyCode = VerifyCodeUtils.generateVerifyCode(5); 
		//存入会话session 
		HttpSession session = ((HttpServletRequest)model.getRequest()).getSession();
		//HttpSession session = request.getSession(true); 
		//删除以前的
		session.removeAttribute("verCode");
		session.setAttribute("verCode", verifyCode.toLowerCase()); 
		//生成图片 
		int w = 200, h = 50; 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			VerifyCodeUtils.outputImage(w, h, baos, verifyCode);
		} catch (IOException e) {
			log.error("=====验证码图片生成失败=====",e);  
            model.setValue("message_login", "验证码图片生成失败"); 
		} 
		byte [] imgByte = baos.toByteArray();
		model.setValue("_ViewStr", "img");
		model.setValue("Content", imgByte);
	}

}

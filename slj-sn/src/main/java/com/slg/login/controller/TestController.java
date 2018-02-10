package com.slg.login.controller;

import javax.servlet.http.HttpServletRequest;

import com.slj.core.base.Business;
import com.slj.core.base.Model;
import com.slj.core.exception.MarkException;

public class TestController implements Business{
	
	@Override
	public void execute(Model model) throws MarkException {
		// TODO Auto-generated method stub
		System.out.println("----->"+((HttpServletRequest)model.getRequest()).getSession()); 
		model.setValue("ceshi", "测试");
		model.setValue("aaa", "bbb");
	}
	
}

package com.slg.login.controller;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.slj.core.base.Business;
import com.slj.core.base.Model;
import com.slj.core.exception.MarkException;
/**
 * @author tingis13
 * @date 2016-10-17
 * @version 1.0
 */
public class LoginController implements Business{
	protected Log log = LogFactory.getLog(getClass());
	@Override
	public void execute(Model model) throws MarkException {
		String username = (String) model.getValue("TellerId");  
        String password = (String) model.getValue("Password");
		
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);  
//        token.setRememberMe(true);
        log.info("为了验证登录用户而封装的token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        
//        ModelAndView mv = new ModelAndView();
        //设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
      //获取当前的Subject  
        Subject currentUser = SecurityUtils.getSubject();  
        try {  
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查  
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应  
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法  
            log.info("对用户[" + username + "]进行登录验证..验证开始...");  
            currentUser.login(token);  
            log.info("对用户[" + username + "]进行登录验证..验证通过");  
        }catch(UnknownAccountException uae){  
            log.error("对用户[" + username + "]进行登录验证..验证未通过,未知账户",uae);  
//            model.setValue("message_login", "未知账户");
            throw new MarkException("unkown.account.error",new String[]{username});
        }catch(IncorrectCredentialsException ice){  
            log.error("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证",ice);  
            model.setValue("message_login", "密码不正确");  
        }catch(LockedAccountException lae){  
            log.error("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定",lae);  
            model.setValue("message_login", "账户已锁定");  
        }catch(ExcessiveAttemptsException eae){  
            log.error("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多",eae);  
            model.setValue("message_login", "用户名或密码错误次数过多");  
        }catch(AuthenticationException ae){  
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
           log.error("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下",ae);  
            model.setValue("message_login", "用户名或密码不正确");  
        }  
        //验证是否登录成功  
        if(currentUser.isAuthenticated()){  
           log.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");  
        }else{  
            token.clear();
        }  
	}
}

package com.slj.core.view;

import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

/**
 * @author tg
 * @date 2014-2-20
 * @version 1.0
 */
public class ImgView extends AbstractView{
	private String contentName;
	
    public ImgView(){
    	contentName = "Content";
    }
    
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("images/*");
		Object content = model.get(contentName);
		if(content != null){
			if(content instanceof byte[])
            {
                OutputStream out = response.getOutputStream();
                response.setContentLength(((byte[])content).length);
                out.write((byte[])content);
                out.flush();
            }
		}
		
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

}

package com.slj.core.view;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.view.AbstractView;

/**
 * @author tg
 * @date 2014-2-20
 * @version 1.0
 */
public class DownloadView extends AbstractView{
	private String contentType;
	private String fileNameField;
    private String fileContentField;
    protected Log log;
    private String fileNameEncoding;
    private String fileNameToEncoding;
	
    public DownloadView(){
        contentType = "application/x-download";
        fileNameField = "DownloadFileName";
        fileContentField = "DownloadFileContent";
        fileNameToEncoding = "ISO-8859-1";
        log = LogFactory.getLog(getClass());
    }
    
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        response.setContentType(getContentType());
        StringBuffer sb = new StringBuffer();
        String inline = request.getParameter("inline");
        if(inline == null)
            inline = String.valueOf(request.getAttribute("inline"));
        if(inline != null && inline.trim().equalsIgnoreCase("true"))
            sb.append("inline; filename=");
        else
            sb.append("attachment; filename=");
        if(fileNameEncoding != null){     	
        	try {
        		String fileName = String.valueOf(((Map)model).get(fileNameField));
				sb.append(new String(fileName.getBytes(fileNameEncoding), fileNameToEncoding));
			} catch (UnsupportedEncodingException e) {
				log.warn(e.getMessage(), e);
				sb.append(((Map)model).get(fileNameField));
			}
        }else{
        	sb.append(((Map)model).get(fileNameField));
        }
        response.setHeader("Content-Disposition", sb.toString());
        if(log.isDebugEnabled())
            log.debug(sb);
        try
        {
            OutputStream out = response.getOutputStream();
            byte fileContent[] = (byte[])((Map)model).get(fileContentField);
            out.write(fileContent);
            out.flush();
        }
        catch(Exception e)
        {
            log.error("download render", e);
        }
		
	}

	public void setFileNameField(String fileNameField) {
		this.fileNameField = fileNameField;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setFileContentField(String fileContentField) {
		this.fileContentField = fileContentField;
	}

	public void setFileNameEncoding(String fileNameEncoding) {
		this.fileNameEncoding = fileNameEncoding;
	}

	public void setFileNameToEncoding(String fileNameToEncoding) {
		this.fileNameToEncoding = fileNameToEncoding;
	}

}

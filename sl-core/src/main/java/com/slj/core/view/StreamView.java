package com.slj.core.view;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.AbstractView;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StreamView extends AbstractView {
	protected Log log;
	public static final String HEADER_PRAGMA = "Pragma";
	public static final String HEADER_EXPIRES = "Expires";
	public static final String HEADER_CACHE_CONTROL = "Cache-Control";
	private boolean nocacheFlag;
	private String contentType;
	private String contentField;
	private String encoding;

	public StreamView() {
		log = LogFactory.getLog(getClass());
		nocacheFlag = true;
	}

	public void setNocacheFlag(boolean nocacheFlag) {
		this.nocacheFlag = nocacheFlag;
	}

	public String getContentField() {
		return contentField;
	}

	public void setContentField(String cf) {
		contentField = cf;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	protected final void preventCaching(HttpServletResponse response) {
		if (nocacheFlag)
			response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 1L);
		if (nocacheFlag)
			response.setHeader("Cache-Control", "no-cache");
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest arg1, HttpServletResponse response) throws Exception {
//		preventCaching(response);
//		response.setContentType(getContentType());
//		Object content;
//		if (viewName != null && viewName.trim().length() > 0)
//			content = ((Map) model).get(viewName);
//		else
//			content = ((Map) model).get(contentField);
//		if (log.isDebugEnabled())
//			log.debug((new StringBuilder(String.valueOf(viewName))).append(" ")
//					.append(model).append(" ").append(content).toString());
//		if (content == null)
//			return;
//		try {
//			if (content instanceof byte[]) {
//				OutputStream out = response.getOutputStream();
//				response.setContentLength(((byte[]) content).length);
//				out.write((byte[]) content);
//				out.flush();
//			} else {
//				content = content.toString();
//				if (log.isDebugEnabled())
//					log.debug(content);
//				OutputStream out = response.getOutputStream();
//				byte bytes[];
//				if (encoding == null)
//					bytes = ((String) content).getBytes();
//				else
//					bytes = ((String) content).getBytes(encoding);
//				response.setContentLength(bytes.length);
//				out.write(bytes);
//				out.flush();
//			}
//		} catch (Exception e) {
//			log.error("render", e);
//		}
//		
	}

}

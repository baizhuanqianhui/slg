package com.slj.core.http;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;

import com.slj.core.exception.MarkException;
import com.slj.core.utils.JsonUtils;

/**
 * @author tingis13
 * @date 2013-10-19
 * @version 1.0
 */
public class JsonRequestResolver implements RequestResolver{
    protected Log log;
    
    public JsonRequestResolver()
    {
    	log = LogFactory.getLog(getClass());
    }

    public boolean accept(HttpServletRequest request)
    {
        return JsonUtils.isJSON(request);
    }

    public Map resolve(HttpServletRequest request, HttpServletResponse response, Locale locale, String actionId)
        throws MarkException
    {
        try
        {
            byte data[] = readData(request.getInputStream(), request.getContentLength());
//            Map context = new HashMap();
//            context.put("_TransactionId_", identity);
//            if(beforeParser != null)
//                data = (byte[])beforeParser.parse(data, context);
            Map result = (Map)JsonUtils.decode(new ByteArrayInputStream(data), java.util.Map.class);
            if(log.isDebugEnabled())
            	log.debug((new StringBuilder("parse json:")).append(result).toString());
            return result;
        }
        catch(JsonParseException e)
        {
            throw new MarkException("mark json parse error", e);
        }
        catch(IOException e)
        {
            throw new MarkException("mark json io error", e);
        }
    }

	protected byte[] readData(InputStream in, int length) throws IOException {
		byte buf[] = new byte[length];
		int off;
		int len;
		for (off = 0; off < length; off += len) {
			len = in.read(buf, off, length - off);
			if (len == -1)
				break;
		}

		if (off < length)
			throw new IOException(
					(new StringBuilder("data not enough,expires "))
							.append(length).append("bytes,but ").append(off)
							.append(" bytes at fact").toString());
		else
			return buf;
	}

//	public Parser getBeforeParser() {
//		return beforeParser;
//	}
//
//	public void setBeforeParser(Parser beforeParser) {
//		this.beforeParser = beforeParser;
//	}
    
}

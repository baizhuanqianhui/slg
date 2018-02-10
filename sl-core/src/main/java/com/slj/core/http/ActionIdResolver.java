package com.slj.core.http;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.util.UrlPathHelper;

/**
 * @author tingis13
 * @date 2013-10-17
 * @version 1.0
 */
public class ActionIdResolver implements IdResolver{
    private UrlPathHelper urlPathHelper;
    protected Log log;
    private String idName;
	
    public ActionIdResolver()
    {
        urlPathHelper = new UrlPathHelper();
        log = LogFactory.getLog(getClass());
        idName = "actionId";
    }

    public void setIdName(String str)
    {
    	idName = str;
    }

    public String getId(HttpServletRequest request, Map map)
    {
        if(map != null)
        {
            String id = (String)map.get(idName);
            if(id != null)
                return id;
        }
        String lookupPath = urlPathHelper.getLookupPathForRequest(request);
        String actionId = resolveActionId(lookupPath, request);
        return actionId;
    }

    protected String resolveActionId(String path, HttpServletRequest request)
    {
        String actionId = request.getParameter(idName);
        if(actionId == null)
        {
            int s = 1;
            int l1 = path.indexOf('/', s);
            int l2 = path.lastIndexOf('.');
            int l = -1;
            if(l1 != -1 && l2 != -1)
                l = l1 <= l2 ? l1 : l2;
            else
            if(l1 != -1)
                l = l1;
            else
            if(l2 != -1)
                l = l2;
            if(l == -1)
                l = path.length();
            actionId = path.substring(s, l);
            if(log.isDebugEnabled())
                log.debug((new StringBuilder("call action: ")).append(actionId).toString());
        }
        return actionId;
    }
}

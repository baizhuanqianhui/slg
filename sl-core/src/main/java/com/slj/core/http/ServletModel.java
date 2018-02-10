package com.slj.core.http;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.slj.core.base.AbstractModel;
import com.slj.core.base.User;
import com.slj.core.exception.MarkRuntimeException;

/**
 * @author tingis13
 * @date 2013-10-16
 * @version 1.0
 */
public class ServletModel extends AbstractModel{
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private Locale locale;
    private boolean initRequestMap;
    
	public ServletModel(String actionId, HttpServletRequest request, HttpServletResponse response, Locale locale)
    {
        super(actionId);
        initRequestMap = false;
        this.request = request;
        this.response = response;
        this.locale = locale;
        session = request.getSession(false);
        if(session != null)
        {
            User user = (User)session.getAttribute("_User");
            super.setUser(user);
        }
    }

    public ServletModel(String actionId, Map dataMap, HttpServletRequest request, HttpServletResponse response, Locale locale)
    {
        super(actionId, dataMap);
        initRequestMap = false;
        this.request = request;
        this.response = response;
        this.locale = locale;
        session = request.getSession(false);
        if(session != null)
        {
            User user = (User)session.getAttribute("_User");
            super.setUser(user);
        }
    }
    
	public void setUser(User user) {
        if(user == super.getUser())
        {
            if(session != null && user != null)
                session.setAttribute("_User", user);
            return;
        }
        if(session == null)
            session = request.getSession();
        else{
            session.invalidate();
            session = request.getSession();
        }
        if(user != null)
            session.setAttribute("_User", user);
        else
            session.removeAttribute("_User");
        super.setUser(user);
	}

	public Object getSessionAttribute(String name)
    {
        if(session != null)
            return session.getAttribute(name);
        else
            return null;
    }

    public void setSessionAttribute(String name, Object object)
    {
        if(session == null)
            session = request.getSession();
        session.setAttribute(name, object);
    }

    public void removeSessionAttribute(String name)
    {
        if(session != null)
            session.removeAttribute(name);
    }

    public Object getRequestAttribute(String name)
    {
        if(name.equals("javax.net.ssl.peer_certificates"))
        {
            Object object = request.getAttribute(name);
            if(object != null)
            {
                String className = object.getClass().getName();
                if(className.indexOf("Ljava.security.cert.X509Certificate") == -1)
                {
                    object = request.getAttribute("javax.servlet.request.X509Certificate");
                    return object;
                } else
                {
                    return object;
                }
            } else
            {
                return request.getAttribute("javax.servlet.request.X509Certificate");
            }
        } else
        {
            return request.getAttribute(name);
        }
    }

    public void setRequestAttribute(String name, Object object)
    {
        request.setAttribute(name, object);
    }

    public Object getValue(String name)
    {
        Object object = super.getValue(name);
        if(object != null)
            return object;
        Object fieldDefinition = getFieldDefinition(name);
        Object value = getValueInternal(name, "", fieldDefinition, false);
        if(value != null)
        {
            super.setValue(name, value);
            return value;
        }
//        if(name.equals("application/stream"))
//            return stream;
//        else
            return null;
    }

    private byte[] getStream()
    {
        int contentLength;
        byte content[];
        int offset;
        contentLength = request.getContentLength();
        content = new byte[contentLength];
        offset = 0;
        while(offset < contentLength){
            try
            {
                int realLength = request.getInputStream().read(content, offset, contentLength - offset);
                if(realLength == -1)
                    return null;
            	offset += realLength;
            }
            catch(IOException e)
            {
                throw new MarkRuntimeException("request_isnot_a_valid_stream");
            }        	
        }
        return content;
    }

    private Object getValueInternal(String name, String path, Object fieldDefinition, boolean ignoreCondition)
    {
//        if(fieldDefinition == null || (fieldDefinition instanceof String))
//        {
            String values[] = request.getParameterValues((new StringBuilder(String.valueOf(path))).append(name).toString());
            if(values == null){
	            if(fieldDefinition != null)
	            {
	                String fd = (String)fieldDefinition;
	                if(fd.equals("FileStyle") || fd.startsWith("FileStyle{") && fd.endsWith("}"))
	                    if(request instanceof MultipartHttpServletRequest)
	                    {
	                        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
	                        return multipartRequest.getFile((new StringBuilder(String.valueOf(path))).append(name).toString());
	                    } else
	                    {
	                        throw new MarkRuntimeException("request_isnot_a_valid_multipart_requst");
	                    }
	            }
	            return null;
            }
            if(values.length == 1)
                return values[0];
            else
                return values;
//        }
//        if(fieldDefinition instanceof Map)
//        {
//            Map map = new HashMap(((Map)fieldDefinition).size());
//            Object key;
//            Object object;
//            for(Iterator it = ((Map)fieldDefinition).keySet().iterator(); it.hasNext(); map.put(key, object))
//            {
//                key = it.next();
//                object = getValueInternal((String)key, path, ((Map)fieldDefinition).get(key), ignoreCondition);
//            }
//
//            return map;
//        }
//        if(fieldDefinition instanceof FieldList)
//        {
//            FieldList fieldList = (FieldList)fieldDefinition;
//            String counterName = fieldList.getCounter();
//            String counterValue = (String)getValueInternal(counterName, path, "", ignoreCondition);
//            if(counterValue == null)
//            {
//                counterValue = (String)getValueInternal(counterName, "", "", ignoreCondition);
//                if(counterValue == null)
//                    counterValue = counterName;
//            }
//            int counter = 0;
//            try
//            {
//                counter = Integer.parseInt(counterValue);
//            }
//            catch(Exception exception) { }
//            List list = new ArrayList(counter);
//            Map fieldListDefinition = fieldList.getFields();
//            String condition = fieldList.getCondition();
//            for(int i = 0; i < counter; i++)
//            {
//                String currentPath = (new StringBuilder(String.valueOf(path))).append(name).append("[").append(i).append("]").append(".").toString();
//                Map currentMap = (Map)getValueInternal(null, currentPath, fieldListDefinition, ignoreCondition);
//                if(!ignoreCondition && condition != null && condition.length() > 0)
//                {
//                    Expr expr = new OgnlExpr();
//                    boolean b = ((Boolean)expr.getValue(condition, null, currentMap)).booleanValue();
//                    if(b)
//                        list.add(currentMap);
//                } else
//                {
//                    list.add(currentMap);
//                }
//            }
//
//            return list;
//        } else
//        {
//            return "Invalid field type";
//        }
    }
    
    private Object getFieldDefinition(String fieldName)
    {
//        if(getTransactionConfig() != null && getTransactionConfig().getFields() != null)
//            return getTransactionConfig().getFields().get(fieldName);
//        else
            return null;
    }

    public Map getMap()
    {
        if(initRequestMap)
            return new HashMap(super.getMap());
//        TransactionConfig tc = getTransactionConfig();
//        Map fields = tc != null ? tc.getFields() : null;
//        if(fields == null)
//        {
            Map requestMap = getRequestMap();
            if(super.getMap().size() > 0)
                requestMap.putAll(super.getMap());
            super.setMap(requestMap);
            initRequestMap = true;
            return requestMap;
//        }
//        Map map = new HashMap(super.getMap());
//        String key;
//        for(Iterator it = fields.keySet().iterator(); it.hasNext(); map.put(key, getValue(key)))
//            key = (String)it.next();
//
//        initRequestMap = true;
//        return map;
    }

    private Map getRequestMap()
    {
        Map data = new HashMap();
        for(Enumeration enum0 = request.getParameterNames(); enum0.hasMoreElements();)
        {
            String key = (String)enum0.nextElement();
            if(!key.startsWith("_"))
            {
                String values[] = request.getParameterValues(key);
                if(values.length == 1)
                    data.put(key, values[0]);
                else
                    data.put(key, values);
            }
        }

        return data;
    }

    public Locale getLocale()
    {
        return locale;
    }

    public Map getRequestData()
    {
        Map result = new HashMap();
        for(Enumeration enum0 = request.getParameterNames(); enum0.hasMoreElements();)
        {
            String key = (String)enum0.nextElement();
            String valueArray[] = request.getParameterValues(key);
            if(valueArray.length == 1)
                result.put(key, valueArray[0]);
            else
                result.put(key, valueArray);
        }

        return result;
    }

    public Object getSession()
    {
        return session;
    }

    public Object getRequest()
    {
        return request;
    }
    
    public Object getResponse()
    {
        return response;
    }
}

package com.slj.core.base;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;

/**
 * @author tingis13
 * @date 2013-10-16
 * @version 1.0
 */
public interface Model {
	
    public abstract String getActionId();

    public abstract void setActionId(String s);
    
    public abstract User getUser();

    public abstract void setUser(User user);

    public abstract Locale getLocale();

    public abstract Object getSession();
    
    public abstract Object getSessionAttribute(String name);
    
    public abstract void setSessionAttribute(String name, Object object);
    
    public abstract void removeSessionAttribute(String name);
    
    public abstract Object getRequest();
    
    public abstract Object getRequestAttribute(String s);
    
    public abstract void setRequestAttribute(String name, Object object);
    
    public abstract Object getResponse();

    public abstract Object getValue(String s);

    public abstract String getString(String s);

    public abstract Boolean getBoolean(String s);

    public abstract Integer getInteger(String s);

    public abstract Long getLong(String s);

    public abstract BigDecimal getBigDecimal(String s);

    public abstract String[] getStringArray(String s);

    public abstract Object[] getObjectArray(String s, Class class1);

    public abstract void setValue(String s, Object obj);

    public abstract Map getMap();

    public abstract void setMap(Map map);
    
    public abstract int getState();

    public abstract void setState(int i);

    public abstract Timestamp getTimestamp();

    public abstract void setApplicationContext(ApplicationContext applicationcontext);

    public abstract ApplicationContext getApplicationContext();

    public abstract MessageSource getMessageSource();
    
    public abstract void setVariable(Object obj);

    public abstract Object getVariable();
}

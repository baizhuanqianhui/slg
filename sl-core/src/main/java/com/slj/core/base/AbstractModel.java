package com.slj.core.base;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;

import com.slj.core.exception.MarkRuntimeException;

/**
 * @author tingis13
 * @date 2013-10-16
 * @version 1.0
 */
public abstract class AbstractModel implements Model{
    private int state;
    private Timestamp timestamp;
    private User user;
    private String actionId;
    private Map data;
    protected Log log;
    private ApplicationContext applicationContext;
    private MessageSource messageSource;
    private Object variable;
    
    public AbstractModel(String actionId)
    {
        state = 0;
        log = LogFactory.getLog(getClass());
        this.actionId = actionId;
        timestamp = new Timestamp(System.currentTimeMillis());
        data = new HashMap();
    }

    public AbstractModel(String actionId, Map dataMap)
    {
        state = 0;
        log = LogFactory.getLog(getClass());
        this.actionId = actionId;
        timestamp = new Timestamp(System.currentTimeMillis());
        data = dataMap;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Object getValue(String name)
    {
        Object object = data.get(name);
        return object;
    }

    public void setValue(String name, Object data)
    {
        if(data == null)
            this.data.remove(name);
        else
            this.data.put(name, data);
    }

    public Map getMap()
    {
        return data;
    }
    
    //2016-1-6 yxf for ognl
    public final Map getInnerMap()
    {
        return data;
    }    

    public void setMap(Map map)
    {
        data.putAll(map);
    }

    public int getState()
    {
        return state;
    }

    public void setState(int i)
    {
        state = i;
    }

    public String getActionId()
    {
        return actionId;
    }

    public void setActionId(String string)
    {
    	actionId = string;
    }

    public Locale getLocale()
    {
        return Locale.getDefault();
    }

    public Boolean getBoolean(String name)
    {
        Object object = getValue(name);
        if(object == null)
            return null;
        if(object instanceof Boolean)
            return (Boolean)object;
        if(object instanceof String)
        {
            if(((String)object).equals(""))
                return null;
            if(((String)object).length() == 1)
            {
                char ch = ((String)object).charAt(0);
                switch(ch)
                {
                case 49: // '1'
                case 84: // 'T'
                case 89: // 'Y'
                case 116: // 't'
                case 121: // 'y'
                    return Boolean.TRUE;

                case 48: // '0'
                case 70: // 'F'
                case 78: // 'N'
                case 102: // 'f'
                case 110: // 'n'
                    return Boolean.FALSE;
                }
                throw new MarkRuntimeException("unsupported_boolean_convert", new Object[] {
                    object
                });
            } else
            {
                return new Boolean((String)object);
            }
        } else
        {
            throw new MarkRuntimeException("unsupported_boolean_convert", new Object[] {
                object
            });
        }
    }

    public Integer getInteger(String name)
    {
        Object object = getValue(name);
        if(object == null)
            return null;
        if(object instanceof Integer)
            return (Integer)object;
        if(object instanceof String)
            if(((String)object).equals(""))
                return null;
            else
                return new Integer((String)object);
        if(object instanceof Number)
            return new Integer(((Number)object).intValue());
        else
            throw new MarkRuntimeException("unsupported_integer_convert", new Object[] {
                object
            });
    }

    public Long getLong(String name)
    {
        Object object = getValue(name);
        if(object == null)
            return null;
        if(object instanceof Long)
            return (Long)object;
        if(object instanceof String)
            if(((String)object).equals(""))
                return null;
            else
                return new Long((String)object);
        if(object instanceof Number)
            return new Long(((Number)object).longValue());
        else
            throw new MarkRuntimeException("unsupported_long_convert", new Object[] {
                object
            });
    }

    public String getString(String name)
    {
        Object object = getValue(name);
        if(object == null)
            return null;
        if(object instanceof String)
            return (String)object;
        else
            return object.toString();
    }

    public BigDecimal getBigDecimal(String name)
    {
        Object object = getValue(name);
        if(object == null)
            return null;
        if(object instanceof BigDecimal)
            return (BigDecimal)object;
        if(object instanceof String)
            return new BigDecimal((String)object);
        if(object instanceof Number)
            return new BigDecimal(((Number)object).doubleValue());
        else
            throw new MarkRuntimeException("unsupported_bigdecimal_convert", new Object[] {
                object
            });
    }

    public String[] getStringArray(String name)
    {
        Object object = getValue(name);
        if(object == null)
            return new String[0];
        if(object.getClass().isArray())
        {
            if(object.getClass().getComponentType() == java.lang.String.class)
                return (String[])object;
            int length = Array.getLength(object);
            String newArray[] = new String[length];
            for(int i = 0; i < length; i++)
            {
                Object subObject = Array.get(object, i);
                if(subObject == null)
                    newArray[i] = null;
                else
                    newArray[i] = String.valueOf(Array.get(object, i));
            }

            return newArray;
        } else
        {
            return (new String[] {
                (String)object
            });
        }
    }

    public Object[] getObjectArray(String name, Class class0)
    {
        Object object = getValue(name);
        if(object == null)
            return (Object[])Array.newInstance(class0, 0);
        if(object.getClass().isArray())
            if(object.getClass().getComponentType() == class0)
                return (Object[])object;
            else
                throw new MarkRuntimeException("unsupported_array_convert", new Object[] {
                    object
                });
        if(class0.isAssignableFrom(object.getClass()))
        {
            Object result[] = (Object[])Array.newInstance(class0, 1);
            result[0] = object;
            return result;
        } else
        {
            throw new MarkRuntimeException("unsupported_array_convert", new Object[] {
                object
            });
        }
    }

    public Timestamp getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp)
    {
        this.timestamp = timestamp;
    }

    public ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext)
    {
        this.applicationContext = applicationContext;
    }

    public MessageSource getMessageSource()
    {
        if(messageSource != null)
            return messageSource;
//        if(applicationContext != null && getTransactionConfig() != null)
//        {
//            ApplicationContext bc = getTransactionConfig().getApplicationContext();
//            if(bc != null && bc != applicationContext)
//            {
//                messageSource = new MessageSourceWrapper(applicationContext, bc);
//                return messageSource;
//            }
//        }
        messageSource = applicationContext;
        return messageSource;
    }

    public void setVariable(Object variable)
    {
        this.variable = variable;
    }

    public Object getVariable()
    {
        return variable;
    }    
    
    public String toString()
    {
        return getClass().getName() + " ActionId:" + getActionId() + " User:" + user + " Timestamp:" + timestamp + " Inner data:" + data;
    }
    
}
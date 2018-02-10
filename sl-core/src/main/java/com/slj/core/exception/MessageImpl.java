package com.slj.core.exception;

/**
 * @author tingis13
 * @date 2013-10-16
 * @version 1.0
 */
public class MessageImpl implements Message{
    private String defaultMessage;
    private String messageKey;
    private Object args[];
    
    public MessageImpl()
    {
        defaultMessage = null;
        messageKey = null;
        args = new Object[0];
    }

    public boolean hasDefaultMessage()
    {
        return defaultMessage != null;
    }

    public void setDefaultMessage(String message)
    {
        defaultMessage = message;
    }

    public String getDefaultMessage()
    {
        if(defaultMessage == null)
            return null;
        else
            return defaultMessage;
    }

    public void setMessageKey(String key)
    {
        messageKey = key;
    }

    public String getMessageKey()
    {
        return messageKey;
    }

    public void setArgs(Object args[])
    {
        this.args = args;
    }

    public Object[] getArgs()
    {
        return args;
    }
}

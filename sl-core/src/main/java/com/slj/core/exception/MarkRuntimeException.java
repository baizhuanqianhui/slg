package com.slj.core.exception;


/**
 * @author tingis13
 * @date 2013-10-16
 * @version 1.0
 */
public class MarkRuntimeException extends RuntimeException{
    private MessageImpl msg;
    private static final String DEFAULT_EX_KEY = "mark.error.undefined";
    private Object args[];
    
    public MarkRuntimeException()
    {
        super("");
        msg = new MessageImpl();
        msg.setMessageKey(DEFAULT_EX_KEY);
    }

    public MarkRuntimeException(String arg0)
    {
        super(arg0);
        msg = new MessageImpl();
        if(arg0 == null || arg0.trim().length() == 0)
            msg.setMessageKey(DEFAULT_EX_KEY);
        else
            msg.setMessageKey(arg0);
    }

    public MarkRuntimeException(String arg0, Object args[])
    {
        super(arg0);
        msg = new MessageImpl();
        this.args = args;
        if(arg0 == null || arg0.trim().length() == 0)
            msg.setMessageKey(DEFAULT_EX_KEY);
        else
            msg.setMessageKey(arg0);
        msg.setArgs(args);
    }

    public MarkRuntimeException(String arg0, Throwable arg1)
    {
        super(arg0, arg1);
        msg = new MessageImpl();
        if(arg0 == null || arg0.trim().length() == 0)
            msg.setMessageKey(DEFAULT_EX_KEY);
        else
            msg.setMessageKey(arg0);
    }

    public MarkRuntimeException(String arg0, Throwable arg1, Object args[])
    {
        super(arg0, arg1);
        msg = new MessageImpl();
        this.args = args;
        if(arg0 == null || arg0.trim().length() == 0)
            msg.setMessageKey(DEFAULT_EX_KEY);
        else
            msg.setMessageKey(arg0);
        msg.setArgs(args);
    }

    public MarkRuntimeException(Throwable arg0)
    {
        super("", arg0);
        msg = new MessageImpl();
        msg.setMessageKey(DEFAULT_EX_KEY);
    }

    public boolean hasDefaultMessage()
    {
        return msg.hasDefaultMessage();
    }

    public void setDefaultMessage(String message)
    {
        msg.setDefaultMessage(message);
    }

    public String getDefaultMessage()
    {
        String result = msg.getDefaultMessage();
        if(result == null)
        {
            StringBuffer sb = (new StringBuffer(getClass().getName())).append(" MessageCode: ").append(getMessageKey());
            if(msg.getArgs() != null)
            {
                sb.append(" Args: ");
                Object args[] = msg.getArgs();
                for(int i = 0; i < args.length; i++)
                    sb.append(args[i]).append(" ");

            }
            if(getCause() != null)
            {
                sb.append(" nested exception is: ");
                sb.append(getCause());
            }
            return sb.toString();
        } else
        {
            return result;
        }
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer(super.toString());
        if(msg.getArgs() != null)
        {
            sb.append(" args:");
            Object args[] = msg.getArgs();
            for(int i = 0; i < args.length; i++)
                sb.append(args[i]).append(" ");

        }
        return sb.toString();
    }

    public String getMessageKey()
    {
        return msg.getMessageKey();
    }

    public Object[] getArgs()
    {
        return msg.getArgs();
    }

    public void setArgs(Object args[])
    {
        this.args = args;
        msg.setArgs(args);
    }

    public String getMessage()
    {
        StringBuffer sb;
        if(getCause() == null)
        {
            if(args == null)
                return super.getMessage();
            sb = new StringBuffer(super.getMessage());
            sb.append(" Args: ");
            for(int i = 0; i < args.length; i++)
                sb.append(args[i]).append(" ");

            return sb.toString();
        }
        if(args == null)
            return (new StringBuilder(String.valueOf(super.getMessage()))).append("; nested exception is ").append(getCause().getClass().getName()).append(": ").append(getCause().getMessage()).toString();
        sb = new StringBuffer(super.getMessage());
        sb.append(" Args: ");
        for(int i = 0; i < args.length; i++)
            sb.append(args[i]).append(" ");

        sb.append("; nested exception is ").append(getCause().getClass().getName()).append(": ").append(getCause().getMessage());
        return sb.toString();
    }  
}

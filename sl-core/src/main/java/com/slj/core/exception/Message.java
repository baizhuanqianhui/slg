package com.slj.core.exception;

import java.io.Serializable;

/**
 * @author tingis13
 * @date 2013-10-16
 * @version 1.0
 */
public interface Message extends Serializable{
    public abstract String getDefaultMessage();

    public abstract boolean hasDefaultMessage();

    public abstract String getMessageKey();

    public abstract Object[] getArgs();
}

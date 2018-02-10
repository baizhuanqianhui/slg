package com.slj.core.base;

import java.io.Serializable;
import java.util.Locale;

/**
 * @author tingis13
 * @date 2013-10-16
 * @version 1.0
 */
public interface User extends Serializable{
    public abstract String getId();

    public abstract void setId(String s);

    public abstract String getUserId();

    public abstract void setUserId(String s);

    public abstract String getName();

    public abstract void setName(String s);

    public abstract String getCifNo();

    public abstract void setCifNo(String s);

    public abstract Locale getLocale();

    public abstract void setLocale(Locale locale);

    public abstract Object getClientCertificate();

    public abstract void setClientCertificate(Object obj);

    public abstract String getPassword();

    public abstract void setPassword(String s);

    public abstract void logout();

    public abstract void setLogout(boolean flag);

    public abstract boolean isLogout();

}

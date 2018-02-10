package com.slj.core.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * @author tg
 * @date 2014-7-3
 * @version 1.0
 */
public class BeanUtil {
    protected BeanUtil()
    {
    }

    public static Object getBeanProperty(Object obj, String name)
    {
        Method readMethod;
        readMethod = null;
        BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(obj.getClass());
		} catch (IntrospectionException e1) {
			e1.printStackTrace();
		}
        if(beanInfo != null)
        {
            PropertyDescriptor pd[] = beanInfo.getPropertyDescriptors();
            for(int i = 0; i < pd.length; i++)
            {
                PropertyDescriptor one = pd[i];
                if(!one.getName().equals(name))
                    continue;
                readMethod = one.getReadMethod();
                break;
            }

        }
        if(readMethod == null)
            return null;
        try
        {
            Object returnObj = null;
            returnObj = readMethod.invoke(obj, new Object[0]);
            return returnObj;
        }
        catch(Exception e)
        {
            return null;
        }
    }
}

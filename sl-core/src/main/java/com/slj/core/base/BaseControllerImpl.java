package com.slj.core.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.slj.core.exception.MarkException;

/**
 * @author tingis13
 * @date 2016-10-17
 * @version 1.0
 */
public class BaseControllerImpl implements ApplicationContextAware, BaseController{
	protected ApplicationContext applicationContext;
    private static ThreadLocal modelThreadLocal = new ThreadLocal() {

        protected Object initialValue()
        {
            return new ArrayList();
        }

    };
    
    public BaseControllerImpl()
    {
    }

    public void handle(Model model)
        throws MarkException
    {
    	setModel(model);
    	model.setApplicationContext(applicationContext);
        Object o = applicationContext.getBean(model.getActionId());
        //分发到各个业务bean进行处理
        if(o instanceof Business){
        	((Business) o).execute(model);
        }
        setModel(null);
    }

    public static Model getModel()
    {
        List list = (List)modelThreadLocal.get();
        int size = list.size();
        return size != 0 ? (Model)list.get(size - 1) : null;
    }

    public static void setModel(Model model)
    {
        if(model == null)
        {
            List list = (List)modelThreadLocal.get();
            if(list.size() > 0)
                list.remove(list.size() - 1);
        } else
        {
            List list = (List)modelThreadLocal.get();
            list.add(model);
        }
    }

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		applicationContext = arg0;
	}
}

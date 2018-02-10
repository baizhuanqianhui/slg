package com.slg.batch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestBatch {
	protected Log log = LogFactory.getLog(getClass());
	public void printSomething(String test){
		//内容就是打印一句话
		System.out.println("this is andy schedule");
		log.info("****Quartz定时批量框架测试*****"+test);
	}

}

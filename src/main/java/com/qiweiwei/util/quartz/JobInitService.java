package com.qiweiwei.util.quartz;

import com.jfinal.kit.LogKit;

public class JobInitService {

	/**
	 * 创建定时执行
	 * 
	 * @param groupname
	 * @param id
	 * @param Cron
	 */
	public static void createDynamicJob(String groupname, String id, String Cron) {
		JobBean job = new JobBean();
		job.setJobClass("com.highmall.core.quartz.DakeTaskJob");
		job.setCronExpression(Cron);
		job.setJobGroup(groupname);
		job.setJobDesc(id);
		QuartzDynamicPlugin.addJob(job);
		LogKit.info("创建定时任务:" + groupname + "." + id + ",Cron:" + Cron);
	}

	/**
	 * 团购活动动态创建定时任务
	 * @param jobclass
	 * @param groupname
	 * @param id
	 * @param Cron
	 */
	public static void createDynamicJob_groupbuy(String jobclass, String groupname, String id, String Cron) {
		JobBean job = new JobBean();
		job.setJobClass(jobclass);
		job.setCronExpression(Cron);
		job.setJobGroup(groupname);
		job.setJobDesc(id);
		QuartzDynamicPlugin.addJob(job);
		LogKit.info("创建定时任务:" + groupname + "," + jobclass + "," + id + ",Cron:" + Cron);
	}
	
	public static void initDynamicJob() {
		LogKit.info("开始执行初始化定时任务");
	}
	
	
	

}

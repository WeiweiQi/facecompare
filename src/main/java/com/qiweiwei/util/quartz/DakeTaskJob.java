package com.qiweiwei.util.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.jfinal.kit.LogKit;
import com.qiweiwei.util.common.DateUtil;

/**
 * @Description:
 * @author 齐巍巍
 * @Date 2019年9月30日09:18:56
 */
public class DakeTaskJob implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {
		Date date = new Date();
		System.out.println(DateUtil.df_yyyy_MM_dd_HH_mm_ss.get().format(date) + "执行Trigger:" + context.getTrigger().getJobKey());
		LogKit.info(DateUtil.df_yyyy_MM_dd_HH_mm_ss.get().format(date) + "执行Trigger:" + context.getTrigger().getJobKey());
		String[] jobsplit = context.getTrigger().getJobKey().toString().split("\\.");
		if (jobsplit.length <= 1) {
			LogKit.error("DakeTaskJob出错:" + context.getTrigger().getJobKey().toString());
			return;
		}
//		if (context.getTrigger().getJobKey().toString().startsWith(HighmallStatus.GROUP_NAME_TIME_OFF_SHELF)) {
//			TimeShelfManageService.offShelfByJobId(Integer.parseInt(jobsplit[1]));
//		}
		
		 System.out.println("动态任务执行成功");
		
	}
}
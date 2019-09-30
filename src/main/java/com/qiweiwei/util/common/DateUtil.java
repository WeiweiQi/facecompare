package com.qiweiwei.util.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateUtil {
	
	public static final ThreadLocal<DateFormat> df_yyyy_MM_dd = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
	public static final ThreadLocal<DateFormat> df_yyyy_MM_dd_HH_mm_ss = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	public static final ThreadLocal<DateFormat> df_yyyy_MM_dd_HH_mm_ss_SSS = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
	

}

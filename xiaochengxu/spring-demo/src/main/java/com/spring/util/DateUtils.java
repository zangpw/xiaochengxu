package com.spring.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	public static final String DEFAULT_FORMAT = "yyyyMMddHHmmssSSS";
	public static final String DEFAULT_FORMAT_STRING = "yyyyMMddHHmmss";
	public static final String DEFAULT_FORMAT_YYYYMMDD = "yyyyMMdd";
	public static final String DEFAULT_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String DEFAULT_YEAR_MON_DAY = "yyyy-MM-dd HH:mm:ss";
	public static final String DEFAULT_YEAR_MON_DAY2 = "yyyy/MM/dd HH:mm:ss";
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};


	/**
	 * ��ȡ32λ��UUID ����
	 * 
	 * @return String
	 * 
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String newuuid = String.valueOf(uuid).replace("-", "");
		return newuuid;
	}

	/**
	 * ��ȡ��ǰʱ��
	 * 
	 * @return Timestamp
	 * 
	 */
	@Deprecated
	public static Timestamp getTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * Dateתstring ��ȡʱ��yyyyMMddHHmmss ��ȡ��ǰʱ��
	 * 
	 * @return String
	 * 
	 */
	@Deprecated
	public static String getDateString() {
		return DateTime.now().toString(DEFAULT_FORMAT_STRING);
	}

	/**
	 * Dateתstring ��ȡʱ��yyyyMMddHHmmss ��ȡ��ǰʱ��
	 * 
	 * @return String
	 * 
	 */
	@Deprecated
	public static String getDateStringFFF() {
		return DateTime.now().toString(DEFAULT_FORMAT);
	}

	/**
	 * String ����תDATE
	 * 
	 * @return DATE
	 * 
	 */
	@Deprecated
	public static Date parse(String strDate) throws Exception {
		DateTimeFormatter format = DateTimeFormat
				.forPattern(DEFAULT_FORMAT_YYYY_MM_DD);
		return DateTime.parse(strDate, format).toDate();
	}

	/**
	 * ��ȡ��ǰʱ�� new Date()
	 * 
	 * @return String date
	 */
	@Deprecated
	public static String getDate() {
		SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT_YYYYMMDD);
		String date = df.format(new Date());
		return date;
	}

	/**
	 * ��ȡ��ǰʱ�� new Date() yyyy-MM-dd
	 * 
	 * @return String date
	 */
	@Deprecated
	public static String getDate24() {
		SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT_YYYY_MM_DD);
		String date = df.format(new Date());
		return date;
	}

	/**
	 * ��ȡ�����һ��
	 * 
	 * @return
	 */
	@Deprecated
	public static String lastDayOfMonth(String str) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = df.parse(str);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.roll(Calendar.DAY_OF_MONTH, -1);
			String monthDay = new SimpleDateFormat("dd").format(cal.getTime());
			return monthDay;
		} catch (ParseException e) {
			System.out.println("��ȡ�����һ���쳣��");
		}
		return "30";
	}

	/**
	 * �ж��ַ��Ƿ�Ϊ��
	 * 
	 * @param param
	 *            (param != null && param.split(",").length > 1 ) ? true : false
	 * @return boolean
	 */
	public static boolean paramLength(String param) {
		return (param != null && param.split(",").length > 1) ? true : false;
	}

	/**
	 * 
	 * @param strDate
	 *            �������� 20140404
	 * @param t
	 *            ���ڵļӼ��㷨
	 * @return String
	 */
	public static String getNextDay_YYYYMMDD(String strDate, int t) {

		SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FORMAT_YYYYMMDD);
		Date newDate = null;
		try {
			Date date = format.parse(strDate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH,
					calendar.get(Calendar.DAY_OF_MONTH) + t);// �����ڼ�1
			newDate = calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return format.format(newDate);
	}

	/**
	 * �ַ�ȥ�� ���磨1,1,1,2,2,2,2,3,4,5,5,6,6,7,8,8,8,�� return 1,2,3,4,5,6,7,8,
	 * return String
	 */
	public static String quchong(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		} else {
			String[] s = str.split(",");
			String string = new String();
			for (int i = 0; i < s.length; i++) {
				if (!string.contains(String.valueOf(s[i]))) {
					string += String.valueOf(s[i]) + ",";
				}
			}
			string = string.substring(0, string.length() - 1);
			return string;
		}
	}

	/**
	 * ������� ��ȡ �·�
	 * 
	 * @Title: getMonth
	 * @Description: TODO(������һ�仰�����������������)
	 * @param @param date ��20141111��
	 * @param @param t ���� ��
	 * @param @return
	 * @param @throws Exception �趨�ļ�
	 * @return String ��������
	 * @throws Exception
	 */
	@Deprecated
	public static String getMonth(String date, int t) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			Date dt = sdf.parse(date);
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(dt);
			rightNow.add(Calendar.MONTH, -1);// ���ڵļ���
			Date dt1 = rightNow.getTime();
			String reStr = sdf.format(dt1);
			return reStr;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public enum queryStr {
		// ���ù��캯���
		RED(1), GREEN(3), YELLOW(2);

		// ����˽�б���
		private int code;

		// ���캯��ö������ֻ��Ϊ˽��
		private queryStr(int _code) {
			this.code = _code;
		}

		@Override
		public String toString() {
			return String.valueOf(this.code);
		}
	}

	public static String getRandomString(int length) { // length��ʾ����ַ�ĳ���
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * ��ȡ��ǰ�����ַ�
	 * 
	 * @return
	 */
	public static String getDateStr() {
		return getDateStr(DEFAULT_FORMAT);
	}

	/**
	 * ȡ�õ�ǰʱ���ַ�
	 * 
	 * @return
	 */
	public static String getDateStr(String pattern) {
		return DateTime.now().toString(pattern);
	}

	/**
	 * ��ʽ������
	 * 
	 * @param date
	 *            ����ʵ��
	 * @param pattern
	 *            ��ʽ
	 * @return
	 */
	public static String getDateStr(Date date, String pattern) {
		return DateFormatUtils.format(date, pattern);
	}

	/**
	 * ��ʽ������
	 * 
	 * @param date
	 *            ����ʵ��
	 * @return
	 */
	public static String getDateStr(Date date) {
		return DateFormatUtils.format(date, DEFAULT_FORMAT);
	}

	/**
	 * ��ȡ��ǰ���ڵ�����
	 * 
	 * @return
	 */
	public static String getDateYYYYMM() {
		return getDateStr("yyyyMM");
	}

	/**
	 * �����ַ�ת����Date
	 * 
	 * @param dateStr
	 *            �����ַ�
	 * @param pattern
	 *            ���ڸ�ʽ
	 * @return
	 * @throws Exception
	 */
	public static Date parse(String dateStr, String pattern) {
		DateTimeFormatter format = DateTimeFormat.forPattern(pattern);
		return DateTime.parse(dateStr, format).toDate();
	}

	/**
	 * ȡ����һ��
	 * 
	 * @param dateStr
	 *            �����ַ�
	 * @param sourcePattern
	 *            ��������ڸ�ʽ
	 * @param resultPattern
	 *            ����֮������ڸ�ʽ
	 * @return
	 */
	public static String getNextDay(String dateStr, String sourcePattern,
			String resultPattern) {
		return getAfterDay(dateStr, 1, sourcePattern, resultPattern);
	}

	/**
	 * ȡ����һ��
	 * 
	 * @param dateStr
	 *            �����ַ�
	 * @return
	 */
	public static String getNextDay(String dateStr) {
		return getAfterDay(dateStr, 1, DEFAULT_FORMAT, DEFAULT_FORMAT);
	}

	/**
	 * ȡ����һ��
	 * 
	 * @param dateStr
	 *            �����ַ�
	 * @param days
	 *            ����
	 * @param sourcePattern
	 *            ��������ڸ�ʽ
	 * @param resultPattern
	 *            ����֮������ڸ�ʽ
	 * @return
	 */
	public static String getAfterDay(String dateStr, int days,
			String sourcePattern, String resultPattern) {
		DateTimeFormatter format = DateTimeFormat.forPattern(sourcePattern);
		DateTime dateTime = DateTime.parse(dateStr, format);
		return dateTime.plusDays(days).toString(resultPattern);
	}

	/**
	 * ȡ��ǰһ��
	 * 
	 * @param dateStr
	 *            �����ַ�
	 * @return
	 */
	public static String getBeforeDay(String dateStr) {
		return getBeforeDays(dateStr, 1, DEFAULT_FORMAT, DEFAULT_FORMAT);
	}

	/**
	 * ȡ��ǰһ��
	 * 
	 * @param dateStr
	 *            �����ַ�
	 * @param days
	 *            ����
	 * @return
	 */
	public static String getBeforeDays(String dateStr, int days) {
		return getBeforeDays(dateStr, days, DEFAULT_FORMAT, DEFAULT_FORMAT);
	}

	/**
	 * ȡ��ǰһ��
	 * 
	 * @param dateStr
	 *            �����ַ�
	 * @param days
	 *            ����
	 * @param sourcePattern
	 *            ��������ڸ�ʽ
	 * @param resultPattern
	 *            ����֮������ڸ�ʽ
	 * @return
	 */
	public static String getBeforeDays(String dateStr, int days,
			String sourcePattern, String resultPattern) {
		return getAfterDay(dateStr, -days, sourcePattern, resultPattern);
	}

	/**
	 *
	 * @param dateStr
	 *            �����ַ�
	 * @param sourcePattern
	 *            ��������ڸ�ʽ
	 * @param resultPattern
	 *            ����֮������ڸ�ʽ
	 * @return
	 */
	public static String lastDayOfMonth(String dateStr, String sourcePattern,
			String resultPattern) {
		DateTimeFormatter format = DateTimeFormat.forPattern(sourcePattern);
		DateTime dateTime = DateTime.parse(dateStr, format);
		return dateTime.dayOfMonth().withMaximumValue().toString(resultPattern);
	}

	public static String firstDayOfMonth(String dateStr) {
		DateTimeFormatter format = DateTimeFormat.forPattern(DEFAULT_FORMAT);
		DateTime dateTime = DateTime.parse(dateStr, format);
		return dateTime.dayOfMonth().withMinimumValue()
				.toString(DEFAULT_FORMAT);
	}
	
	/**
	 * ��ȡ�ϸ��µĵ�һ��(����ʱ����)
	 * @return
	 */
	public static String firstDayOfLastMonth(){
		Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.MONTH, -1);
    	calendar.set(Calendar.HOUR_OF_DAY, 0);
    	calendar.set(Calendar.MINUTE, 0);
    	calendar.set(Calendar.SECOND, 0);
    	calendar.set(Calendar.DAY_OF_MONTH, 1);
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	return format.format(calendar.getTime());
	}
	
	/**
	 * ��ȡ�ϸ��µ����һ��(����ʱ����)
	 * @return
	 */
	public static String lastDayOfLastMonth(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1); 
		calendar.add(Calendar.DATE, -1);
    	calendar.set(Calendar.HOUR_OF_DAY, 23);
    	calendar.set(Calendar.MINUTE, 59);
    	calendar.set(Calendar.SECOND, 59);
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	return format.format(calendar.getTime());
	}
	
	/**
	 * ��ȡ��ǰ�µ�һ�� 
	 * @param pattern ʱ���ʽ,��""Ĭ��Ϊ"yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String firstDayOfCurrentMonth(String pattern){
		if(pattern==""){
			pattern = DEFAULT_YEAR_MON_DAY;
		}
		Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.MONTH, 0);
    	calendar.set(Calendar.HOUR_OF_DAY, 0);
    	calendar.set(Calendar.MINUTE, 0);
    	calendar.set(Calendar.SECOND, 0);
    	calendar.set(Calendar.DAY_OF_MONTH, 1);
    	SimpleDateFormat format = new SimpleDateFormat(pattern);
    	return format.format(calendar.getTime());
	}
	
	/**
	 * ��ȡ��ǰ�����һ�� 
	 * @param pattern ʱ���ʽ,��""Ĭ��Ϊ"yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String lastDayOfCurrentMonth(String pattern){
		if(pattern==""){
			pattern = DEFAULT_YEAR_MON_DAY;
		}
		Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.MONTH, 0);
    	calendar.set(Calendar.HOUR_OF_DAY, 0);
    	calendar.set(Calendar.MINUTE, 0);
    	calendar.set(Calendar.SECOND, 0);
    	calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
    	SimpleDateFormat format = new SimpleDateFormat(pattern);
    	return format.format(calendar.getTime());
	}

	/**
	 * ���ָ����
	 * 
	 * @param dateStr
	 *            �����ַ�
	 * @param sourcePattern
	 *            ��������ڸ�ʽ
	 * @param resultPattern
	 *            ����֮������ڸ�ʽ
	 * @param month
	 *            ����ǰ����󼸸���
	 * @return
	 */
	public static String getMonth(String dateStr, String sourcePattern,
			String resultPattern, int month) {
		DateTimeFormatter format = DateTimeFormat.forPattern(sourcePattern);
		DateTime dateTime = DateTime.parse(dateStr, format);
		return dateTime.plusMonths(month).toString(resultPattern);
	}
	
	/**
	 * ��ȡ��ǰʱ���timestamp
	 * @return
	 */
	public static Timestamp getNowTimesTamp(){
		return new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * ��ȡָ��ʱ���timestamp
	 * @param time
	 * @return
	 */
	public static Timestamp getTimestampByLong(long time){
		return new Timestamp(time);
	}
	
	/**
	 * ��һ���ַ�ת�������ڸ�ʽ, �ַ����ͱ����ڸ�ʽ����Ӧ 
	 * ���磺2015-09-01��Ӧyyyy-MM-dd
	 * ���磺2015-09-01 00:00:00��Ӧyyyy-MM-dd HH:mm:ss
	 * @param date      
	 * @param pattern  
	 * @return
	 */
	public static Date toDate(String date, String pattern) {
		if((""+date).equals("")){
			return null;
		}
		if(pattern == null){
			pattern = DEFAULT_YEAR_MON_DAY;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date newDate = new Date();
		try {
			newDate = sdf.parse(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return newDate;
	}
	
	/**
	 * �ַ�תΪlong�� �����ʱ���֡���
	 * ���磺2015-09-01��Ӧyyyy-MM-dd
	 * ���磺2015-09-01 00:00:00��Ӧyyyy-MM-dd HH:mm:ss
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static long strToLong(String dateStr, String pattern){
		Date date = toDate(dateStr, pattern);
		return date.getTime();
	}
	
	/**
	 * �ַ�תΪlong��
	 * @param dateStr  �����ʱ���֡���
	 * @return
	 */
	public static long strToLong(String dateStr){
		Date date = toDate(dateStr, DEFAULT_YEAR_MON_DAY);
		return date.getTime();
	}
	
	
	/**
	 * ��ȡ���������Ժ������
	 * */
	public static String getDateAddMonths(int months) {
		try {
			Calendar date = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					DEFAULT_YEAR_MON_DAY);
			date.add(Calendar.MONTH, months);
			return dateFormat.format(date.getTime());
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * ��ȡ���������Ժ������
	 * */
	public static String getDateAddDays(int days) {
		try {
			Calendar date = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					DEFAULT_YEAR_MON_DAY);
			date.add(Calendar.DAY_OF_MONTH, days);
			return dateFormat.format(date.getTime());
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * ��ȡ������ǰ�����������
	 * @param date
	 * @param days ���������ǰ������
	 * @param format ʱ���ʽ,Ϊ��ΪĬ��"yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String getDateAddDays(Date date,int days,String format) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			format = StringUtils.isNotBlank(format) ? format : DEFAULT_YEAR_MON_DAY;
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			calendar.add(Calendar.DAY_OF_MONTH, days);
			return dateFormat.format(calendar.getTime());
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * long תΪ ����
	 * @param time
	 * @return
	 */
	public static String formatLongToStr(long time, String pattern){
		if(StringUtils.isEmpty(pattern)){
			pattern = DEFAULT_YEAR_MON_DAY;
		}
		SimpleDateFormat sdf= new SimpleDateFormat(pattern);
		Date date = new Date(time);
		String sDateTime = sdf.format(date);  //�õ���ȷ����ı�ʾ��08/31/2006 21:08:00
		return sDateTime;
	}
	
	
	
	/**
	 *  ��ȡһ������֮ǰ��ʱ���
	 *  @param weeknum
	 *  ���뼸������
	 * @return
	 */
	public static Long getweektime(Integer weeknum){
		Calendar curr = Calendar.getInstance();
		curr.set(Calendar.WEEK_OF_MONTH,curr.get(Calendar.WEEK_OF_MONTH)-weeknum);
		Date date=curr.getTime();
		return date.getTime();
	}
	/**��ȡһ����֮ǰ��ʱ���
	 * @param monthnum
	 *  ���뼸����
	 * @return
	 */
	public static Long getmonthtime(Integer monthnum){
		Calendar curr = Calendar.getInstance();
		curr.set(Calendar.MONTH,curr.get(Calendar.MONTH)-monthnum);
		Date date=curr.getTime();
		return date.getTime();
	}
	
	/**��ȡһ��֮ǰ��ʱ���
	 * @param yearnum
	 *  ���뼸��
	 * @return
	 */
	public static Long getyeartime(Integer yearnum){
		Calendar curr = Calendar.getInstance();
		curr.set(Calendar.YEAR,curr.get(Calendar.YEAR)-yearnum);
		Date date=curr.getTime();
		return date.getTime();
	}
	
	/**
	 * ת��ΪĬ�ϵ�ʱ���ʽ
	 * @param times
	 * @return
	 */
	public static Long getlongtimes(String times){
		String viewtime =times;
		Date time = new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			time = sdf.parse(viewtime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		viewtime = sdf2.format(time);
		System.out.println(DateUtils.strToLong(viewtime));
		return DateUtils.strToLong(viewtime);
	}
	
	/**
	 * ��ȡ���쿪ʼʱ��
	 * @return
	 */
	public static Long getStartTime(){  
        Calendar todayStart = Calendar.getInstance();  
        todayStart.set(Calendar.HOUR, 0);  
        todayStart.set(Calendar.MINUTE, 0);  
        todayStart.set(Calendar.SECOND, 0);  
        todayStart.set(Calendar.MILLISECOND, 0);  
        return todayStart.getTime().getTime();  
    }  
     
	/**
	 * ��ȡ�������ʱ��
	 * @return
	 */
	public static Long getEndTime(){  
        Calendar todayEnd = Calendar.getInstance();  
        todayEnd.set(Calendar.HOUR, 23);  
        todayEnd.set(Calendar.MINUTE, 59);  
        todayEnd.set(Calendar.SECOND, 59);  
        todayEnd.set(Calendar.MILLISECOND, 999);  
        return todayEnd.getTime().getTime();  
    }  
	
	/**
	 * ��ȡ����������,������Ϊ0
	 * @return
	 */
	public static int getWeek(){
		Calendar cal=Calendar.getInstance();
    	int week=cal.get(Calendar.DAY_OF_WEEK)-1;
    	return week;
	}
	
	/**
	 * ��ȡ��������Ϊ����
	 * @return
	 */
	public static int getDayOfMonth(){
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * ��ȡ����һʱ��(����ʱ����)
	 * @return
	 */
	public static String getLastMonday(){
		Calendar cal =Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT_YYYY_MM_DD); 
		cal.add(Calendar.WEEK_OF_MONTH, -1);
        cal.set(Calendar.DAY_OF_WEEK, 2);
        return df.format(cal.getTime());
	}
	
	/**
	 * ��ȡ������ʱ��(����ʱ����)
	 * @return
	 */
	public static String getLastSunday(){
		Calendar cal =Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(DEFAULT_FORMAT_YYYY_MM_DD); 
		cal.set(Calendar.DAY_OF_WEEK, 1);
        return df.format(cal.getTime());
	}
	
	/**
	 * �������ַ�ת��Ϊ���� ��ʽ
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * ��ȡ����ʱ�� yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getnowDate(){
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date=sdf2.format(new Date());
		return date;
	}
	
	/**
	 * timestampת��long
	 * @param time
	 * @return
	 */
	public static Long getLongToTimestamp(Timestamp time){
		Long longTime = time.getTime();
		return longTime;
	}
	
	public static void main(String[] args) throws Exception {
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		// String str = "201411";
		// Date dt = sdf.parse(str);
		// Calendar rightNow = Calendar.getInstance();
		// rightNow.setTime(dt);
		// // rightNow.add(Calendar.YEAR,-1);//���ڼ�1��
		// rightNow.add(Calendar.MONTH, -1);// ���ڼ�3����
		// // rightNow.add(Calendar.DAY_OF_YEAR,10);//���ڼ�10��
		// Date dt1 = rightNow.getTime();
		// String reStr = sdf.format(dt1);
		// System.out.println(queryStr.RED);
		//
		// System.out.println(DataUtil.quchong("1111,22,1,2,11,1"));
		// System.out.println(getNextDay_YYYYMMDD("20140409"));
		//getRandomString(8);
		
		//getMonth("2015-11-01 00:00:00","yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm:ss",6);
//		System.out.println(strToLong("2015-09-14", DEFAULT_FORMAT_YYYY_MM_DD));
//		System.out.println(System.currentTimeMillis());
//		System.out.println("2015-09-14 00:00:00".length());
//		System.out.println(formatLongToStr(System.currentTimeMillis(), null));
//		Long aa = Long.parseLong("1442232291269");
//		Date date = new Date(aa);
//		System.out.println("date"+new Date());
//		
//		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println("dated"+DateUtils.getDateStr("yyyyMMddHHmmssSSS")+NumberUtils.getRandomNumber());
//		System.out.println(new Timestamp(aa));
//		System.out.println(System.currentTimeMillis());
		//System.out.println(formatLongToStr(System.currentTimeMillis(),""));
		//System.out.println("ʱ�䣺"+strToLong((getMonth(formatLongToStr(System.currentTimeMillis(),"") ,"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm:ss",6))));
		//gettimes("2015/11/26 6:23:45");
		//System.out.println("month"+getmonthtime(6));
//		System.out.println(getTimestampByLong(1448967358546l));
//		System.out.println(getTimestampByLong(getweektime(1)));
//		System.out.println(getweektime(1));
//		System.out.println(getmonthtime(1));
		
		Date date  = new Date();
//		System.out.println(getDateAddDays(date,7,""));
		
		
		System.out.println(firstDayOfCurrentMonth("yyyy-MM-dd"));
	}
	
	
}

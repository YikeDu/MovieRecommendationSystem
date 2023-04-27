package com.example.dxw.https;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class dxw {


    public static double average(double[] nums) {
        double num = 0.0;
        for (int i = 0; i < nums.length; i++) {
            num += nums[i];
        }
        return num / nums.length;
    }


    public static Double average(List<Double> nums) {
        Double num = 0.0;
        for (int i = 0; i < nums.size(); i++) {
            num += nums.get(i);
        }
        double v = num / nums.size();
        BigDecimal b = new BigDecimal(v);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }



    public static String[] StringSz(Integer[] integers) {
        String[] sz = new String[integers.length];
        for (int i = 0; i < integers.length; i++) {
            if (integers[i] != 0) {
                sz[i] = String.valueOf(integers[i]);
            } else {
                sz[i] = "";
            }
        }
        return sz;
    }


    public static String[] stringsNewline(String[] str) {
        for (String s : str) {
            System.out.print("\"" + s + "\",");
        }
        return null;
    }


    public static String[] newline(int[] str) {
        for (int s : str) {
            System.out.print(s + ",");
        }
        return null;
    }

    public static String rqzfc() {
        Date time = new Date(); // 获取当前时间
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(time);
        return strDate;
    }


    public static String rqzfc(Date date, String yyMMddhhmmss) {
        DateFormat dateFormat = new SimpleDateFormat(yyMMddhhmmss);
        String strDate = dateFormat.format(date);
        return strDate;
    }


    public static long ms(Integer s) {
        long i = 1l * s * 24 * 60 * 60 * 1000;
        return i;
    }


    public static String[] dayNum(Integer s) {
        long endDate = new Date().getTime() + 1l * 24 * 60 * 60 * 1000;
        long startDate = endDate - ms(s);
        String end = sdf2(endDate);
        String start = sdf2(startDate);
        return new String[]{start, end};
    }


    public static Date sdf(long s) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date time = new Date(); // 获取当前时间
        time.setTime(s);
//        String format = sdf.format(s);// 格式化时间
        return time;
    }


    public static String sdf2(long s) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date time = new Date(); // 获取当前时间
//        time.setTime(s);
        String format = sdf.format(s);// 格式化时间
        return format;
    }


    public static Date sdf3(String date, String yyyyMMdd) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd);
        Date parse = null;
        try {
            parse = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }


    public static List<String> sdf4(String time1, String time2, String yyyyMMdd) {

        long l = (dxw.sdf3(time2, yyyyMMdd).getTime() - dxw.sdf3(time1, yyyyMMdd).getTime()) / 86400000;
        String s = dxw.sdf2(dxw.sdf3(time1, yyyyMMdd).getTime() + 86400000);
        int timeunm = Math.toIntExact(l);
        List<String> repairtarget = new ArrayList<>();
        for (int i = 0; i <= timeunm; i++) {
            long aa = 86400000;
            String s1 = dxw.sdf2(dxw.sdf3(time1, yyyyMMdd).getTime() + aa * i);
            repairtarget.add(s1);
        }
        return repairtarget;
    }

    public static Date sdf5(long s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date time = new Date(s); // 获取当前时间
//        time.setTime(s);
        String format = sdf.format(s);// 格式化时间
        return time;
    }


    public static String sdf6(String s, String e) {
        String dateStr1 = "2022-05-01 " + s + ":00";
        Date date1 = DateUtil.parse(dateStr1);
        String dateStr2 = "2022-05-01 " + e + ":00";
        Date date2 = DateUtil.parse(dateStr2);
        long betweenDay = 0;
        if (date1.getTime() < date2.getTime()) {
            betweenDay = DateUtil.between(date1, date2, DateUnit.MINUTE);
        } else {
            long l = date2.getTime() + 86400000;
            Date date = new Date(l);
            betweenDay = DateUtil.between(date1, date, DateUnit.MINUTE);
        }
        String allTime = String.valueOf(betweenDay);
        return allTime;
    }


    public static long now() {
        long l = System.currentTimeMillis();
        return l;
    }

    public static long now2(long l) {
        long l2 = System.currentTimeMillis() - l;
        long l1 = l2 / 1000;
        System.out.println("时间差(单位:毫秒秒): " + l2);
        System.out.println("时间差(单位:秒): " + l1);
        return l1;
    }

    //    让程序休眠多少秒
    public static void sleep(long millis) {
        try {
            Thread.currentThread().sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static String week(String productionNext) {
        String returndata = null;
        switch (productionNext) {
            case "星期一":
                returndata = "1";
                break;
            case "星期二":
                returndata = "2";
                break;
            case "星期三":
                returndata = "3";
                break;
            case "星期四":
                returndata = "4";
                break;
            case "星期五":
                returndata = "5";
                break;
            case "星期六":
                returndata = "6";
                break;
            case "星期日":
                returndata = "7";
                break;
            default:
                returndata = "星期有问题";
                break;
        }
        return returndata;
    }


    public static String arrayLine(int[] code, int[] location) {
        for (int i : code) {
            System.out.print(i + ",");
        }
        for (int i : location) {
            System.out.print(i + ",");
        }
        return null;
    }



    public static String diyyue() {
        //获取前月的第一天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");

        Calendar cale2 = Calendar.getInstance();//获取当前日期
        cale2.add(Calendar.MONTH, -1);
        cale2.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String firstDay = format.format(cale2.getTime()) + "%";
        return firstDay;
    }

    public static String yesterday() {
        return dxw.sdf2(System.currentTimeMillis() - 86400000);
    }

    public static int yestermonth() {
        long l = System.currentTimeMillis() - 86400000;
        Date time = new Date(); // 获取当前时间
        time.setTime(l);
        return time.getMonth();
    }



    public static int positioning(String[] names, String name) {
        int a = 0;
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(name)) {
                a = i;
                break;
            }
        }
        return a;
    }


    public static Integer integerNotNull(Integer o) {
        if (o == null) {
            o = 0;
        }
        return o;
    }

    public static String integerNotNull(String o) {
        if (o == null) {
            o = "";
        }
        return o;
    }

    public static String strNotNull(Object o) {
        if (o == null) {
            String a = "";
            return a;
        }
        return o.toString();

    }

    public static int integerNotNull2(Object o) {
        if (o == null) {
            return 0;
        }
        return (Integer) o;
    }

    public static boolean integerNotNull2(String o) {
        if (o == null) {
            return false;
        }
        return true;
    }


    public static void deBug() {
        System.out.println("到这里了");
    }


    public static void deBug(Object o) {
        System.out.println(o);
    }


    public static String cuttingSorting(String cutting, String str) {
        if (str.equals("")) return "";
        String[] split = str.split(cutting);
        String str2 = "";
        for (int i = split.length - 1; i > -1; i--) {
            str2 += split[i] + "; ";
        }
        return str2;
    }


    public static String sdf5() {
        GregorianCalendar calendar = new GregorianCalendar();
        int minute = calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE);
        String time = "";
        if (minute < 510) {//计算昨天的出库入库(大线入库/钣金返修入库),查询,零件号,
            time = DateUtil.formatDate(DateUtil.yesterday());
        } else {//计算当天天出库入库
            time = DateUtil.today();
        }//这里耦合每天出入库
        return time;
    }


    public static Integer[] sdf7nowMonth() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        Integer[] yn = {year, month};
//        // 获取当前年
//int year = calendar.get(Calendar.YEAR);
//// 获取当前月
//int month = calendar.get(Calendar.MONTH) + 1;
//// 获取当前日
//int day = calenedar.get(Calendar.DATE);
//// 获取当前小时
//int hour = calendar.get(Calendar.HOUR_OF_DAY);
//// 获取当前分钟
//int minute = calendar.get(Calendar.MINUTE);
//// 获取当前秒
//int second = calendar.get(Calendar.SECOND);
//// 获取当前是本周第几天
//int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
//// 获取当前是本月第几天
//int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//// 获取当前是本年第几天
//int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
//SimpleDateFormat format = new SimpleDateFormat(“yyyy-MM-dd”);
//// 获取当月第一天
//calendar = Calendar.getInstance();
//calendar.add(Calendar.MONTH, 0);
//calendar.set(Calendar.DAY_OF_MONTH, 1);
//String firstday = format.format(calendar.getTime());
//// 获取当月最后一天
//calendar = Calendar.getInstance();
//calendar.add(Calendar.MONTH, 1);
//calendar.set(Calendar.DAY_OF_MONTH, 0);
//String lastday = format.format(calendar.getTime());
        return yn;
    }


    public static Integer sdf7(Integer year, Integer month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天

        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }


    public static Object sdf8(Integer year, Integer month) {
        Integer day = sdf7(year, month);
        List<Object> linkedList = new LinkedList<>();
        for (Integer i = 0; i < day; i++) {
            linkedList.add(i + 1);
        }
        return linkedList;
    }


    public static List<Object> sdf11(Integer year, Integer month) {
        Integer day = sdf7(year, month);
        List<Object> linkedList = new LinkedList<>();
        for (Integer i = 0; i < day; i++) {
            linkedList.add(0);
        }
        return linkedList;
    }


    public static Object sdf9(Integer year, Integer month) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Integer.valueOf(month) - 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return sdf.format(c.getTime());
    }

    public static Object sdf10(Integer year, Integer month) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Integer.valueOf(month) - 1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return sdf.format(c.getTime());
    }


    public static Date sdf12(String dateStr) {
//         dateStr = "2017-03-05";
        Date date = DateUtil.parse(dateStr);
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        ca.set(Calendar.HOUR, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        String format = DateUtil.format(ca.getTime(), "yyyy-MM-dd");
        return ca.getTime();
    }


    public static Date sdf13(String dateStr) {
//        String dateStr = "2017-03-05";
        Date date = DateUtil.parse(dateStr);
        DateTime newDate2 = DateUtil.offsetDay(date, -1);
        return newDate2;
    }

    public static String sdfbc() {
        Calendar cal = Calendar.getInstance();
//int y=cal.get(Calendar.YEAR);
//int m=cal.get(Calendar.MONTH);
//int d=cal.get(Calendar.DATE);
        int h = cal.get(Calendar.HOUR_OF_DAY);
        int mi = cal.get(Calendar.MINUTE);
//int s=cal.get(Calendar.SECOND);
//System.out.println("现在时刻是"+y+"年"+m+"月"+d+"日"+h+"时"+mi+"分"+s+"秒");
//        获取当前分钟数.
        String bcz = "";
        Integer minutes = h * 60 + mi;
        if (minutes > 90 && minutes <= 510) {
            bcz = "三班";
        } else if (minutes > 510 && minutes <= 1020) {
            bcz = "白班";
        } else {
            bcz = "二班";
        }
        return bcz;
    }

    //    动态ip
    public static LinkedList<Object> dynamicIp() throws Exception {
        LinkedList<Object> ipList = new LinkedList<>();
        Enumeration<NetworkInterface> interfs = NetworkInterface.getNetworkInterfaces();
        int i = 0;
        while (interfs.hasMoreElements()) {
            NetworkInterface interf = interfs.nextElement();
            Enumeration<InetAddress> addres = interf.getInetAddresses();
            boolean b = addres.hasMoreElements();
            while (addres.hasMoreElements()) {
                InetAddress in = addres.nextElement();
                if (in instanceof Inet4Address) {
                    String hostAddress = in.getHostAddress();
                    ipList.add(hostAddress);
                } else if (in.getHostAddress().indexOf("2409") != -1 && in instanceof Inet6Address) {
//                    //      "v6:" + in.getHostAddress());
                    i++;
                }
            }
        }
        return ipList;
    }

    //    判断是本机就返回ture
    public static boolean localhost(String ip) throws Exception {
        LinkedList<Object> objects = dynamicIp();
        for (Object object : objects) {
            if (object.equals(ip)) {
                return true;
            }
        }
        return false;
    }

    public static Boolean pdIp() throws Exception {
        boolean localhost = dxw.localhost("10.238.0.10");
        return localhost;
    }

    //    执行批处理 "D:/1.bat",只需要传入绝对路劲
    public String executeBat(String strcmd) throws Exception {

        Runtime rt = Runtime.getRuntime(); //Runtime.getRuntime()返回当前应用程序的Runtime对象
        Process ps = null;  //Process可以控制该子进程的执行或获取该子进程的信息。
        try {
            ps = rt.exec(strcmd);   //该对象的exec()方法指示Java虚拟机创建一个子进程执行指定的可执行程序，并返回与该子进程对应的Process对象实例。
            ps.waitFor();  //等待子进程完成再往下执行。
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int i = ps.exitValue();  //接收执行完毕的返回值
        String status = "0";
        if (i == 0) {
            System.out.println("execution is completed");
            status = "1";
        } else {
            System.out.println("Exec failure.");
        }
        ps.destroy();  //销毁子进程
        ps = null;
        return status;
    }

    //    从报文中获取cid和ip地址
    public static LinkedHashMap getcidAndip(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String authorization = request.getHeader("Authorization");
        String[] token = authorization.split(" ");
        String cidqx = token[1];
        LinkedHashMap map = new LinkedHashMap();
        map.put("cid", cidqx);
        map.put("ip", remoteAddr);
        return map;
    }

    //    判断该对象是否为null,如果是,则返回空字符串,不是则之间返回该对象转化字符串本身,
    public static String isNollStr(Object o) throws Exception {
        if (o == null) return "";
        return o.toString();
    }

    //    返回当前时间的创建时间
    public static String creatStr() throws Exception {
        return DateUtil.today();
    }

    //    返回当前时间的创建时间带毫秒值
    public static String creatStrMin() throws Exception {
        return DateUtil.now();
    }



}

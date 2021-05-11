package com.ckunion.constant;
import java.util.Iterator;
/**
 * Description:
 *
 * @Date: 2021/4/29 16:19
 * @Author: wuj@ckunion.com
 */
public class CkuCommons {

    /**
     * 将集合转换为字符串，逗号分隔
     * @param i
     * @return
     */
    public static <T> String listToStr(Iterator<T> i) {
        if (!i.hasNext())
            return "";
        StringBuilder sb = new StringBuilder();
        for (;;) {
            T e = i.next();
            sb.append(e);
            if (!i.hasNext())
                return sb.toString();
            sb.append(",");
        }
    }

    /**
     * 将集合转换为字符串(带有单引号)，逗号分隔
     * @param i
     * @return
     */
    public static <T> String listToSymStr(Iterator<T> i) {
        if (!i.hasNext())
            return "";
        StringBuilder sb = new StringBuilder();
        for (;;) {
            T e = i.next();
            sb.append("'"+e+"'");
            if (!i.hasNext())
                return sb.toString();
            sb.append(",");
        }
    }

    //统一获取版本号方法
    public static String getVersionNum(String verNum, String bigVer){

        String bef = "";
        int aff = 0;

        String[] vers = verNum.split("[.]");
        if(vers.length==1){
            bef = vers[0];
        }else{
            bef = vers[0];
            aff = Integer.parseInt(vers[1]);
        }

        //是否大版本升级
        if("Y".equals(bigVer)){
            char chr1 = bef.charAt(0);
            String newBef = String.valueOf((char)(chr1 +1));
            return newBef+".1";
        }else{
            aff = aff+1;
            return bef+"."+aff;
        }

    }

}

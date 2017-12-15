package org.vlis.dog.util;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author thinking_fioa
 * @createTime 2017/12/15
 * @description 对数字的规格化
 */


public final class NumberUtil {

    private static final String NUMBER_PATTER = "-?[0-9]?.?[0-9]+";
    private static final Character ZERO = '0';
    private static final Character FIVE = '5';
    private static final Character NINE = '9';

    /**
     * 模糊化指标，考虑多个告警类型的Feature字段，是具体的值，所以采用模糊化具体指标。
     * 目前方案是指标个位数的值[0-4] -> 0。 [5-9] -> 5
     * 比如告警子类型为: CpuSystemUsage的Feature字段值为93，模糊啊化成90。字段值为97的模糊化成95。目前只定义两个这样的阶段。
     * @param quota 指标数字
     * @return 末尾映射,如112 -> 110，384 -> 380，987 -> 985
     */
    public static String obfuscationUsageQuotaOfFeature(String quota) {
        if(quota == null || !isNumeric(quota)) {
            throw new IllegalArgumentException("parameter is error.");
        }
        String modifyQuota="";
        if(quota.contains(".")) {
            quota = quota.substring(0, quota.indexOf("."));
        }
        // 个位数为0-4之间的，换成'0'
        if(ZERO<=quota.charAt(quota.length()-1) && quota.charAt(quota.length()-1) <FIVE) {
            if(quota.length() == 1) {
                modifyQuota = "0";
            }else {
                modifyQuota = quota.substring(0, quota.length()-1)+"0";
            }

        }
        // 个位数为5-9之间的，换成'5'
        if(FIVE<=quota.charAt(quota.length()-1) && quota.charAt(quota.length()-1) <NINE) {
            if(quota.length() == 1) {
                modifyQuota = "5";
            }else {
                modifyQuota = quota.substring(0, quota.length()-1)+"5";
            }
        }

        return modifyQuota;
    }

    /**
     * 判断指标是否是数字，包括正数，负数，小数等
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        if(null == str || str.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(NUMBER_PATTER);
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }


}

package org.vlis.dog.config;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description
 */


final class ConfigFileHelper {
    private static final Pattern BOOLEAN_PATTERN = Pattern.compile("^(?:true|True|TRUE|false|False|FALSE)$");
    private static final Pattern FLOAT_PATTERN = Pattern.compile("^(?:[-+]?(?:[0-9][0-9_]*)\\.[0-9_]*(?:[eE][-+][0-9]+)?|[-+]?(?:[0-9][0-9_]*)?\\.[0-9_]+(?:[eE][-+][0-9]+)?|[-+]?[0-9][0-9_]*(?::[0-5]?[0-9])+\\.[0-9_]*|[-+]?\\.(?:inf|Inf|INF)|\\.(?:nan|NaN|NAN))$");
    private static final Pattern INTEGER_PATTERN = Pattern.compile("^(?:[-+]?0b[0-1_]+|[-+]?0[0-7_]+|[-+]?(?:0|[1-9][0-9_]*)|[-+]?0x[0-9a-fA-F_]+|[-+]?[1-9][0-9_]*(?::[0-5]?[0-9])+)$");

    /**
     * 将Properties变成 Key-Value的Map队
     * @param pros Properties
     * @return {@code Map<String, Object> }
     * @throws IOException 异常抛出
     */
    public static Map<String, Object> getConfigSettings(Properties pros) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        for (Entry<Object, Object> entry : pros.entrySet()) {
            String key = (String) entry.getKey();
            Object value = stringToObject((String) pros.get(key));
            map.put(key, value);
        }

        return map;
    }

    /**
     * String 转换成Object对象
     * @param str 待转换的String
     * @return Object返回对象
     */
    private static Object stringToObject(String str) {
        str = str.trim();
        if (BOOLEAN_PATTERN.matcher(str).matches()) {
            return Boolean.valueOf(str);
        }
        if (FLOAT_PATTERN.matcher(str).matches()) {
            return Float.valueOf(str);
        }
        if (INTEGER_PATTERN.matcher(str).matches()) {
            return Integer.valueOf(Integer.parseInt(str));
        }
        return str;
    }
}

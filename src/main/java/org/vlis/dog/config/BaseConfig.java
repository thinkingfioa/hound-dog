package org.vlis.dog.config;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description
 */


final class BaseConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseConfig.class.getName());

    /**
     * 不区分value类型
     * @param props 属性值
     * @param key 关键字key
     * @param defaultVal 缺省值
     * @return 如果key对应的value是null，则返回缺省值
     */
    public static Object getProperty(Map<String, Object> props, String key, Object defaultVal) {
        Object propVal = props.get(key);
        if(propVal == null) {
            return defaultVal;
        }

        if(propVal instanceof String) {
            return ((String)propVal).trim();
        }
        return propVal;
    }

    /**
     * String类型 if key==null,then valueOf(Object obj) return a String equals "null"
     * @param props 属性值
     * @param key 关键字key
     * @param defaultVal 缺省值
     * @return 如果key对应的value是null，则返回缺省值
     */
    public static String getStringProperty(Map<String, Object> props, String key, String defaultVal) {

        String propVal = String.valueOf(props.get(key));
        if("null".equals(propVal)) {
            LOGGER.info("value of "+key+" is null,will return default value");
            propVal=null;
        }
        if(propVal == null) {
            return defaultVal;
        }

        return propVal.trim();
    }

    /**
     * Int类型
     * @param props 属性值
     * @param key 关键字key
     * @param defaultVal 缺省值
     * @return 如果key对应的value是null，则返回缺省值
     */
    public static int getIntProperty(Map<String, Object> props, String key, int defaultVal) {
        Number val = (Number) props.get(key);
        if (val == null) {
            return defaultVal;
        }

        return val.intValue();
    }

    /**
     * Boolean类型
     * @param props 属性值
     * @param key 关键字key
     * @param defaultVal 缺省值
     * @return 如果key对应的value是null，则返回缺省值
     */
    public static boolean getBooleanProperty(Map<String, Object> props, String key, Boolean defaultVal) {
        Boolean val = (Boolean) props.get(key);
        if (val == null) {
            return defaultVal;
        }

        return val.booleanValue();
    }
}

package org.vlis.dog.util;

import org.vlis.dog.bean.DataWrapperBean;
import org.vlis.dog.bean.MapWarningDataBean;
import org.vlis.dog.bean.WarningBean;
import org.vlis.dog.constant.WarningEnum;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author thinking_fioa
 * @createTime 2017/11/21
 * @description 提供提取数据的工具类
 */


public final class WarningDataExtractUtil {

    /**
     * 提取告警信息，按照提供的{@see org.vlis.dog.constant.WarningEnum}来提取
     * @param pendingDataBean 原始数据
     * @param warningBeanEnum 提取类型
     */
    public static MapWarningDataBean extractWarningDataBean(DataWrapperBean pendingDataBean, WarningEnum warningBeanEnum) {
        if(null == pendingDataBean) {
            throw new NullPointerException("pendingDataBean is null.");
        }

        MapWarningDataBean extractAlarmTypeMap = new MapWarningDataBean();
        Map<String, List<WarningBean>> pendingWarningBeanMap = ((MapWarningDataBean)pendingDataBean).getDataBeans();

        Set<Map.Entry<String, List<WarningBean>>> pendingWarningBeanSet = pendingWarningBeanMap.entrySet();
        for(Map.Entry<String, List<WarningBean>> onePendingWarningBean : pendingWarningBeanSet) {
            String key = onePendingWarningBean.getKey();
            if(key.startsWith(warningBeanEnum.getAlarmType())) {
                extractAlarmTypeMap.addWarningDataBeanList(key, onePendingWarningBean.getValue());
            }
        }

        return extractAlarmTypeMap;
    }

}

package org.vlis.dog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.bean.WarningBean;
import org.vlis.dog.bloomfilter.BloomFilter;
import org.vlis.dog.constant.WarningEnum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author thinking_fioa
 * @createTime 2017/11/22
 * @description 判断重复性算法
 */


public final class RedundancyAlgorithmUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedundancyAlgorithmUtil.class);

    /**
     * 判断集合WarningBeanList是否冗余
     * 主要根据5种类型的告警数据: SqlExecute、sqlConnection、exception、statusCode、call。
     * @param warningBeanList 告警集合
     * @return true 则表示重复，false 则表示非重复。
     */
    public static  boolean isRedundancyWarningList(BloomFilter<String> filter, List<WarningBean> warningBeanList) {
        if(null == filter || null == warningBeanList || warningBeanList.isEmpty()) {
            return false;
        }

        //分类
        List<WarningBean> sqlExecuteWarningList = new ArrayList<WarningBean>();
        List<WarningBean> sqlConnectionWarningList = new ArrayList<WarningBean>();
        List<WarningBean> exceptionWarningList = new ArrayList<WarningBean>();
        List<WarningBean> statusCodeWarningList = new ArrayList<WarningBean>();
        List<WarningBean> callWarningList = new ArrayList<WarningBean>();

        for(WarningBean oneWarningBean : warningBeanList) {
            if(WarningEnum.SQL_EXECUTE.equals(oneWarningBean.getAlarmType())){
                sqlExecuteWarningList.add(oneWarningBean);
            }
            if(WarningEnum.SQL_CONNECTION.equals(oneWarningBean.getAlarmType())){
                sqlConnectionWarningList.add(oneWarningBean);
            }
            if(WarningEnum.APPLICATION_EXCEPTION.equals(oneWarningBean.getAlarmType())){
                exceptionWarningList.add(oneWarningBean);
            }
            if(WarningEnum.APPLICATION_STATUS_CODE.equals(oneWarningBean.getAlarmType())){
                statusCodeWarningList.add(oneWarningBean);
            }
            if(WarningEnum.APPLICATION_CALL.equals(oneWarningBean.getAlarmType())){
                callWarningList.add(oneWarningBean);
            }
        }

        //排序
        Collections.sort(sqlExecuteWarningList, WarningBeanComparator.getInstance());
        Collections.sort(sqlConnectionWarningList, WarningBeanComparator.getInstance());
        Collections.sort(exceptionWarningList, WarningBeanComparator.getInstance());
        Collections.sort(statusCodeWarningList, WarningBeanComparator.getInstance());
        Collections.sort(callWarningList, WarningBeanComparator.getInstance());

        StringBuilder keyStringBuilder = new StringBuilder();
        genereteRedundancyKey(keyStringBuilder, sqlExecuteWarningList);
        genereteRedundancyKey(keyStringBuilder, sqlConnectionWarningList);
        genereteRedundancyKey(keyStringBuilder, exceptionWarningList);
        genereteRedundancyKey(keyStringBuilder, statusCodeWarningList);
        genereteRedundancyKey(keyStringBuilder, callWarningList);

        String specificKey = keyStringBuilder.toString();

        if( !filter.contains(specificKey)) {
            filter.add(specificKey);
            return false;
        }

        return true;
    }

    /**
     * 判断单个WarningBean是否冗余
     * @param warningBean 单个告警
     * @return true表示冗余，false 则不冗余。
     */
    @SuppressWarnings("unused")
    public static <T> boolean isRedundancyWarning(BloomFilter<T> filter, WarningBean warningBean) {
        //todo:: 提供具体逻辑，目前没有接口调用
        return false;
    }

    /**
     * 生成校验的唯一key, 输入是多个告警信息
     * @param warningBeanList  告警信息集合
     * @return {@code String}
     */
    public static void genereteRedundancyKey(StringBuilder sb, List<WarningBean> warningBeanList) {
        if(null == sb || null == warningBeanList || warningBeanList.isEmpty()) {
            throw new NullPointerException("parameter is null.");
        }

        for(WarningBean oneWarningBean : warningBeanList) {
            geneteRedundancyKey(sb, oneWarningBean);
        }
    }

    /**
     * 生成校验的唯一key, 输入是单个告警信息
     * 根据不同的类型，提取不同属性作为的特征值，作为key
     * 1. SQL_EXECUTE: applicationKey、destinationIp、destinationPort、feature(sql语句)
     * 2. SQL_CONNECTION: applicationKey、url、feature(连接语句)
     * 3. APPLICATION_EXCEPTION:applicationkey、url、feature(sql语句)
     * 4. APPLICATION_STATUS_CODE:applicationkey、url、feature(sql语句)
     * 5. APPLICATION_CALL:applicationkey、destinationIp、destinationPort、url、feature(sql语句)
     * @param sb {@link StringBuilder}
     * @param warningBean  告警信息集合
     */
    private static void geneteRedundancyKey(StringBuilder sb, WarningBean warningBean) {
        if(WarningEnum.SQL_EXECUTE.equals(warningBean.getAlarmType())){
            sb.append(warningBean.getApplicationKey())
                    .append(warningBean.getDestinationIp())
                    .append(warningBean.getDestinationPort())
                    .append(warningBean.getFeature());
        }
        if(WarningEnum.SQL_CONNECTION.equals(warningBean.getAlarmType())){
            sb.append(warningBean.getApplicationKey())
                    .append(warningBean.getUrl())
                    .append(warningBean.getFeature());
        }
        if(WarningEnum.APPLICATION_EXCEPTION.equals(warningBean.getAlarmType())){
            sb.append(warningBean.getApplicationKey())
                    .append(warningBean.getUrl())
                    .append(warningBean.getFeature());
        }
        if(WarningEnum.APPLICATION_STATUS_CODE.equals(warningBean.getAlarmType())){
            sb.append(warningBean.getApplicationKey())
                    .append(warningBean.getUrl())
                    .append(warningBean.getFeature());
        }
        if(WarningEnum.APPLICATION_CALL.equals(warningBean.getAlarmType())){
            sb.append(warningBean.getApplicationKey())
                    .append(warningBean.getDestinationIp())
                    .append(warningBean.getDestinationPort())
                    .append(warningBean.getUrl())
                    .append(warningBean.getFeature());
        }
    }

}

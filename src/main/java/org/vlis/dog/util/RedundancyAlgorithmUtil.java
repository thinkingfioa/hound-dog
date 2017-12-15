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
     * 该方法可以被下面的代替
     * 判断集合WarningBeanList是否冗余
     * 主要根据5种类型的告警数据: SqlExecute、sqlConnection、exception、statusCode、call。
     * @param warningBeanList 告警集合
     * @return true 则表示重复，false 则表示非重复。
     */
    @Deprecated
    public static  boolean isRedundancyWarningListBackup(BloomFilter<String> filter, List<WarningBean> warningBeanList) {
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
            if(WarningEnum.SQL_EXECUTE.getAlarmType().equals(oneWarningBean.getAlarmType())){
                sqlExecuteWarningList.add(oneWarningBean);
            }
            if(WarningEnum.SQL_CONNECTION.getAlarmType().equals(oneWarningBean.getAlarmType())){
                sqlConnectionWarningList.add(oneWarningBean);
            }
            if(WarningEnum.APPLICATION_EXCEPTION.equals(oneWarningBean.getAlarmType())){
                exceptionWarningList.add(oneWarningBean);
            }
            if(WarningEnum.APPLICATION_STATUS_CODE.getAlarmType().equals(oneWarningBean.getAlarmType())){
                statusCodeWarningList.add(oneWarningBean);
            }
            if(WarningEnum.APPLICATION_CALL.getAlarmType().equals(oneWarningBean.getAlarmType())){
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
        genereteRedundancyKey(WarningEnum.SQL_EXECUTE.getAlarmType(), keyStringBuilder, sqlExecuteWarningList);
        genereteRedundancyKey(WarningEnum.SQL_CONNECTION.getAlarmType(), keyStringBuilder, sqlConnectionWarningList);
        genereteRedundancyKey(WarningEnum.APPLICATION_EXCEPTION.getAlarmType(), keyStringBuilder, exceptionWarningList);
        genereteRedundancyKey(WarningEnum.APPLICATION_STATUS_CODE.getAlarmType(), keyStringBuilder, statusCodeWarningList);
        genereteRedundancyKey(WarningEnum.APPLICATION_CALL.getAlarmType(), keyStringBuilder, callWarningList);

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
    public static boolean isRedundancyWarning(BloomFilter<String> filter, WarningBean warningBean) {
        StringBuilder keyStringBuilder = new StringBuilder();
        geneteRedundancyKey(keyStringBuilder, warningBean);
        String specificKey = keyStringBuilder.toString();

        if( !filter.contains(specificKey)) {
            filter.add(specificKey);
            return false;
        }

        return true;
    }

    /**
     * 判断多个告警信息
     * 判断集合WarningBeanList是否冗余
     * 根据12种类型的告警数据判断是否重复
     * @param warningBeanList 告警集合
     * @return true 则表示重复，false 则表示非重复。
     */
    public static boolean isRedundancyWarningList(BloomFilter<String> filter, List<WarningBean> warningBeanList) {
        if(null == filter || null == warningBeanList || warningBeanList.isEmpty()) {
            return false;
        }

        //分类
        List<WarningBean> sqlExecuteWarningList = new ArrayList<WarningBean>();
        List<WarningBean> sqlConnectionWarningList = new ArrayList<WarningBean>();
        List<WarningBean> exceptionWarningList = new ArrayList<WarningBean>();
        List<WarningBean> statusCodeWarningList = new ArrayList<WarningBean>();
        List<WarningBean> callWarningList = new ArrayList<WarningBean>();
        List<WarningBean> cpuSystemUsageList = new ArrayList<WarningBean>();
        List<WarningBean> cpuUserUsageList = new ArrayList<WarningBean>();
        List<WarningBean> cpuLoad5List = new ArrayList<WarningBean>();
        List<WarningBean> memoryUsageList = new ArrayList<WarningBean>();
        List<WarningBean> swapUsageList = new ArrayList<WarningBean>();
        List<WarningBean> diskUsageList = new ArrayList<WarningBean>();

        List<WarningBean> jvmOldList = new ArrayList<WarningBean>();

        for(WarningBean oneWarningBean : warningBeanList) {
            // 5种Application+DB
            if(WarningEnum.SQL_EXECUTE.getAlarmType().equals(oneWarningBean.getAlarmType())){
                sqlExecuteWarningList.add(oneWarningBean);
            }
            if(WarningEnum.SQL_CONNECTION.getAlarmType().equals(oneWarningBean.getAlarmType())){
                sqlConnectionWarningList.add(oneWarningBean);
            }
            if(WarningEnum.APPLICATION_EXCEPTION.getAlarmType().equals(oneWarningBean.getAlarmType())){
                exceptionWarningList.add(oneWarningBean);
            }
            if(WarningEnum.APPLICATION_STATUS_CODE.getAlarmType().equals(oneWarningBean.getAlarmType())){
                statusCodeWarningList.add(oneWarningBean);
            }
            if(WarningEnum.APPLICATION_CALL.getAlarmType().equals(oneWarningBean.getAlarmType())){
                callWarningList.add(oneWarningBean);
            }
            // 6种Machine
            if(WarningEnum.CPU_SYSTEM_USAGE.getAlarmType().equals(oneWarningBean.getAlarmType())){
                cpuSystemUsageList.add(oneWarningBean);
            }
            if(WarningEnum.CPU_USER_USAGE.getAlarmType().equals(oneWarningBean.getAlarmType())){
                cpuUserUsageList.add(oneWarningBean);
            }
            if(WarningEnum.CPU_LOAD_5.getAlarmType().equals(oneWarningBean.getAlarmType())){
                cpuLoad5List.add(oneWarningBean);
            }
            if(WarningEnum.MEMORY_USAGE.getAlarmType().equals(oneWarningBean.getAlarmType())){
                memoryUsageList.add(oneWarningBean);
            }
            if(WarningEnum.SWAP_USAGE.getAlarmType().equals(oneWarningBean.getAlarmType())){
                swapUsageList.add(oneWarningBean);
            }
            if(WarningEnum.DISK_USAGE.getAlarmType().equals(oneWarningBean.getAlarmType())){
                diskUsageList.add(oneWarningBean);
            }
            // 1种Jvm
            if(WarningEnum.JVM_OLD_USE_RATE.getAlarmType().equals(oneWarningBean.getAlarmType())){
                jvmOldList.add(oneWarningBean);
            }
        }

        //排序
        Collections.sort(sqlExecuteWarningList, WarningBeanComparator.getInstance());
        Collections.sort(sqlConnectionWarningList, WarningBeanComparator.getInstance());
        Collections.sort(exceptionWarningList, WarningBeanComparator.getInstance());
        Collections.sort(statusCodeWarningList, WarningBeanComparator.getInstance());
        Collections.sort(callWarningList, WarningBeanComparator.getInstance());
        Collections.sort(cpuSystemUsageList, WarningBeanComparator.getInstance());
        Collections.sort(cpuUserUsageList, WarningBeanComparator.getInstance());
        Collections.sort(cpuLoad5List, WarningBeanComparator.getInstance());
        Collections.sort(memoryUsageList, WarningBeanComparator.getInstance());
        Collections.sort(swapUsageList, WarningBeanComparator.getInstance());
        Collections.sort(diskUsageList, WarningBeanComparator.getInstance());
        Collections.sort(jvmOldList, WarningBeanComparator.getInstance());


        StringBuilder keyStringBuilder = new StringBuilder();
        genereteRedundancyKey(WarningEnum.SQL_EXECUTE.getAlarmType(), keyStringBuilder, sqlExecuteWarningList);
        genereteRedundancyKey(WarningEnum.SQL_CONNECTION.getAlarmType(), keyStringBuilder, sqlConnectionWarningList);
        genereteRedundancyKey(WarningEnum.APPLICATION_EXCEPTION.getAlarmType(), keyStringBuilder, exceptionWarningList);
        genereteRedundancyKey(WarningEnum.APPLICATION_STATUS_CODE.getAlarmType(), keyStringBuilder, statusCodeWarningList);
        genereteRedundancyKey(WarningEnum.APPLICATION_CALL.getAlarmType(), keyStringBuilder, callWarningList);
        genereteRedundancyKey(WarningEnum.CPU_SYSTEM_USAGE.getAlarmType(), keyStringBuilder, cpuSystemUsageList);
        genereteRedundancyKey(WarningEnum.CPU_USER_USAGE.getAlarmType(), keyStringBuilder, cpuUserUsageList);
        genereteRedundancyKey(WarningEnum.CPU_LOAD_5.getAlarmType(), keyStringBuilder, cpuLoad5List);
        genereteRedundancyKey(WarningEnum.MEMORY_USAGE.getAlarmType(), keyStringBuilder, memoryUsageList);
        genereteRedundancyKey(WarningEnum.SWAP_USAGE.getAlarmType(), keyStringBuilder, swapUsageList);
        genereteRedundancyKey(WarningEnum.DISK_USAGE.getAlarmType(), keyStringBuilder, diskUsageList);
        genereteRedundancyKey(WarningEnum.JVM_OLD_USE_RATE.getAlarmType(), keyStringBuilder, jvmOldList);

        String specificKey = keyStringBuilder.toString();

        if( !filter.contains(specificKey)) {
            filter.add(specificKey);
            return false;
        }

        return true;
    }

    /**
     * 生成校验的唯一key, 输入是多个告警信息
     * @param warningBeanList  告警信息集合
     * @return {@code String}
     */
    public static void genereteRedundancyKey(String warningBeanType, StringBuilder sb, List<WarningBean> warningBeanList) {
        if(null == sb || null == warningBeanList || warningBeanList.isEmpty()) {
            LOGGER.error("waringBeanList is null or Empty. [warningBeanType]:{}", warningBeanType);
            return ;
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
        if(WarningEnum.SQL_EXECUTE.getAlarmType().equals(warningBean.getAlarmType())){
            sb.append(warningBean.getApplicationKey())
                    .append(warningBean.getDestinationIp())
                    .append(warningBean.getDestinationPort())
                    .append(warningBean.getFeature());
        }
        if(WarningEnum.SQL_CONNECTION.getAlarmType().equals(warningBean.getAlarmType())){
            sb.append(warningBean.getApplicationKey())
                    .append(warningBean.getUrl())
                    .append(warningBean.getFeature());
        }
        if(WarningEnum.APPLICATION_EXCEPTION.getAlarmType().equals(warningBean.getAlarmType())){
            sb.append(warningBean.getApplicationKey())
                    .append(warningBean.getUrl())
                    .append(warningBean.getFeature());
        }
        if(WarningEnum.APPLICATION_STATUS_CODE.getAlarmType().equals(warningBean.getAlarmType())){
            sb.append(warningBean.getApplicationKey())
                    .append(warningBean.getUrl())
                    .append(warningBean.getFeature());
        }
        if(WarningEnum.APPLICATION_CALL.getAlarmType().equals(warningBean.getAlarmType())){
            sb.append(warningBean.getApplicationKey())
                    .append(warningBean.getDestinationIp())
                    .append(warningBean.getDestinationPort())
                    .append(warningBean.getUrl())
                    .append(warningBean.getFeature());
        }
        if(WarningEnum.CPU_SYSTEM_USAGE.getAlarmType().equals(warningBean.getAlarmType())){
            sb.append(warningBean.getSourceIp())
                    .append(NumberUtil.obfuscationUsageQuotaOfFeature(warningBean.getFeature()));
        }
        if(WarningEnum.CPU_USER_USAGE.getAlarmType().equals(warningBean.getAlarmType())){
            sb.append(warningBean.getSourceIp())
                    .append(NumberUtil.obfuscationUsageQuotaOfFeature(warningBean.getFeature()));
        }
        if(WarningEnum.CPU_LOAD_5.getAlarmType().equals(warningBean.getAlarmType())){
            sb.append(warningBean.getSourceIp())
                    .append(NumberUtil.obfuscationUsageQuotaOfFeature(warningBean.getFeature()));
        }
        if(WarningEnum.MEMORY_USAGE.getAlarmType().equals(warningBean.getAlarmType())){
            sb.append(warningBean.getSourceIp())
                    .append(NumberUtil.obfuscationUsageQuotaOfFeature(warningBean.getFeature()));
        }
        if(WarningEnum.SWAP_USAGE.getAlarmType().equals(warningBean.getAlarmType())){
            sb.append(warningBean.getSourceIp())
                    .append(NumberUtil.obfuscationUsageQuotaOfFeature(warningBean.getFeature()));
        }
        if(WarningEnum.DISK_USAGE.getAlarmType().equals(warningBean.getAlarmType())){
            sb.append(warningBean.getSourceIp())
                    .append(NumberUtil.obfuscationUsageQuotaOfFeature(warningBean.getFeature()))
                    .append(warningBean.getMountPoint());
        }
        if(WarningEnum.JVM_OLD_USE_RATE.getAlarmType().equals(warningBean.getAlarmType())){
            sb.append(warningBean.getApplicationKey())
                    .append(NumberUtil.obfuscationUsageQuotaOfFeature(warningBean.getFeature()));

        }
    }



}

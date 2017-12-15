package org.vlis.dog.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.bean.*;
import org.vlis.dog.constant.DataWrapperBeanTypeEnum;
import org.vlis.dog.constant.ManagerTypeEnum;
import org.vlis.dog.constant.WarningEnum;
import org.vlis.dog.util.WarningDataExtractUtil;

import javax.management.monitor.StringMonitorMBean;
import java.util.*;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 推理Manager
 */


public class ReasoningManager extends AbstractManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReasoningManager.class);
    private static final String CALL_STATUS_CODE_SUCCESS = "200";

    public ReasoningManager( ItfManager successorManager) {
        super(successorManager, ManagerTypeEnum.REASONING_MANAGER, DataWrapperBeanTypeEnum.MAP_TYPE);
    }

    /**
     * 数据进行推理处理，判断最有根源性定位告警
     * @param warningBeans 满足要求的数据集合
     * @return
     */
    @Override
    public DataWrapperBean cleanWarningBeans(DataWrapperBean warningBeans) {
        LOGGER.info("{} starting...", ManagerTypeEnum.REASONING_MANAGER.getDescription());
        DataWrapperBean storeAfterCleanWarningBeans = new ListWarningDataBean();

        // Machine 推理
        cleanWarningBeansOfMachine(storeAfterCleanWarningBeans, warningBeans);

        // Jvm 推理
        cleanWarningBeansOfJvm(storeAfterCleanWarningBeans, warningBeans);

        // DB 推理
        cleanWarningBeansOfDb(storeAfterCleanWarningBeans, warningBeans);

        // application 推理
        cleanWarningBeansOfApplication(storeAfterCleanWarningBeans, warningBeans);

        // 处理器最后一个处理节点
        return cleanWarningBeansOfEnd(storeAfterCleanWarningBeans, warningBeans);
    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.MACHINE} Machine机器类型接口
     * 第一步：获取MACHINE机器类型告警数据
     * 第二步：存入storeAfterClean中
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     */
    @Override
    public void cleanWarningBeansOfMachine(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(! canAcceptDataBeanType(pendingDataBean)|| !(pendingDataBean instanceof MapWarningDataBean)
                || !(storeAfterClean instanceof ListWarningDataBean) ) {
            throw new IllegalStateException("DataBean is error.");
        }
        // 第一步:
        // 获取Machine数据
        MapWarningDataBean machineAlarmTypeMap = WarningDataExtractUtil.extractWarningDataBean(pendingDataBean, WarningEnum.MACHINE);

        // 第二步:
        // 添加硬件信息
        Map<String, List<WarningBean>> machineWarningBeanMap = machineAlarmTypeMap.getDataBeans();
        Set<Map.Entry<String, List<WarningBean>>> machineWarningBeanSet = machineWarningBeanMap.entrySet();
        for(Map.Entry<String, List<WarningBean>> machineWarningBeanEntry : machineWarningBeanSet) {
            if(null != machineWarningBeanEntry.getValue() && !machineWarningBeanEntry.getValue().isEmpty()) {
                ((ListWarningDataBean)storeAfterClean).add(machineWarningBeanEntry.getValue().get(0));
            }
        }
    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.DB} Db类型接口
     * 第一步：获取DB机器类型告警数据
     * 第二步：存入storeAfterClean中
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     */
    @Override
    public void cleanWarningBeansOfDb(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(! canAcceptDataBeanType(pendingDataBean)|| !(pendingDataBean instanceof MapWarningDataBean)
                || !(storeAfterClean instanceof ListWarningDataBean) ) {
            throw new IllegalStateException("DataBean is error.");
        }

        // 第一步:
        // 获取DB数据
        MapWarningDataBean dbAlarmTypeMap = WarningDataExtractUtil.extractWarningDataBean(pendingDataBean, WarningEnum.DB);

        // 第二步:
        // 添加数据库信息
        Map<String, List<WarningBean>> dbWarningBeanMap = dbAlarmTypeMap.getDataBeans();
        Set<Map.Entry<String, List<WarningBean>>> dbWarningBeanSet = dbWarningBeanMap.entrySet();
        for(Map.Entry<String, List<WarningBean>> dbWarningBeanEntry : dbWarningBeanSet) {
            if(null != dbWarningBeanEntry.getValue() && !dbWarningBeanEntry.getValue().isEmpty()) {
                ((ListWarningDataBean)storeAfterClean).add(dbWarningBeanEntry.getValue().get(0));
            }
        }

    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.JVM} Jvm类型接口
     * 第一步：获取JVM机器类型告警数据
     * 第二步：存入storeAfterClean中
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     */
    @Override
    public void cleanWarningBeansOfJvm(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(! canAcceptDataBeanType(pendingDataBean)|| !(pendingDataBean instanceof MapWarningDataBean)
                || !(storeAfterClean instanceof ListWarningDataBean) ) {
            throw new IllegalStateException("DataBean is error.");
        }

        // 第一步:
        // 获取DB数据
        MapWarningDataBean jvmAlarmTypeMap = WarningDataExtractUtil.extractWarningDataBean(pendingDataBean, WarningEnum.JVM);

        // 第二步:
        // 添加数据库信息
        Map<String, List<WarningBean>> jvmWarningBeanMap = jvmAlarmTypeMap.getDataBeans();
        Set<Map.Entry<String, List<WarningBean>>> jvmWarningBeanSet = jvmWarningBeanMap.entrySet();
        for(Map.Entry<String, List<WarningBean>> jvmWarningBeanEntry : jvmWarningBeanSet) {
            if(null != jvmWarningBeanEntry.getValue() && !jvmWarningBeanEntry.getValue().isEmpty()) {
                ((ListWarningDataBean)storeAfterClean).add(jvmWarningBeanEntry.getValue().get(0));
            }
        }

    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.APPLICATION} Application类型接口
     * 第一步：获取Application类型告警数据
     * 第二步：对每个集合数据进行推理
     * 第二步：存入storeAfterClean中
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     */
    @Override
    public void cleanWarningBeansOfApplication(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(! canAcceptDataBeanType(pendingDataBean)|| !(pendingDataBean instanceof MapWarningDataBean)
                || !(storeAfterClean instanceof ListWarningDataBean) ) {
            throw new IllegalStateException("DataBean is error.");
        }

        // 第一步:
        // 获取DB数据
        MapWarningDataBean applicationAlarmTypeMap = WarningDataExtractUtil.extractWarningDataBean(pendingDataBean, WarningEnum.APPLICATION);

        // 第二步：
        // 进行推理
        Map<String, List<WarningBean>> applicationWarningBeanMap = applicationAlarmTypeMap.getDataBeans();
        Set<Map.Entry<String, List<WarningBean>>> applicationWarningBeanSet = applicationWarningBeanMap.entrySet();
        for(Map.Entry<String, List<WarningBean>> applicationWarningBeanEntry : applicationWarningBeanSet) {
            List<WarningBean> warningBeanList = applicationWarningBeanEntry.getValue();
            reasoningOfApplication(storeAfterClean, warningBeanList);
        }
    }

    /**
     * 推理分布式跟踪告警数据
     * 1. 先根据父类型，筛选出{@see WarningEnum.MACHINE}和{@see WarningEnum.JVM}告警数据
     * 2. 对于数据进行分布式跟踪的告警数据，进行推理
     * @param storeAfterClean 存放的处理后数据
     * @param warningBeanList 待处理数据
     */
    private void reasoningOfApplication(DataWrapperBean storeAfterClean, List<WarningBean> warningBeanList) {
        if(null == storeAfterClean || null == warningBeanList || warningBeanList.isEmpty()) {
            throw new NullPointerException("parameter is null.");
        }

        List<WarningBean> machineOrJvmList = new ArrayList<WarningBean>();
        List<WarningBean> applicationList = new ArrayList<WarningBean>();

        //key : parentspanId, value is List
        Map<String, List<WarningBean> > applicationMap = new HashMap<String, List<WarningBean>>();

        String statusCode = null;
        long interval = Long.MIN_VALUE;

        for(WarningBean warningBean : warningBeanList) {
            if(WarningEnum.MACHINE.getParentWarningEnum().getAlarmType().equals(warningBean.getAlarmParentType())) {
                machineOrJvmList.add(warningBean);
            } else if(WarningEnum.JVM.getParentWarningEnum().getAlarmType().equals(warningBean.getAlarmParentType())) {
                machineOrJvmList.add(warningBean);
            } else {
                String parentSpanIdKey = warningBean.getParentSpanId();
                if(null == parentSpanIdKey || "null".equals(parentSpanIdKey) ) {
                    throw new NullPointerException("happen error.");
                }
                if(applicationMap.containsKey(parentSpanIdKey)){
                    applicationMap.get(parentSpanIdKey).add(warningBean);
                }else {
                    List<WarningBean> subWarningBeanList = new ArrayList<WarningBean>();
                    subWarningBeanList.add(warningBean);
                    applicationMap.put(parentSpanIdKey, subWarningBeanList);
                }
                if("-1".equals(parentSpanIdKey)
                        && WarningEnum.APPLICATION_STATUS_CODE.getAlarmType().equals(warningBean.getAlarmType())) {
                    statusCode = warningBean.getFeature();
                    interval = Long.parseLong(warningBean.getExecuteTime());
                }
            }
        }

        if(null == statusCode || Long.MIN_VALUE == interval) {
            throw new IllegalStateException("TraceWarningBean data error.");
        }

        WarningBeanWrapper warningBeanWrapper = new WarningBeanWrapper();

        if(CALL_STATUS_CODE_SUCCESS.equals(statusCode) ){
            // 延时高的告警数据诊断
            reasoningOfDelayed(warningBeanWrapper,interval , applicationMap);
        } else {
            // 错误的告警数据诊断
            reasoningOfError(warningBeanWrapper, statusCode, applicationMap);
        }

        // 告警数据保存起来
        ((ListWarningDataBean)storeAfterClean).add(warningBeanWrapper);

    }

    /**
     * 如果是链路上发生错误，则找到错误点
     * @param warningBeanWrapper  存放的处理后数据
     * @param statusCode 调用状态码
     * @param applicationMap 查找数据集合
     */
    private void reasoningOfError(WarningBeanWrapper warningBeanWrapper, String statusCode, Map<String, List<WarningBean> > applicationMap) {
        String nextSpanId = "-1";
        while(true) {
            if("200".equals(statusCode) ){
                break;
            }

            if(!applicationMap.containsKey(nextSpanId)) {
                break;
            }

            List<WarningBean> warningBeanList = applicationMap.get(nextSpanId);
            for(WarningBean warningBean : warningBeanList) {
                if(WarningEnum.APPLICATION_STATUS_CODE.getAlarmType().equals(warningBean.getAlarmType()) ) {
                    statusCode = warningBean.getFeature();
                    nextSpanId = warningBean.getSpanId();
                } else if(WarningEnum.APPLICATION_EXCEPTION.getAlarmType().equals(warningBean.getAlarmType()) ) {
                    warningBeanWrapper.setTraceWarningBean(warningBean);
                } else if(WarningEnum.APPLICATION_CALL.getAlarmType().equals(warningBean.getAlarmType()) ) {
                    warningBeanWrapper.setTraceWarningBean(warningBean);
                }
            }
        }
    }

    /**
     * 如果是链路上发生高延时，凡是超过总耗时的1/3的告警数据，都作为诊断出的告警数据。
     * @param warningBeanWrapper 存放的处理后数据
     * @param interval 调用延时, unit : ms
     * @param applicationMap 查找数据集合
     */
    private void reasoningOfDelayed(WarningBeanWrapper warningBeanWrapper, long interval, Map<String, List<WarningBean> > applicationMap) {
        String nextSpanId = "-1";
        long nextInterval = interval;
        WarningBean lastMaxIntrvalWarningBean = null;
        while(true) {

            if(interval / 3 > nextInterval ){
                if(null != lastMaxIntrvalWarningBean) {
                    warningBeanWrapper.setTraceWarningBean(lastMaxIntrvalWarningBean);
                }
                break;
            }

            if(!applicationMap.containsKey(nextSpanId)) {
                break;
            }
            nextInterval = 0;
            nextSpanId = "unknownSpanId";

            List<WarningBean> warningBeanList = applicationMap.get(nextSpanId);
            for(WarningBean warningBean : warningBeanList) {
                if(WarningEnum.APPLICATION_STATUS_CODE.getAlarmType().equals(warningBean.getAlarmType()) ) {
                    // 这里可能比较难理解，但是肯定没有问题。目的是找到：调用链下一个最大服务节点，如果没有则选择当前的告警数据。
                    long executeTime = Long.parseLong(warningBean.getExecuteTime());
                    if(executeTime > nextInterval) {
                        if(executeTime > nextInterval && executeTime > interval / 3) {
                            nextSpanId = warningBean.getSpanId();
                        }
                        nextInterval = executeTime;
                        lastMaxIntrvalWarningBean = warningBean;
                    }
                } else if(WarningEnum.SQL_CONNECTION.getAlarmType().equals(warningBean.getAlarmType()) ) {
                    long executeTime = Long.parseLong(warningBean.getExecuteTime());
                    if(executeTime > interval / 3) {
                        warningBeanWrapper.setTraceWarningBean(warningBean);
                    }

                } else if(WarningEnum.SQL_CONNECTION.getAlarmType().equals(warningBean.getAlarmType()) ) {
                    long executeTime = Long.parseLong(warningBean.getExecuteTime());
                    if(executeTime > interval / 3) {
                        warningBeanWrapper.setTraceWarningBean(warningBean);
                    }
                } else if(WarningEnum.APPLICATION_CALL.getAlarmType().equals(warningBean.getAlarmType()) ) {
                    long executeTime = Long.parseLong(warningBean.getExecuteTime());
                    if(executeTime > interval / 3) {
                        warningBeanWrapper.setTraceWarningBean(warningBean);
                    }
                }
            }
        }
    }

}

package org.vlis.dog.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.bean.DataWrapperBean;
import org.vlis.dog.bean.ListWarningDataBean;
import org.vlis.dog.bean.MapWarningDataBean;
import org.vlis.dog.bean.WarningBean;
import org.vlis.dog.constant.DataWrapperBeanTypeEnum;
import org.vlis.dog.constant.ManagerTypeEnum;
import org.vlis.dog.constant.WarningEnum;
import org.vlis.dog.util.WarningDataExtractUtil;

import javax.management.monitor.StringMonitorMBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 推理Manager
 */


public class ReasoningManager extends AbstractManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReasoningManager.class);

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
            //todo:: 提供推理函数， 推理函数第一，看看是不是错误问题。第二看看是不是延时高。分别定位问题
        }

    }

}

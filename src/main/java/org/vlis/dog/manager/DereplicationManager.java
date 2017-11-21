package org.vlis.dog.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.bean.DataWrapperBean;
import org.vlis.dog.bean.MapWarningDataBean;
import org.vlis.dog.bean.WarningBean;
import org.vlis.dog.constant.DataWrapperBeanTypeEnum;
import org.vlis.dog.constant.ManagerTypeEnum;
import org.vlis.dog.constant.WarningEnum;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 告警信息去重模块
 */


public final class DereplicationManager extends AbstractManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DereplicationManager.class);

    public DereplicationManager( ItfManager successorManager) {
        super(successorManager, ManagerTypeEnum.CONDENSE_MANAGER, DataWrapperBeanTypeEnum.MAP_TYPE);
    }

    /**
     * 去重模块，将分类后的数据，去重复
     * @param warningBeans 待处理的数据集
     * @return
     */
    @Override
    public DataWrapperBean cleanWarningBeans(DataWrapperBean warningBeans) {
        LOGGER.info("{} starting...", ManagerTypeEnum.CONDENSE_MANAGER.getDescription());
        DataWrapperBean storeAfterCleanWarningBeans = new MapWarningDataBean();

        // application 分类
        cleanWarningBeansOfApplication(storeAfterCleanWarningBeans, warningBeans);

        // Jvm 分类
        cleanWarningBeansOfJvm(storeAfterCleanWarningBeans, warningBeans);

        // DB 分类
        cleanWarningBeansOfDb(storeAfterCleanWarningBeans, warningBeans);

        // Machine 分类
        cleanWarningBeansOfMachine(storeAfterCleanWarningBeans, warningBeans);

        // 处理器最后一个处理节点
        return cleanWarningBeansOfEnd(storeAfterCleanWarningBeans, warningBeans);
    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.MACHINE} Machine机器类型
     * 第一步：根据AlarmParentType获取数据
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     * @return 下一个类型数据处理
     */
    @Override
    public void cleanWarningBeansOfMachine(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(!canAcceptDataBeanType(pendingDataBean) || !(pendingDataBean instanceof MapWarningDataBean)
                || !(storeAfterClean instanceof MapWarningDataBean)) {
            throw new IllegalStateException("DataBean is error.");
        }

        // 第一步：
        // 获取分类后Application应用数据
        MapWarningDataBean jvmAlarmTypeMap = extractWarningDataBean(pendingDataBean, WarningEnum.MACHINE);

        // 第二步：挑选出最优的
        //todo:: 算法提供
    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.DB} Db机器类型
     * 第一步：根据AlarmParentType获取数据
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     * @return 下一个类型数据处理
     */
    @Override
    public void cleanWarningBeansOfDb(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(!canAcceptDataBeanType(pendingDataBean) || !(pendingDataBean instanceof MapWarningDataBean)
                || !(storeAfterClean instanceof MapWarningDataBean)) {
            throw new IllegalStateException("DataBean is error.");
        }

        // 第一步：
        // 获取分类后Application应用数据
        MapWarningDataBean jvmAlarmTypeMap = extractWarningDataBean(pendingDataBean, WarningEnum.DB);

        // 第二步：利用去重复化算法: Bloom Filter
        //todo:: 算法提供

    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.JVM} Jvm机器类型
     * 第一步：根据AlarmParentType获取数据
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     * @return 下一个类型数据处理
     */
    @Override
    public void cleanWarningBeansOfJvm(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(!canAcceptDataBeanType(pendingDataBean) || !(pendingDataBean instanceof MapWarningDataBean)
                || !(storeAfterClean instanceof MapWarningDataBean)) {
            throw new IllegalStateException("DataBean is error.");
        }

        // 第一步：
        // 获取分类后Application应用数据
        MapWarningDataBean jvmAlarmTypeMap = extractWarningDataBean(pendingDataBean, WarningEnum.JVM);

        // 第二步：利用去重复化算法: Bloom Filter
        //todo:: 算法提供



    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.APPLICATION} Application类型
     * 第一步：根据AlarmParentType获取数据
     * 第二步：
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     * @return 下一个类型数据处理
     */
    @Override
    public void cleanWarningBeansOfApplication(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(!canAcceptDataBeanType(pendingDataBean) || !(pendingDataBean instanceof MapWarningDataBean)
                || !(storeAfterClean instanceof MapWarningDataBean)) {
            throw new IllegalStateException("DataBean is error.");
        }

        // 第一步：
        // 获取分类后Application应用数据
        MapWarningDataBean applicationAlarmTypeMap = extractWarningDataBean(pendingDataBean, WarningEnum.APPLICATION);

        // 第二步：利用去重复化算法: Bloom Filter
        //todo:: 算法提供

    }

    /**
     * 提取告警信息，按照提供的{@see org.vlis.dog.constant.WarningEnum}来提取
     * @param pendingDataBean 原始数据
     * @param warningBeanEnum 提取类型
     * @return 提取出的数据
     */
    private MapWarningDataBean extractWarningDataBean(DataWrapperBean pendingDataBean, WarningEnum warningBeanEnum) {
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

package org.vlis.dog.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.bean.DataWrapperBean;
import org.vlis.dog.bean.MapWarningDataBean;
import org.vlis.dog.bean.WarningBean;
import org.vlis.dog.constant.DataWrapperBeanTypeEnum;
import org.vlis.dog.constant.ManagerTypeEnum;
import org.vlis.dog.constant.WarningEnum;
import org.vlis.dog.util.WarningDataExtractUtil;

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

        // application 去重
        cleanWarningBeansOfApplication(storeAfterCleanWarningBeans, warningBeans);

        // Jvm 去重
        cleanWarningBeansOfJvm(storeAfterCleanWarningBeans, warningBeans);

        // DB 去重
        cleanWarningBeansOfDb(storeAfterCleanWarningBeans, warningBeans);

        // Machine 去重
        cleanWarningBeansOfMachine(storeAfterCleanWarningBeans, warningBeans);

        // 处理器最后一个处理节点
        return cleanWarningBeansOfEnd(storeAfterCleanWarningBeans, warningBeans);
    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.MACHINE} Machine机器类型
     * 第一步：根据AlarmParentType获取数据
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     */
    @Override
    public void cleanWarningBeansOfMachine(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(!canAcceptDataBeanType(pendingDataBean) || !(pendingDataBean instanceof MapWarningDataBean)
                || !(storeAfterClean instanceof MapWarningDataBean)) {
            throw new IllegalStateException("DataBean is error.");
        }

        // 第一步：
        // 获取分类后Application应用数据
        MapWarningDataBean jvmAlarmTypeMap = WarningDataExtractUtil.extractWarningDataBean(pendingDataBean, WarningEnum.MACHINE);

        // 第二步：挑选出最优的
        //todo:: 算法提供
    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.DB} Db机器类型
     * 第一步：根据AlarmParentType获取数据
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     */
    @Override
    public void cleanWarningBeansOfDb(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(!canAcceptDataBeanType(pendingDataBean) || !(pendingDataBean instanceof MapWarningDataBean)
                || !(storeAfterClean instanceof MapWarningDataBean)) {
            throw new IllegalStateException("DataBean is error.");
        }

        // 第一步：
        // 获取分类后Application应用数据
        MapWarningDataBean jvmAlarmTypeMap = WarningDataExtractUtil.extractWarningDataBean(pendingDataBean, WarningEnum.DB);

        // 第二步：利用去重复化算法: Bloom Filter
        //todo:: 算法提供

    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.JVM} Jvm机器类型
     * 第一步：根据AlarmParentType获取数据
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     */
    @Override
    public void cleanWarningBeansOfJvm(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(!canAcceptDataBeanType(pendingDataBean) || !(pendingDataBean instanceof MapWarningDataBean)
                || !(storeAfterClean instanceof MapWarningDataBean)) {
            throw new IllegalStateException("DataBean is error.");
        }

        // 第一步：
        // 获取分类后Application应用数据
        MapWarningDataBean jvmAlarmTypeMap = WarningDataExtractUtil.extractWarningDataBean(pendingDataBean, WarningEnum.JVM);

        // 第二步：利用去重复化算法: Bloom Filter
        //todo:: 算法提供



    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.APPLICATION} Application类型
     * 第一步：根据AlarmParentType获取数据
     * 第二步：
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     */
    @Override
    public void cleanWarningBeansOfApplication(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(!canAcceptDataBeanType(pendingDataBean) || !(pendingDataBean instanceof MapWarningDataBean)
                || !(storeAfterClean instanceof MapWarningDataBean)) {
            throw new IllegalStateException("DataBean is error.");
        }

        // 第一步：
        // 获取分类后Application应用数据
        MapWarningDataBean applicationAlarmTypeMap = WarningDataExtractUtil.extractWarningDataBean(pendingDataBean, WarningEnum.APPLICATION);
        // 第二步:
        // 补充DB的traceId数据相同的数据进来，同时把原来的地方移除

        // 第二步：利用去重复化算法: Bloom Filter
        //todo:: 算法提供

    }

}

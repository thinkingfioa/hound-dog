package org.vlis.dog.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.bean.DataWrapperBean;
import org.vlis.dog.bean.MapWarningDataBean;
import org.vlis.dog.constant.DataWrapperBeanTypeEnum;
import org.vlis.dog.constant.ManagerTypeEnum;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 告警分类管理器
 */


public class ClassifyManager extends AbstractManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassifyManager.class);

    public ClassifyManager(ItfManager successorManager) {
        super(successorManager, ManagerTypeEnum.CLASSIFY_MANAGER, DataWrapperBeanTypeEnum.LIST_TYPE);
    }

    /**
     * 按照要求，对数据进行分类处理
     * @param warningBeans 提供规格的Map数据集
     * @return {@see org.vlis.dog.bean.MapWarningDataBean} 返回清理后的数据
     */
    @Override
    public DataWrapperBean cleanWarningBeans(DataWrapperBean warningBeans) {
        LOGGER.info("{} starting... ", ManagerTypeEnum.CLASSIFY_MANAGER.getDescription());
        DataWrapperBean storeAfterCleanWarningBeans = new MapWarningDataBean();

        // application 分类
        DataWrapperBean nextPendingDataBean  =  cleanWarningBeansOfApplication(storeAfterCleanWarningBeans, warningBeans);

        // Jvm 分类
        nextPendingDataBean = cleanWarningBeansOfJvm(storeAfterCleanWarningBeans, nextPendingDataBean);

        // DB 分类
        nextPendingDataBean = cleanWarningBeansOfDb(storeAfterCleanWarningBeans, nextPendingDataBean);

        // Machine 分类
        nextPendingDataBean = cleanWarningBeansOfMachine(storeAfterCleanWarningBeans, nextPendingDataBean);

        // 处理器最后一个处理节点
        return cleanWarningBeansOfEnd(storeAfterCleanWarningBeans, nextPendingDataBean);
    }


    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.MACHINE} Machine机器类型接口
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     * @return 下一个类型数据处理
     */
    @Override
    public DataWrapperBean cleanWarningBeansOfMachine(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(! canAcceptDataBeanType(pendingDataBean)) {
            throw new IllegalStateException("DataBean is error.");
        }

        //todo
        return storeAfterClean;

    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.DB} Machine机器类型接口
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     * @return 下一个类型数据处理
     */
    @Override
    public DataWrapperBean cleanWarningBeansOfDb(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(! canAcceptDataBeanType(pendingDataBean)) {
            throw new IllegalStateException("DataBean is error.");
        }

        //todo
        return storeAfterClean;
    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.JVM} Machine机器类型接口
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     * @return 下一个类型数据处理
     */
    @Override
    public DataWrapperBean cleanWarningBeansOfJvm(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(! canAcceptDataBeanType(pendingDataBean)) {
            throw new IllegalStateException("DataBean is error.");
        }

        //todo
        return storeAfterClean;
    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.APPLICATION} Machine机器类型接口
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     * @return 下一个类型数据处理
     */
    @Override
    public DataWrapperBean cleanWarningBeansOfApplication(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(! canAcceptDataBeanType(pendingDataBean)) {
            throw new IllegalStateException("DataBean is error.");
        }

        //todo
        return storeAfterClean;
    }

}

package org.vlis.dog.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.bean.DataWrapperBean;
import org.vlis.dog.constant.DataWrapperBeanTypeEnum;
import org.vlis.dog.constant.ManagerTypeEnum;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 抽象的数据整理
 */


public abstract class AbstractManager implements ItfManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractManager.class);

    /**
     * 后继处理的Manager
     */
    private ItfManager successorManager;

    /**
     * 处理器Manager类型
     */
    private ManagerTypeEnum managerTypeEnum;

    /**
     * 待处理数据类型，也就是Manager输入数据
     */
    private DataWrapperBeanTypeEnum pendingDataBeanType;

    AbstractManager(ItfManager successorManager,
                              ManagerTypeEnum managerTypeEnum, DataWrapperBeanTypeEnum pendingDataBeanType) {
        this.successorManager = successorManager;
        this.managerTypeEnum = managerTypeEnum;
        this.pendingDataBeanType = pendingDataBeanType;
    }

    /**
     * 1> 数据清洗，子类实现的接口：cleanWarningBeans
     * 2> 将数据传播下一个节点
     * @param warningBeans 待清洗的告警数据
     * @return  {@code DataWrapperBean } 清洗后的数据集合
     */
    @Override
    public DataWrapperBean dealWithWarningBeans(DataWrapperBean warningBeans) {

        if(! warningBeans.getDataBeanType().equals(pendingDataBeanType)) {
            throw new IllegalStateException("DataType error.");
        }

        // 对应的Manager清洗数据
        DataWrapperBean cleanWarningBeans = cleanWarningBeans(warningBeans);
        if(null == successorManager || ManagerTypeEnum.DEFAULT_MANAGER.equals(managerTypeEnum) ) {
            // 结束往后传播
            return warningBeans;
        }

        return successorManager.dealWithWarningBeans(cleanWarningBeans);
    }

    /**
     * 清洗数据集合
     * @param warningBeans 满足要求的数据规格
     * @return 清洗后数据集合 {@code List<WarningBean> }
     */
    protected abstract DataWrapperBean cleanWarningBeans(DataWrapperBean warningBeans);

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.MACHINE} Machine机器类型接口，提供一个缺省的函数实现
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     */
    protected void cleanWarningBeansOfMachine(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) { }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.DB} Db类型接口，提供一个缺省的函数实现
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     */
    protected void cleanWarningBeansOfDb(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) { }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.JVM} Jvm类型接口，提供一个缺省的函数实现
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     */
    protected void cleanWarningBeansOfJvm(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) { }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.APPLICATION} Application类型接口，提供一个缺省的函数实现
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     */
    protected void cleanWarningBeansOfApplication(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) { }

    /**
     * Manager最后一个处理节点，满足下一个链路的输入要求数据
     * @param storeAfterClean 存放的处理后数据
     * @return 下一个类型数据处理
     */
    protected DataWrapperBean cleanWarningBeansOfEnd(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(null != pendingDataBean && !pendingDataBean.isEmpty()) {
            LOGGER.error("[untreated]have some surplus waningData . [size]", pendingDataBean.size());
        }

        return storeAfterClean;
    }

    /**
     * 返回Manager管理器的类型
     * @return  Manager管理器的类型
     */
    @Override
    public ManagerTypeEnum getManagerTypeEnum() {
        return managerTypeEnum;
    }

    /**
     * 该Manager所接受的数据类型
     * @return see org.vlis.dog.constant.DataWrapperBeanTypeEnum 枚举类
     */
    @Override
    public DataWrapperBeanTypeEnum getPendingDataBeanType () {
        return pendingDataBeanType;
    }

    /**
     * 返回Manager管理器的描述
     * @return  Manager管理器的描述
     */
    public String getDescription() {
        if(null == managerTypeEnum.getDescription()) {
            throw new IllegalStateException("Unknow manager.");
        }
        return managerTypeEnum.getDescription();
    }

    /**
     * 判断数据传播是否有问题
     * @param warningBeans 传入待处理的数据
     * @return true 表示可处理；false 表示数据处理类型不同
     */
    @Override
    public boolean canAcceptDataBeanType(DataWrapperBean warningBeans) {
        if( null == warningBeans || warningBeans.isEmpty()) {
            return false;
        }

        return warningBeans.getDataBeanType().equals(pendingDataBeanType);
    }
}

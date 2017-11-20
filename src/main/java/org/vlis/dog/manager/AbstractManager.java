package org.vlis.dog.manager;

import org.vlis.dog.bean.DataWrapperBean;
import org.vlis.dog.constant.DataWarpperBeanTypeEnum;
import org.vlis.dog.constant.ManagerTypeEnum;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 抽象的数据整理
 */


public abstract class AbstractManager implements ItfManager {

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
    private DataWarpperBeanTypeEnum pendingDataBeanType;

    AbstractManager(ItfManager successorManager,
                              ManagerTypeEnum managerTypeEnum, DataWarpperBeanTypeEnum pendingDataBeanType) {
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
     * 返回Manager管理器的类型
     * @return  Manager管理器的类型
     */
    @Override
    public ManagerTypeEnum getManagerTypeEnum() {
        return managerTypeEnum;
    }

    /**
     * 该Manager所接受的数据类型
     * @return see org.vlis.dog.constant.DataWarpperBeanTypeEnum 枚举类
     */
    @Override
    public DataWarpperBeanTypeEnum getPendingDataBeanType () {
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

package org.vlis.dog.manager;

import org.vlis.dog.bean.DataWrapperBean;
import org.vlis.dog.constant.DataWarpperBeanTypeEnum;
import org.vlis.dog.constant.ManagerTypeEnum;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 处理器的上层接口类
 */


public interface ItfManager {

    /**
     * 1> 数据清洗，子类实现的接口：cleanWarningBeans
     * 2> 将数据传播下一个节点
     * @param warningBeans 待清洗的告警数据
     * @return  {@code DataWrapperBean } 清洗后的数据集合
     */
    DataWrapperBean dealWithWarningBeans(DataWrapperBean warningBeans);

    /**
     * 返回Manager管理器的类型
     * @return  Manager管理器的类型
     */
    ManagerTypeEnum getManagerTypeEnum();

    /**
     * 该Manager所接受的数据类型
     * @return {@see org.vlis.dog.constant.DataWarpperBeanTypeEnum} 枚举类
     */
    DataWarpperBeanTypeEnum getPendingDataBeanType ();

    /**
     * 判断数据传播是否有问题
     * @param warningBeans 传入待处理的数据
     * @return true 表示可处理；false 表示数据处理类型不同
     */
    boolean canAcceptDataBeanType(DataWrapperBean warningBeans);
}

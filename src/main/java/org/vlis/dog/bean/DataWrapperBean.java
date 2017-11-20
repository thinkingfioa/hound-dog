package org.vlis.dog.bean;

import org.vlis.dog.constant.DataWrapperBeanTypeEnum;

/**
 * @author thinking_fioa
 * @createTime 2017/11/19
 * @description
 */


public interface DataWrapperBean<T> {
    /**
     * 包装的数据类型
     * @return {@link org.vlis.dog.constant.DataWrapperBeanTypeEnum}
     */
    DataWrapperBeanTypeEnum getDataBeanType();

    /**
     * 为空判断
     * @return false 不空；true空
     */
    boolean isEmpty();

    /**
     * 获取集合大小
     * @return 返回大小
     */
    long size();

    /**
     * 两个DataWrapperBean相加
     * @param otherDataWrapperBean 第二个 DataWrapperBean
     * @return if this DataWrapperBean changed as a result of the call
     */
    boolean addDataWrapperBean(DataWrapperBean otherDataWrapperBean);

    /**
     * 获取包装的实体对象类
     * @return 返回实体对象Bean
     */
    T getDataBeans();
}

package org.vlis.dog.bean;

import org.vlis.dog.constant.DataWarpperBeanTypeEnum;

/**
 * @author thinking_fioa
 * @createTime 2017/11/19
 * @description
 */


public interface DataWrapperBean {
    DataWarpperBeanTypeEnum getDataBeanType();

    boolean isEmpty();

    long size();
}

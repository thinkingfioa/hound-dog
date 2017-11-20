package org.vlis.dog.bean;

import org.vlis.dog.constant.DataWrapperBeanTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author thinking_fioa
 * @createTime 2017/11/19
 * @description 列表数据类型
 */


public class ListWarningDataBean implements DataWrapperBean<List<WarningBean>> {

    private static final DataWrapperBeanTypeEnum WARNING_DATA_BEAN_TYPE_ENUM = DataWrapperBeanTypeEnum.LIST_TYPE;

    private List<WarningBean> warningBeanList;

    public ListWarningDataBean(List<WarningBean> warningBeanList) {
        this.warningBeanList = new ArrayList<WarningBean>(warningBeanList);
    }

    public ListWarningDataBean() {
        this.warningBeanList = new ArrayList<WarningBean>();
    }

    /**
     * 添加
     * @param warningBean 添加的值
     * @return true (as specified by {@link List#add})
     */
    public boolean add(WarningBean warningBean) {
        if(null == warningBeanList ) {
            warningBeanList = new ArrayList<WarningBean>();
        }

        return warningBeanList.add(warningBean);
    }

    /**
     * 添加List集合
     * @param warningBeanList 集合
     * @return if this List changed as a result of the call
     */
    public boolean addList(List<WarningBean>  warningBeanList) {
        if(null == warningBeanList || warningBeanList.isEmpty()) {
            return false;
        }

        return this.warningBeanList.addAll(warningBeanList);
    }


    public static ListWarningDataBean valueOf(List<WarningBean>  warningBeanList) {
        if(null == warningBeanList || warningBeanList.isEmpty()) {
            throw new NullPointerException("parameters is null.");
        }
        return new ListWarningDataBean(warningBeanList);
    }

    /**
     * 添加另一个DataWrapper对象
     * @param otherDataWrapperBean 另一个DataWrapper对象
     * @return if this List changed as a result of the call
     */
    @Override
    public boolean addDataWrapperBean(DataWrapperBean otherDataWrapperBean) {
        if(!WARNING_DATA_BEAN_TYPE_ENUM.equals(otherDataWrapperBean.getDataBeanType())) {
            return false;
        }

        return addList(((ListWarningDataBean)otherDataWrapperBean).getDataBeans());
    }

    /**
     * 判断是否空集合
     * @return true 为空; false 为非空
     */
    @Override
    public boolean isEmpty() {
        return null == warningBeanList || warningBeanList.isEmpty();
    }

    @Override
    public DataWrapperBeanTypeEnum getDataBeanType() {
        return WARNING_DATA_BEAN_TYPE_ENUM;
    }

    @Override
    public long size(){
        if(null == warningBeanList || warningBeanList.isEmpty()) {
            return 0;
        }

        return warningBeanList.size();
    }


    @Override
    public List<WarningBean> getDataBeans() {
        return warningBeanList;
    }

}

package org.vlis.dog.bean;

import org.vlis.dog.constant.DataWarpperBeanTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author thinking_fioa
 * @createTime 2017/11/19
 * @description 列表数据类型
 */


public class ListWarningDataBean implements DataWrapperBean {

    private DataWarpperBeanTypeEnum WARNING_DATA_BEAN_TYPE_ENUM = DataWarpperBeanTypeEnum.LIST_TYPE;

    private List<WarningBean> warningBeanList;

    public ListWarningDataBean(List<WarningBean> warningBeanList) {
        this.warningBeanList = new ArrayList<WarningBean>(warningBeanList);
    }

    /**
     * 添加
     * @param warningBean 添加的值
     * @return true (as specified by {@link List#add})
     */
    public  boolean add(WarningBean warningBean) {
        if(null == warningBeanList ) {
            warningBeanList = new ArrayList<WarningBean>();
        }

        return warningBeanList.add(warningBean);
    }

    /**
     * 判断是否空集合
     * @return true 为空; false 为非空
     */
    @Override
    public boolean isEmpty() {
        if( null == warningBeanList || warningBeanList.isEmpty()) {
            return true;
        }

        return false;
    }

    @Override
    public DataWarpperBeanTypeEnum getDataBeanType() {
        return WARNING_DATA_BEAN_TYPE_ENUM;
    }

    @Override
    public long size() {
        if(null == warningBeanList || warningBeanList.isEmpty()) {
            return 0;
        }

        return warningBeanList.size();
    }

    public static ListWarningDataBean valueOf(List<WarningBean>  warningBeanList) {
        if(null == warningBeanList || warningBeanList.isEmpty()) {
            throw new NullPointerException("parameters is null.");
        }
        return new ListWarningDataBean(warningBeanList);
    }

    public List<WarningBean> getWarningBeanList() {
        return warningBeanList;
    }

    public void setWarningBeanList(List<WarningBean> warningBeanList) {
        this.warningBeanList = warningBeanList;
    }
}

package org.vlis.dog.bean;

import org.vlis.dog.constant.DataWarpperBeanTypeEnum;

import java.util.*;

/**
 * @author thinking_fioa
 * @createTime 2017/11/19
 * @description Map数据类型
 */


public class MapWarningDataBean implements DataWrapperBean {

    private DataWarpperBeanTypeEnum WARNING_DATA_BEAN_TYPE_ENUM = DataWarpperBeanTypeEnum.MAP_TYPE;

    private Map<String, List<WarningBean>> warningBeanMap;

    private long size = 0;

    public MapWarningDataBean(Map<String, List<WarningBean>> warningBeanMap) {
        this.warningBeanMap = new HashMap<String, List<WarningBean>>(warningBeanMap);
    }

    /**
     * 判断集合中是否有Key
     * @param key 查询的Key
     * @return true 有; false 无
     */
    public boolean containsKey(String key) {
        if(null == warningBeanMap || warningBeanMap.isEmpty()) {
            return false;
        }

        return warningBeanMap.containsKey(key);
    }

    /**
     * 两个MapWarningBeanDataBean集合融合
     * @param otherMapWarningDataBean 另一个Bean
     */
    public synchronized void addWarningDataBeanMap(MapWarningDataBean otherMapWarningDataBean) {
        if( null == otherMapWarningDataBean || otherMapWarningDataBean.isEmpty()) {
            return;
        }

        Set<Map.Entry<String, List<WarningBean>>> otherWarningDataBeanSet = otherMapWarningDataBean.getWarningBeanMap().entrySet();
        for(Map.Entry<String, List<WarningBean>> otherWarningDataBean : otherWarningDataBeanSet) {
            String otherWarningDataBeanKey = otherWarningDataBean.getKey();
            this.addWarningDataBeanList(otherWarningDataBeanKey, otherWarningDataBean.getValue());
        }
    }

    /**
     * 添加特定key的集合warningBeanList
     * @param key warningBeanList类型
     * @param warningBeanList warningBeanList
     */
    public synchronized void  addWarningDataBeanList(String key, List<WarningBean> warningBeanList) {
        if(null == key || warningBeanList == null || warningBeanList.isEmpty()) {
            return;
        }

        if(null == warningBeanMap) {
            warningBeanMap = new HashMap<String, List<WarningBean>>();
        }

        if(warningBeanMap.containsKey(key)) {
            warningBeanMap.get(key).addAll(warningBeanList);
        } else {
            warningBeanMap.put(key, warningBeanList);
        }

        //update size
        size += warningBeanList.size();
    }

    /**
     * 添加特定的warningBean
     * @param key warningBean类型
     * @param warningBean warningBean
     */
    public synchronized void addWarningDataBean(String key, WarningBean warningBean) {
        if(null == key || null == warningBean) {
            return;
        }

        if(null == warningBeanMap) {
            warningBeanMap = new HashMap<String, List<WarningBean>>();
        }

        if(warningBeanMap.containsKey(key)) {
            warningBeanMap.get(key).add(warningBean);
        } else {
            List<WarningBean> warningBeanList = new ArrayList<WarningBean>();
            warningBeanList.add(warningBean);
            warningBeanMap.put(key, warningBeanList);
        }

        //update size
        size += 1;
    }

    /**
     * 判断是否空集合
     * @return
     */
    @Override
    public boolean isEmpty() {
        if( null == warningBeanMap || warningBeanMap.isEmpty()) {
            return true;
        }

        return false;
    }

    @Override
    public long size() {
        return size;
    }

    public Map<String, List<WarningBean>> getWarningBeanMap() {
        return warningBeanMap;
    }

    public void setWarningBeanMap(Map<String, List<WarningBean>> warningBeanMap) {
        this.warningBeanMap = warningBeanMap;
    }

    @Override
    public DataWarpperBeanTypeEnum getDataBeanType() {
        return WARNING_DATA_BEAN_TYPE_ENUM;
    }
}

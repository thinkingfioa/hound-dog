package org.vlis.dog.util;

import org.vlis.dog.bean.WarningBean;

import java.util.Comparator;

/**
 * @author thinking_fioa
 * @createTime 2017/12/2
 * @description
 */


class WarningBeanComparator implements Comparator<WarningBean> {

    private static final WarningBeanComparator INSTANCE = new WarningBeanComparator();

    public static WarningBeanComparator getInstance() {
        return INSTANCE;
    }

    private WarningBeanComparator() { }
    /**
     * 比较两个WarningBean的大小.
     * 对各个属性进行比较。
     * @param w1 第一个WarningBean
     * @param w2 第二个WarningBean
     * @return 返回负数表示w1 < w2; 返回零表示w1 = w2; 返回正数表示w1 > w2
     */
    @Override
    public int compare(WarningBean w1, WarningBean w2) {
        if(null == w1 || null == w2) {
            throw new NullPointerException("parameters is null.");
        }

        // 比较父类型: alarmParentType
        if(! w1.getAlarmParentType().equals(w2.getAlarmParentType())) {
            return w1.getAlarmParentType().compareTo(w2.getAlarmParentType());
        }

        // 比较子类型: alarmType
        if( ! w1.getAlarmType().equals(w2.getAlarmType())) {
            return w1.getAlarmType().compareTo(w2.getAlarmType());
        }

        // 以下的比较都是同一个类型。父类型：alarmParentType和子类型: alarmType都相同
        // 属性: applicationKey
        if( null != w1.getApplicationKey() && ! w1.getApplicationKey().equals(w2.getApplicationKey())) {
            return w1.getApplicationKey().compareTo(w2.getApplicationKey());
        }

        if( null != w2.getApplicationKey() && ! w2.getApplicationKey().equals(w1.getApplicationKey())) {
            return w2.getApplicationKey().compareTo(w1.getApplicationKey());
        }

        // 属性: SourceIp
        if( null != w1.getSourceIp() && ! w1.getSourceIp().equals(w2.getSourceIp())) {
            return w1.getSourceIp().compareTo(w2.getSourceIp());
        }

        if( null != w2.getSourceIp() && ! w2.getSourceIp().equals(w1.getSourceIp())) {
            return w2.getSourceIp().compareTo(w1.getSourceIp());
        }

        // 属性: SourceIp
        if( null != w1.getSourceIp() && ! w1.getSourceIp().equals(w2.getSourceIp())) {
            return w1.getSourceIp().compareTo(w2.getSourceIp());
        }

        if( null != w2.getSourceIp() && ! w2.getSourceIp().equals(w1.getSourceIp())) {
            return w2.getSourceIp().compareTo(w1.getSourceIp());
        }

        // 属性: SourcePort
        if( null != w1.getSourcePort() && ! w1.getSourcePort().equals(w2.getSourcePort())) {
            return w1.getSourcePort().compareTo(w2.getSourcePort());
        }

        if( null != w2.getSourcePort() && ! w2.getSourcePort().equals(w1.getSourcePort())) {
            return w2.getSourcePort().compareTo(w1.getSourcePort());
        }

        // 属性: destionationIp
        if( null != w1.getDestinationIp() && ! w1.getDestinationIp().equals(w2.getDestinationIp())) {
            return w1.getDestinationIp().compareTo(w2.getDestinationIp());
        }

        if( null != w2.getDestinationIp() && ! w2.getDestinationIp().equals(w1.getDestinationIp())) {
            return w2.getDestinationIp().compareTo(w1.getDestinationIp());
        }

        // 属性: destionationPort
        if( null != w1.getDestinationPort() && ! w1.getDestinationPort().equals(w2.getDestinationPort())) {
            return w1.getDestinationPort().compareTo(w2.getDestinationPort());
        }

        if( null != w2.getDestinationPort() && ! w2.getDestinationPort().equals(w1.getDestinationPort())) {
            return w2.getDestinationPort().compareTo(w1.getDestinationPort());
        }

        // 属性: url
        if( null != w1.getUrl() && ! w1.getUrl().equals(w2.getUrl())) {
            return w1.getUrl().compareTo(w2.getUrl());
        }

        if( null != w2.getUrl() && ! w2.getUrl().equals(w1.getUrl())) {
            return w2.getUrl().compareTo(w1.getUrl());
        }

        // 属性: mountPoint
        if( null != w1.getMountPoint() && ! w1.getMountPoint().equals(w2.getMountPoint())) {
            return w1.getMountPoint().compareTo(w2.getMountPoint());
        }

        if( null != w2.getMountPoint() && ! w2.getMountPoint().equals(w1.getMountPoint())) {
            return w2.getMountPoint().compareTo(w1.getMountPoint());
        }

        // 属性: feature
        if( null != w1.getFeature() && ! w1.getFeature().equals(w2.getFeature())) {
            return w1.getFeature().compareTo(w2.getFeature());
        }

        if( null != w2.getFeature() && ! w2.getFeature().equals(w1.getFeature())) {
            return w2.getFeature().compareTo(w1.getFeature());
        }

        return 0;
    }
}

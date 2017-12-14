package org.vlis.dog.bean;

import java.util.List;

/**
 * @author thinking_fioa
 * @createTime 2017/12/14
 * @description 用于封装推理后的告警数据。
 * {@Code traceWarningBean}用于保存链路推理找到的原因，
 * {@Code machineOrJvmList}用户补充解释硬件告警信息
 */


public class WarningBeanWrapper extends WarningBean {

    private WarningBean traceWarningBean;
    List<WarningBean> machineOrJvmList;

    public WarningBeanWrapper(WarningBean traceWarningBean, List<WarningBean> machineOrJvmList) {
        this.traceWarningBean = traceWarningBean;
        this.machineOrJvmList = machineOrJvmList;
    }

    public WarningBean getTraceWarningBean() {
        return traceWarningBean;
    }

    public void setTraceWarningBean(WarningBean traceWarningBean) {
        this.traceWarningBean = traceWarningBean;
    }

    public List<WarningBean> getMachineOrJvmList() {
        return machineOrJvmList;
    }

    public void setMachineOrJvmList(List<WarningBean> machineOrJvmList) {
        this.machineOrJvmList = machineOrJvmList;
    }
}

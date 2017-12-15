package org.vlis.dog.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author thinking_fioa
 * @createTime 2017/12/14
 * @description 用于封装推理后的告警数据。
 * {@Code traceWarningBean}用于保存链路推理找到的原因，
 * {@Code machineOrJvmList}用户补充解释硬件告警信息
 */


public class WarningBeanWrapper extends WarningBean {

    private List<WarningBean> traceWarningBeanList;
    private List<WarningBean> machineOrJvmList;

    public WarningBeanWrapper() {
        this.traceWarningBeanList = new ArrayList<WarningBean>();
        this.machineOrJvmList = new ArrayList<WarningBean>();
    }

    public List<WarningBean> getTraceWarningBeanList() {
        return traceWarningBeanList;
    }

    public void setTraceWarningBeanList(List<WarningBean> traceWarningBeanList) {
        this.traceWarningBeanList = traceWarningBeanList;
    }

    public void setTraceWarningBean(WarningBean warningBean) {
        this.traceWarningBeanList.add(warningBean);
    }

    public List<WarningBean> getMachineOrJvmList() {
        return machineOrJvmList;
    }

    public void setMachineOrJvmList(List<WarningBean> machineOrJvmList) {
        this.machineOrJvmList = machineOrJvmList;
    }

    public void setMachineOrJvm(WarningBean warningBean) {
        this.machineOrJvmList.add(warningBean);
    }
}

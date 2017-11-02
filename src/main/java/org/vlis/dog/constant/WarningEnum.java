package org.vlis.dog.constant;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 告警的Enum变量
 */


public enum WarningEnum {
    // alarmParentType
    MACHINE("machine"),
    DB("db"),
    JVM("jvm"),
    APPLICATION("application"),

    // alarmType
    CPU("cpu"),
    MEMORY("memory"),
    DISK("disk"),
    SQL("sql"),
    CONNECTION("connection"),
    OLD_USE_RATE("oldUseRate"),
    DELAYED("delayed"),
    EXCEPTION("exception"),
    STATUS_CODE("statusCode");

    /**
     * alarmType 告警类型
     */
    WarningEnum (String alarmType) {
        this.alarmType = alarmType;
    }

    /**
     * 告警类型
     */
    private String alarmType;

    public String getAlarmType() {
        return alarmType;
    }
}

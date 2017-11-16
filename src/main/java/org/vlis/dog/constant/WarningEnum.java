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
    CPU_SYSTEM_USAGE("cpuSystemUsage"),
    CPU_USER_USAGE("cpuUserUsage"),
    CPU_LOAD_5("cpuLoad5"),
    MEMORY_USAGE("memoryUsage"),
    SWAP_USAGE("swapUsage"),
    DISK_USAGE("diskUsage"),
    SQL_EXECUTE("sqlExecute"),
    SQL_CONNECTION("sqlConnection"),
    JVM_OLD_USE_RATE("jvmOldUseRate"),
    APPLICATION_EXCEPTION("exception"),
    APPLICATION_STATUS_CODE("statusCode"),
    APPLICATION_CALL("call");


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

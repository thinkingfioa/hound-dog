package org.vlis.dog.constant;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 告警的Enum变量
 */


public enum WarningEnum {

    // alarmParentType
    MACHINE(null, "machine"),
    DB(null, "db"),
    JVM(null, "jvm"),
    APPLICATION(null, "application"),

    // alarmType
    // machine
    CPU_SYSTEM_USAGE(MACHINE, "cpuSystemUsage"),
    CPU_USER_USAGE(MACHINE, "cpuUserUsage"),
    CPU_LOAD_5(MACHINE, "cpuLoad5"),
    MEMORY_USAGE(MACHINE, "memoryUsage"),
    SWAP_USAGE(MACHINE, "swapUsage"),
    DISK_USAGE(MACHINE, "diskUsage"),

    // db
    SQL_EXECUTE(DB, "sqlExecute"),
    SQL_CONNECTION(DB, "sqlConnection"),

    // jvm
    JVM_OLD_USE_RATE(JVM, "jvmOldUseRate"),

    // application
    APPLICATION_EXCEPTION(APPLICATION, "exception"),
    APPLICATION_STATUS_CODE(APPLICATION, "statusCode"),
    APPLICATION_CALL(APPLICATION, "call");


    /**
     * alarmType 告警类型
     */
    WarningEnum (WarningEnum parentWarningEnum, String alarmType) {
        this.parentWarningEnum = parentWarningEnum;
        this.alarmType = alarmType;
    }

    /**
     * 告警类型
     */
    private WarningEnum parentWarningEnum;
    private String alarmType;

    public String getAlarmType() {
        return alarmType;
    }

    public WarningEnum getParentWarningEnum() {
        return parentWarningEnum;
    }
}

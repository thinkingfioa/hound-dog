package org.vlis.dog.bean;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 告警信息Bean
 */


public class WarningBean {
    /**
     * 告警机器Ip
     */
    private String sourceIp;

    /**
     * 告警机器端口
     */
    private String sourcePort;

    /**
     * 告警信息的spanId
     */
    private String spanId;

    /**
     * 告警信息的父spanId
     */
    private String parentSpanId;

    /**
     * 告警信息的transactionId
     */
    private String transactionId;

    /**
     * 告警信息大类型: machine, db, jvm, application
     */
    private String alarmParentType;

    /**
     * 告警详细类型 machine         -> [cpu, memory, socketNumber, disk]
     *            db              -> [sql, connection]
     *            jvm             -> [oldUseRate]
     *            application     -> [delayed, exception, statusCode, method]
     */
    private String alarmType;

    /**
     * 告警发生时间
     */
    private String alarmTime;

    /**
     * 告警信息调用目的Ip
     */
    private String destinationIp;

    /**
     * 告警信息调用目的Port
     */
    private String destinationPort;

    /**
     * 执行时间
     */
    private String executeTime;

    /**
     *   cpu，memory，disk        -> 使用率，
     *    socketNumber           -> 连接数
     *  sql语句，connection       -> sql语句或连接sql语句
     *  oldUseRate               -> 使用率
     *  url, Exception, statusCode, method   ->   具体描述
     */
    private String feature;

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public String getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(String sourcePort) {
        this.sourcePort = sourcePort;
    }

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public String getParentSpanId() {
        return parentSpanId;
    }

    public void setParentSpanId(String parentSpanId) {
        this.parentSpanId = parentSpanId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAlarmParentType() {
        return alarmParentType;
    }

    public void setAlarmParentType(String alarmParentType) {
        this.alarmParentType = alarmParentType;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getDestinationIp() {
        return destinationIp;
    }

    public void setDestinationIp(String destinationIp) {
        this.destinationIp = destinationIp;
    }

    public String getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(String destinationPort) {
        this.destinationPort = destinationPort;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
}

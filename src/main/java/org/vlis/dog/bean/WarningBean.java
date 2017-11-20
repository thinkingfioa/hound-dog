package org.vlis.dog.bean;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 告警信息Bean
 */


public class WarningBean implements Serializable {

    /**
     * 应用关键字, SourceIp_sourcePort
     */
    private String applicationKey;

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
     * 告警信息的traceId
     */
    private String traceId;

    /**
     * 告警信息大类型: machine, db, jvm, application
     */
    private String alarmParentType;

    /**
     * 告警详细类型 machine         -> [cpu, memory, load, swapUsage, disk]
     *            db              -> [sql, connection]
     *            jvm             -> [oldUseRate]
     *            application     -> [delayed, exception, statusCode]
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
     * 执行时间, 以ms为单位
     */
    private String executeTime;

    /**
     * 调用url
     */
    private String url;

    /**
     * 磁盘挂载点
     */
    private String mountPoint;

    /**
     *   cpu，memory，disk        -> 使用率，
     *  sql语句，connection       -> sql语句或连接sql语句
     *  oldUseRate               -> 使用率
     *  Exception, statusCode, method   ->   具体描述
     */
    private String feature;

    public String getApplicationKey() {
        return applicationKey;
    }

    public void setApplicationKey(String applicationKey) {
        this.applicationKey = applicationKey;
    }

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

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMountPoint() {
        return mountPoint;
    }

    public void setMountPoint(String mountPoint) {
        this.mountPoint = mountPoint;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    /**
     * 初始化WarningBean
     * @param attributes WarningBean的所有属性
     * @return WarningBean对象
     */
    public static WarningBean valueOfMap(Map<String, Object> attributes) {
        WarningBean warningBean = new WarningBean();
        warningBean.setApplicationKey(String.valueOf(attributes.get("applicationKey")) );
        warningBean.setSourceIp(String.valueOf(attributes.get("sourceIp")) );
        warningBean.setSourcePort(String.valueOf(attributes.get("sourcePort")) );
        warningBean.setSpanId(String.valueOf(attributes.get("spanId")) );
        warningBean.setParentSpanId(String.valueOf(attributes.get("parentSpanId")) );
        warningBean.setTraceId(String.valueOf(attributes.get("traceId")) );
        warningBean.setAlarmParentType(String.valueOf(attributes.get("alarmParentType")) );
        warningBean.setAlarmType(String.valueOf(attributes.get("alarmType")) );
        warningBean.setAlarmTime(String.valueOf(attributes.get("alarmTime")) );
        warningBean.setDestinationIp(String.valueOf(attributes.get("destinationIp")) );
        warningBean.setDestinationPort(String.valueOf(attributes.get("destinationPort")) );
        warningBean.setExecuteTime(String.valueOf(attributes.get("executeTime")) );
        warningBean.setUrl(String.valueOf(attributes.get("url")) );
        warningBean.setMountPoint(String.valueOf(attributes.get("mountPoint")) );
        warningBean.setFeature(String.valueOf(attributes.get("feature")) );

        return warningBean;
    }

    /**
     * 初始化WarningBean
     * @param jsonObject WarningBean的所有属性
     * @return WarningBean对象
     */
    public static WarningBean valueOfJSONObject(JSONObject jsonObject) {
        WarningBean warningBean = new WarningBean();
        warningBean.setApplicationKey(jsonObject.getString("applicationKey") );
        warningBean.setSourceIp(jsonObject.getString("sourceIp") );
        warningBean.setSourcePort(jsonObject.getString("sourcePort") );
        warningBean.setSpanId(jsonObject.getString("spanId") );
        warningBean.setParentSpanId(jsonObject.getString("parentSpanId") );
        warningBean.setTraceId(jsonObject.getString("traceId") );
        warningBean.setAlarmParentType(jsonObject.getString("alarmParentType") );
        warningBean.setAlarmType(jsonObject.getString("alarmType") );
        warningBean.setAlarmTime(jsonObject.getString("alarmTime") );
        warningBean.setDestinationIp(jsonObject.getString("destinationIp") );
        warningBean.setDestinationPort(jsonObject.getString("destinationPort") );
        warningBean.setExecuteTime(jsonObject.getString("executeTime") );
        warningBean.setUrl(jsonObject.getString("url") );
        warningBean.setMountPoint(jsonObject.getString("mountPoint") );
        warningBean.setFeature(jsonObject.getString("feature") );

        return warningBean;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WarningBean that = (WarningBean) o;

        if (applicationKey != null ? !applicationKey.equals(that.applicationKey) : that.applicationKey != null)
            return false;
        if (sourceIp != null ? !sourceIp.equals(that.sourceIp) : that.sourceIp != null) return false;
        if (sourcePort != null ? !sourcePort.equals(that.sourcePort) : that.sourcePort != null) return false;
        if (spanId != null ? !spanId.equals(that.spanId) : that.spanId != null) return false;
        if (parentSpanId != null ? !parentSpanId.equals(that.parentSpanId) : that.parentSpanId != null) return false;
        if (traceId != null ? !traceId.equals(that.traceId) : that.traceId != null) return false;
        if (alarmParentType != null ? !alarmParentType.equals(that.alarmParentType) : that.alarmParentType != null)
            return false;
        if (alarmType != null ? !alarmType.equals(that.alarmType) : that.alarmType != null) return false;
        if (alarmTime != null ? !alarmTime.equals(that.alarmTime) : that.alarmTime != null) return false;
        if (destinationIp != null ? !destinationIp.equals(that.destinationIp) : that.destinationIp != null)
            return false;
        if (destinationPort != null ? !destinationPort.equals(that.destinationPort) : that.destinationPort != null)
            return false;
        if (executeTime != null ? !executeTime.equals(that.executeTime) : that.executeTime != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (mountPoint != null ? !mountPoint.equals(that.mountPoint) : that.mountPoint != null) return false;
        return feature != null ? feature.equals(that.feature) : that.feature == null;
    }

    @Override
    public int hashCode() {
        int result = applicationKey != null ? applicationKey.hashCode() : 0;
        result = 31 * result + (sourceIp != null ? sourceIp.hashCode() : 0);
        result = 31 * result + (sourcePort != null ? sourcePort.hashCode() : 0);
        result = 31 * result + (spanId != null ? spanId.hashCode() : 0);
        result = 31 * result + (parentSpanId != null ? parentSpanId.hashCode() : 0);
        result = 31 * result + (traceId != null ? traceId.hashCode() : 0);
        result = 31 * result + (alarmParentType != null ? alarmParentType.hashCode() : 0);
        result = 31 * result + (alarmType != null ? alarmType.hashCode() : 0);
        result = 31 * result + (alarmTime != null ? alarmTime.hashCode() : 0);
        result = 31 * result + (destinationIp != null ? destinationIp.hashCode() : 0);
        result = 31 * result + (destinationPort != null ? destinationPort.hashCode() : 0);
        result = 31 * result + (executeTime != null ? executeTime.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (mountPoint != null ? mountPoint.hashCode() : 0);
        result = 31 * result + (feature != null ? feature.hashCode() : 0);
        return result;
    }
}

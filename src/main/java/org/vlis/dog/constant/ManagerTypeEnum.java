package org.vlis.dog.constant;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 告警处理manager常量
 */


public enum ManagerTypeEnum {
    /**
     * 管理器Manager处理链路的最后一个节点
     */
    DEFAULT_MANAGER("defaultManager", "[ 管理器链的尾节点 ]"),

    /**
     * 管理器中压缩节点
     */
    CONDENSE_MANAGER("condenseManager", "[ 告警信息压缩节点 ]"),

    /**
     * 管理器中分类节点
     */
    CLASSIFY_MANAGER("classifyManager", "[ 告警分类节点 ]"),

    /**
     * 管理器中根源定位节点
     */
    REASONING_MANAGER("reasoningManager", "[ 告警根源性追踪节点 ]"),

    /**
     * 管理器中结合告警信息
     */
    COMBINE_MANAGER("combineManager", "[ 告警信息结合节点 ]"),

    /**
     * 管理器中告警去重节点
     */
    DEREPLICATION_MANAGER("dereplicationManager", "[ 告警去重节点 ]");


    ManagerTypeEnum(String managerName, String description) {
        this.managerName = managerName;
        this.description = description;
    }

    private String managerName;
    private String description;

    public String getManagerName() {
        return managerName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ManagerTypeEnum{" +
                "description=" + description +
                '}';
    }
}

package org.vlis.dog.constant;

/**
 * @author thinking_fioa
 * @createTime 2017/11/3
 * @description
 */


public enum StartUpRoleEnum {

    DISTRIBUTED_FACADE("DistributedTrace", "分布式启动角色"),
    THIRD_PARTY_FACADE("ThirdParty", "第三方启动角色");

    StartUpRoleEnum(String startUpRoleName, String description) {
        this.startUpRoleName = startUpRoleName;
        this.description = description;
    };

    private String startUpRoleName;
    private String description;

    public String getStartUpRoleName() {
        return startUpRoleName;
    }

    public String getDescription() {
        return description;
    }
}

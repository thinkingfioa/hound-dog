package org.vlis.dog.constant;

/**
 * @author thinking_fioa
 * @createTime 2017/11/20
 * @description 包装数据类型
 */


public enum DataWarpperBeanTypeEnum {

    /**
     * 包装的是列表数据
     */
    LIST_TYPE("List-Type", "List数据"),

    /**
     * 包装的是Map集合数据
     */
    MAP_TYPE("Map-Type", "Map数据集合");

    private String type;
    private String description;

    DataWarpperBeanTypeEnum(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}

package org.vlis.dog.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.bean.DataWrapperBean;
import org.vlis.dog.constant.DataWrapperBeanTypeEnum;
import org.vlis.dog.constant.ManagerTypeEnum;

/**
 * @author thinking_fioa
 * @createTime 2017/11/21
 * @description 合并相关的告警信息。比如，将机器信息添加到应用告警库中
 */


public class CombineManager extends AbstractManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(CombineManager.class);

    public CombineManager(ItfManager successorManager) {
        super(successorManager, ManagerTypeEnum.COMBINE_MANAGER, DataWrapperBeanTypeEnum.LIST_TYPE);
    }

    /**
     *
     * @param warningBeans 告警数据集
     * @return  {@code DataWrapperBean } 归一化数据集，也就是压缩所有相同的数据
     */
    @Override
    public DataWrapperBean cleanWarningBeans(DataWrapperBean warningBeans) {
        //todo::
        LOGGER.info("{} starting...", ManagerTypeEnum.COMBINE_MANAGER.getDescription());
        return null;
    }
}

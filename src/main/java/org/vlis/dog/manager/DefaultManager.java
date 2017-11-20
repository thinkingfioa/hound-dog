package org.vlis.dog.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.bean.DataWrapperBean;
import org.vlis.dog.constant.DataWarpperBeanTypeEnum;
import org.vlis.dog.constant.ManagerTypeEnum;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description
 */


public final class DefaultManager extends AbstractManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultManager.class);

    public DefaultManager() {
        super(null, ManagerTypeEnum.DEFAULT_MANAGER, DataWarpperBeanTypeEnum.LIST_TYPE);
    }

    /**
     * 传播链的最后一个节点。准备返回最终数据
     * @param warningBeans 待处理的数据集
     * @return
     */
    @Override
    public DataWrapperBean cleanWarningBeans(DataWrapperBean warningBeans) {
        LOGGER.info("{} starting ...", ManagerTypeEnum.DEFAULT_MANAGER.getDescription());
        return null;
    }
}

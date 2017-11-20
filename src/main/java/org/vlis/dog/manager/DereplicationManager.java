package org.vlis.dog.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.bean.DataWrapperBean;
import org.vlis.dog.constant.DataWarpperBeanTypeEnum;
import org.vlis.dog.constant.ManagerTypeEnum;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 告警信息去重模块
 */


public final class DereplicationManager extends AbstractManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DereplicationManager.class);

    public DereplicationManager( ItfManager successorManager) {
        super(successorManager, ManagerTypeEnum.CONDENSE_MANAGER, DataWarpperBeanTypeEnum.MAP_TYPE);
    }

    /**
     * 去重模块
     * @param warningBeans 待处理的数据集
     * @return
     */
    @Override
    public DataWrapperBean cleanWarningBeans(DataWrapperBean warningBeans) {
        //todo:: 归并的数据处理
        LOGGER.info("{} starting...", ManagerTypeEnum.CONDENSE_MANAGER.getDescription());
        return null;
    }

}

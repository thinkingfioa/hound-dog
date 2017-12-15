package org.vlis.dog.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.bean.DataWrapperBean;
import org.vlis.dog.bean.ListWarningDataBean;
import org.vlis.dog.constant.DataWrapperBeanTypeEnum;
import org.vlis.dog.constant.ManagerTypeEnum;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 告警统计模块
 */


public class AddupManager extends AbstractManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddupManager.class);

    public AddupManager(ItfManager successorManager) {
        super(successorManager, ManagerTypeEnum.DEREPLICATION_MANAGER, DataWrapperBeanTypeEnum.LIST_TYPE);
    }

    /**
     *
     * @param warningBeans 告警数据集
     * @return  {@code DataWrapperBean } 归一化数据集，也就是压缩所有相同的数据
     */
    @Override
    public DataWrapperBean cleanWarningBeans(DataWrapperBean warningBeans) {
        LOGGER.info("{} starting...", ManagerTypeEnum.DEREPLICATION_MANAGER.getDescription());
        DataWrapperBean storeAfterCleanWarningBeans = new ListWarningDataBean();

        return storeAfterCleanWarningBeans;
    }
}

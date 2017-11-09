package org.vlis.dog.manager.dereplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.bean.WarningBean;
import org.vlis.dog.constant.ManagerTypeEnum;
import org.vlis.dog.factory.ItfDataConvertFactory;
import org.vlis.dog.manager.AbstractManager;
import org.vlis.dog.manager.ItfManager;

import java.util.List;
import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 告警信息merge模块
 */


public final class DereplicationManager extends AbstractManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DereplicationManager.class);

    public DereplicationManager(ItfDataConvertFactory dataConvertFactory, ItfManager successorManager) {
        super(dataConvertFactory, successorManager, ManagerTypeEnum.CONDENSE_MANAGER);
    }

    /**
     *
     * @param warningBeanMap 待处理的数据集
     * @return
     */
    @Override
    public List<WarningBean> cleanWarningBeanList(Map<String, List<WarningBean>> warningBeanMap) {
        //todo:: 归并的数据处理
        LOGGER.info("{} starting...", ManagerTypeEnum.CONDENSE_MANAGER.getDescription());
        return null;
    }

}

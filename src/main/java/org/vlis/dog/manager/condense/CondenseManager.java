package org.vlis.dog.manager.condense;

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
 * @description
 */


public class CondenseManager extends AbstractManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(CondenseManager.class);

    public CondenseManager(ItfDataConvertFactory dataConvertFactory, ItfManager successorManager) {
        super(dataConvertFactory, successorManager, ManagerTypeEnum.DEREPLICATION_MANAGER);
    }

    /**
     *
     * @param warningBeanMap 告警数据集
     * @return  {@code List<WarningBean> } 归一化数据集
     */
    @Override
    public List<WarningBean> cleanWarningBeanList(Map<String, List<WarningBean>> warningBeanMap) {
        //todo::
        LOGGER.info("{} starting...", ManagerTypeEnum.DEREPLICATION_MANAGER.getDescription());
        return null;
    }
}

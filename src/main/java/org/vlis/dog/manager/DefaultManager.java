package org.vlis.dog.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.bean.WarningBean;
import org.vlis.dog.constant.ManagerTypeEnum;
import org.vlis.dog.factory.ItfDataConvertFactory;

import java.util.List;
import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description
 */


public final class DefaultManager extends AbstractManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultManager.class);

    public DefaultManager(ItfDataConvertFactory dataConvertFactory) {
        super(dataConvertFactory, null, ManagerTypeEnum.DEFAULT_MANAGER);
    }

    /**
     * 传播链的最后一个节点。准备返回最终数据
     * @param warningBeanMap 待处理的数据集
     * @return
     */
    @Override
    public List<WarningBean> cleanWarningBeanList(Map<String, List<WarningBean>> warningBeanMap) {
        LOGGER.info("{} starting ...", ManagerTypeEnum.DEFAULT_MANAGER.getDescription());
        return null;
    }
}

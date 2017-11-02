package org.vlis.dog.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.manager.ItfManager;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 提供Apm告警根源性定位门面，避免理解后续复杂逻辑
 */


public class ApmFacade extends AbstractCommonFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApmFacade.class);

    /**
     * 定义整个管理链的整个流转处理顺序
     * @return {@code ItfManager} rootManager管理器链的头部
     */
    @Override
    public ItfManager buildManagerChain() {
        LOGGER.info(" build ApmFacade starting....");
        //todo::
        return null;
    }
}

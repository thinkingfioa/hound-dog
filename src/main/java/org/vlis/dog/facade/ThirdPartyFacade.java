package org.vlis.dog.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.manager.ItfManager;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 提供第三方数据接入模块。目前为实现。
 */


public class ThirdPartyFacade extends AbstractCommonFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThirdPartyFacade.class);

    /**
     * 定义整个管理链的整个流转处理顺序
     * @return {@code ItfManager} rootManager管理器链的头部
     */
    @Override
    public ItfManager buildManagerChain() {
        LOGGER.info(" build ThirdPartyFacade starting....");
        //todo::
        return null;
    }

}

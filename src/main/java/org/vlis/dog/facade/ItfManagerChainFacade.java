package org.vlis.dog.facade;

import org.vlis.dog.manager.ItfManager;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 提供抽象类结构
 */


public interface ItfManagerChainFacade {

    /**
     * 构造管理器Manage链
     * @return {@code ItfManager} rootManager管理器链的头部
     */
    ItfManager buildManagerChain();
}

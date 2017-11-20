package org.vlis.dog.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.factory.DistributedDataConvertFactory;
import org.vlis.dog.manager.DefaultManager;
import org.vlis.dog.manager.ItfManager;
import org.vlis.dog.manager.ClassifyManager;
import org.vlis.dog.manager.CondenseManager;
import org.vlis.dog.manager.DereplicationManager;
import org.vlis.dog.manager.ReasoningManager;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 提供Apm告警根源性定位门面，避免理解后续复杂逻辑
 */


public class DistributedFacade extends AbstractCommonFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistributedFacade.class);

    /**
     * 定义整个管理链的整个流转处理顺序
     * @return {@code ItfManager} rootManager管理器链的头部
     */
    @Override
    public ItfManager buildManagerChain() {
        LOGGER.info(" build DistributedFacade starting....");

        DefaultManager endChainManager = new DefaultManager();
        CondenseManager condenseManager = new CondenseManager(endChainManager);
        ReasoningManager reasoningManager = new ReasoningManager(condenseManager);
        DereplicationManager dereplicationManager = new DereplicationManager(reasoningManager);
        ClassifyManager classifyManager = new ClassifyManager(dereplicationManager);

        LOGGER.error("ManagerChain is : {} -> {} -> {} -> {} -> {}", dereplicationManager.getDescription(), classifyManager.getDescription(),
                reasoningManager.getDescription(), condenseManager.getDescription(), endChainManager.getDescription());

        LOGGER.info(" build DistributedFacade end....");
        return dereplicationManager;
    }
}

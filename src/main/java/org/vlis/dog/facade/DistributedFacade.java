package org.vlis.dog.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.manager.*;

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
        AddupManager addupManager = new AddupManager(endChainManager);
        ReasoningManager reasoningManager = new ReasoningManager(addupManager);
        CombineManager combineManager = new CombineManager(reasoningManager);
        DereplicationManager dereplicationManager = new DereplicationManager(combineManager);
        ClassifyManager classifyManager = new ClassifyManager(dereplicationManager);
        // 链路的节点: classifyManager -> dereplicationManager -> combineManager -> reasoningManager -> addupManager
        LOGGER.error("ManagerChain is : {} -> {} -> {} -> {} -> {} -> {}", classifyManager.getDescription(), dereplicationManager.getDescription(),
                combineManager.getDescription(), reasoningManager.getDescription(), addupManager.getDescription(), endChainManager.getDescription());

        LOGGER.info(" build DistributedFacade end....");
        return classifyManager;
    }
}

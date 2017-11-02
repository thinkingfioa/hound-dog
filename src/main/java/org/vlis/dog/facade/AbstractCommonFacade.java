package org.vlis.dog.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.bean.WarningBean;
import org.vlis.dog.manager.ItfManager;

import java.util.List;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 普通的门面，可以和ManagerChain共同使用
 */


public abstract class AbstractCommonFacade implements ItfManagerChainFacade, ItfFacade{

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCommonFacade.class);

    /**
     * 管理器的起始点: localRootManager
     */
    private ItfManager localRootManager;

    /**
     * 启动根源性分析
     * @param warningBeanList 告警分析集合
     * @return {@code List<WarningBean> } 返回根源性告警
     */
    @Override
    public List<WarningBean> startRootCauseAnalysis(List<WarningBean> warningBeanList) {
        LOGGER.info(" startRootCauseAnalysis...");
        if(null == localRootManager) {
            localRootManager = buildManagerChain();
        }

        return localRootManager.dealWithWarningBeanList(warningBeanList);
    }

    /**
     * 启动根源性分析
     * @param rootManager 管理器的起始点: rootManager
     * @param warningBeanList 告警分析集合
     * @return {@code List<WarningBean> } 返回根源性告警
     */
    @Override
    public List<WarningBean> startBootCauseAnalysisWithRootManager(ItfManager rootManager, List<WarningBean> warningBeanList) {
        if(null == rootManager) {
            throw new NullPointerException("rootManager is null.");
        }
        LOGGER.info(" startBootCauseAnalysisWithRootManager...");

        return rootManager.dealWithWarningBeanList(warningBeanList);
    }

    public ItfManager getLocalRootManager() {
        return localRootManager;
    }

    public void setLocalRootManager(ItfManager localRootManager) {
        this.localRootManager = localRootManager;
    }
}

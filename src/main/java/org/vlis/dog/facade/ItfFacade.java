package org.vlis.dog.facade;

import org.vlis.dog.bean.WarningBean;
import org.vlis.dog.manager.ItfManager;

import java.util.List;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 整个告警根源性分析的Facade
 */


public interface ItfFacade {
    /**
     * 启动根源性分析
     * @param warningBeanList 告警分析集合
     * @return {@code List<WarningBean> } 返回根源性告警
     */
    List<WarningBean> startRootCauseAnalysis(List<WarningBean> warningBeanList);

    /**
     * 启动根源性分析
     * @param rootManager 管理器的起始点: rootManager
     * @param warningBeanList 告警分析集合
     * @return {@code List<WarningBean> } 返回根源性告警
     */
    List<WarningBean> startBootCauseAnalysisWithRootManager(ItfManager rootManager, List<WarningBean> warningBeanList);
}

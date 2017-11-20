package org.vlis.dog.facade;

import org.vlis.dog.bean.DataWrapperBean;
import org.vlis.dog.manager.ItfManager;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 整个告警根源性分析的Facade
 */


public interface ItfFacade {
    /**
     * 启动根源性分析
     * @param DataWarpperBean 告警分析集合
     * @return {DataWrapperBean } 返回根源性告警
     */
    DataWrapperBean startRootCauseAnalysis(DataWrapperBean warningBeans);

    /**
     * 启动根源性分析
     * @param rootManager 管理器的起始点: rootManager
     * @param DataWarpperBean 告警分析集合
     * @return {@code DataWrapperBean } 返回根源性告警
     */
    DataWrapperBean startBootCauseAnalysisWithRootManager(ItfManager rootManager, DataWrapperBean warningBeans);
}

package org.vlis.dog.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.bean.DataWrapperBean;
import org.vlis.dog.bean.WarningBean;
import org.vlis.dog.constant.DataWarpperBeanTypeEnum;
import org.vlis.dog.constant.ManagerTypeEnum;
import org.vlis.dog.factory.ItfDataConvertFactory;

import java.util.List;
import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 推理Manager
 */


public class ReasoningManager extends AbstractManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReasoningManager.class);

    public ReasoningManager( ItfManager successorManager) {
        super(successorManager, ManagerTypeEnum.REASONING_MANAGER, DataWarpperBeanTypeEnum.MAP_TYPE);
    }

    /**
     * 数据进行推理处理，判断最有根源性定位告警
     * @param warningBeans 满足要求的数据集合
     * @return
     */
    @Override
    public DataWrapperBean cleanWarningBeans(DataWrapperBean warningBeans) {
        //todo:: 归并的数据处理
        LOGGER.info("{} starting...", ManagerTypeEnum.REASONING_MANAGER.getDescription());
        return null;
    }
}

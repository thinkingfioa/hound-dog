package org.vlis.dog.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.bean.DataWrapperBean;
import org.vlis.dog.constant.DataWarpperBeanTypeEnum;
import org.vlis.dog.constant.ManagerTypeEnum;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 告警分类管理器
 */


public class ClassifyManager extends AbstractManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassifyManager.class);

    public ClassifyManager(ItfManager successorManager) {
        super(successorManager, ManagerTypeEnum.CLASSIFY_MANAGER, DataWarpperBeanTypeEnum.LIST_TYPE);
    }

    /**
     * 按照要求，对数据进行分类处理
     * @param warningBeans 提供规格的Map数据集
     * @return {@code List<WarningBean} 返回清理后的数据
     */
    @Override
    public DataWrapperBean cleanWarningBeans(DataWrapperBean warningBeans) {
        //todo:: 分类的数据
        LOGGER.info("{} starting... ", ManagerTypeEnum.CLASSIFY_MANAGER.getDescription());
        return null;
    }
}

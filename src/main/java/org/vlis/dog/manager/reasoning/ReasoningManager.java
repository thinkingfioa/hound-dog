package org.vlis.dog.manager.reasoning;

import org.vlis.dog.bean.WarningBean;
import org.vlis.dog.factory.ItfDataConvertFactory;
import org.vlis.dog.manager.AbstractManager;

import java.util.List;
import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description
 */


public class ReasoningManager extends AbstractManager {

    public ReasoningManager(ItfDataConvertFactory dataConvertFactory) {
        super(dataConvertFactory);
    }

    /**
     * 数据进行推理处理，判断最有根源性定位告警
     * @param warningBeanMap 满足要求的数据集合
     * @return
     */
    @Override
    public List<WarningBean> cleanWarningBeanList(Map<String, List<WarningBean>> warningBeanMap) {
        //todo:: 归并的数据处理
        return null;
    }
}

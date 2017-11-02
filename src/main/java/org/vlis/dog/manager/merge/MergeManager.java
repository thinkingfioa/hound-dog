package org.vlis.dog.manager.merge;

import org.vlis.dog.bean.WarningBean;
import org.vlis.dog.factory.ItfDataConvertFactory;
import org.vlis.dog.manager.AbstractManager;

import java.util.List;
import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 告警信息merge模块
 */


public final class MergeManager extends AbstractManager {

    public MergeManager(ItfDataConvertFactory dataConvertFactory) {
        super(dataConvertFactory);
    }

    /**
     *
     * @param warningBeanMap
     * @return
     */
    @Override
    public List<WarningBean> cleanWarningBeanList(Map<String, List<WarningBean>> warningBeanMap) {
        //todo:: 归并的数据处理
        return null;
    }

}

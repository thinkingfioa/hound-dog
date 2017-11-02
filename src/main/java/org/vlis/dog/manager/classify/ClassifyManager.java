package org.vlis.dog.manager.classify;

import org.vlis.dog.bean.WarningBean;
import org.vlis.dog.factory.ItfDataConvertFactory;
import org.vlis.dog.manager.AbstractManager;

import java.util.List;
import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 分类管理器
 */


public final class ClassifyManager extends AbstractManager {

    public ClassifyManager(ItfDataConvertFactory dataConvertFactory) {
        super(dataConvertFactory);
    }

    /**
     * 按照要求，对数据进行分类处理
     * @param warningBeanMap 提供规格的Map数据集
     * @return {@code List<WarningBean} 返回清理后的数据
     */
    @Override
    public List<WarningBean> cleanWarningBeanList(Map<String, List<WarningBean>> warningBeanMap) {
        //todo:: 分类的数据
        return null;
    }
}

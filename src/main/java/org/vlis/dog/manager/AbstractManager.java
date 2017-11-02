package org.vlis.dog.manager;

import org.vlis.dog.bean.WarningBean;
import org.vlis.dog.factory.ItfDataConvertFactory;

import java.util.List;
import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 抽象的数据整理
 */


public abstract class AbstractManager implements ItfManager {

    private ItfDataConvertFactory dataConvertFactory;

    protected AbstractManager(ItfDataConvertFactory dataConvertFactory) {
        this.dataConvertFactory = dataConvertFactory;
    }

    /**
     * 分为2个步骤。1> 第一步：数据整理，利用dataConvertFactory进行整理
     *            2> 第二步：数据清洗，子类实现的接口：dealWithWarningBeanList0
     * @param warningBeanList 待清洗的告警数据
     * @return  {@code List<WarningBean> } 清洗后的数据集合
     */
    @Override
    public List<WarningBean> dealWithWarningBeanList(List<WarningBean> warningBeanList) {
        // 整理满足数据规格
        Map<String, List<WarningBean>> warningBeanMap = dataConvertFactory.dataConvert(warningBeanList);
        // 对应的Manager清洗数据
        return cleanWarningBeanList(warningBeanMap);
    }

    /**
     * 清洗数据集合
     * @param warningBeanMap 满足要求的数据规格
     * @return 清洗后数据集合 {@code List<WarningBean> }
     */
    protected abstract List<WarningBean> cleanWarningBeanList(Map<String, List<WarningBean>> warningBeanMap);
}

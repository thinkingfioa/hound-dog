package org.vlis.dog.factory;

import org.vlis.dog.bean.WarningBean;
import org.vlis.dog.constant.ManagerTypeEnum;

import java.util.List;
import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 提供第3方告警追踪数据的转换工厂
 */


public class ThirdPartyDataConvertFactory implements ItfDataConvertFactory{

    private static final ThirdPartyDataConvertFactory INSTANCE = new ThirdPartyDataConvertFactory();

    @Override
    public Map<String, List<WarningBean> > dataConvert(List<WarningBean> warningBeanList, ManagerTypeEnum managerTypeEnum) {
        //todo::
        return null;
    }


    /**
     * 返回全局唯一单例
     * @return
     */
    public static ThirdPartyDataConvertFactory getInstance() {
        return INSTANCE;
    }
}

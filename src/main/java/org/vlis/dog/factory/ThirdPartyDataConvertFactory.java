package org.vlis.dog.factory;

import org.vlis.dog.bean.WarningBean;

import java.util.List;
import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 提供第3方告警追踪数据的转换工厂
 */


public class ThirdPartyDataConvertFactory implements ItfDataConvertFactory{

    @Override
    public Map<String, List<WarningBean> > dataConvert(List<WarningBean> warningBeanList) {
        //todo::
        return null;
    }
}

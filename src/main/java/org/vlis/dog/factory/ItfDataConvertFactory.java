package org.vlis.dog.factory;

import org.vlis.dog.bean.WarningBean;
import org.vlis.dog.constant.ManagerTypeEnum;

import java.util.List;
import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 数据转换工厂抽象类
 */


public interface ItfDataConvertFactory {

    Map<String, List<WarningBean>> dataConvert(List<WarningBean> warningBeanList, ManagerTypeEnum managerTypeEnum);
}

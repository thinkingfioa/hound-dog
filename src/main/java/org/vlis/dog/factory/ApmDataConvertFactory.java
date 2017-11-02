package org.vlis.dog.factory;

import org.vlis.dog.bean.WarningBean;
import org.vlis.dog.constant.ManagerTypeEnum;

import java.util.List;
import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 提供Apm告警追踪数据的转换工厂
 */


public class ApmDataConvertFactory implements ItfDataConvertFactory {

    @Override
    public Map<String, List<WarningBean>> dataConvert(List<WarningBean> warningBeanList, ManagerTypeEnum managerTypeEnum) {

        //todo::
        if(ManagerTypeEnum.DEREPLICATION_MANAGER.equals(managerTypeEnum)) {
            // 告警去重节点
        }

        if(ManagerTypeEnum.CLASSIFY_MANAGER.equals(managerTypeEnum)) {
            // 告警分类节点
        }

        if(ManagerTypeEnum.REASONING_MANAGER.equals(managerTypeEnum)) {
            // 告警根源性追踪节点
        }

        if(ManagerTypeEnum.CONDENSE_MANAGER.equals(managerTypeEnum)) {
            // 告警信息压缩节点
        }

        if(ManagerTypeEnum.DEFAULT_MANAGER.equals(managerTypeEnum)) {
            //管理器链的尾节点
        }

        return null;
    }


}

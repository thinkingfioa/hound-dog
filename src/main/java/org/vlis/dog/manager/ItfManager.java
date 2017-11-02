package org.vlis.dog.manager;

import org.vlis.dog.bean.WarningBean;

import java.util.List;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 处理器的上层接口类
 */


public interface ItfManager {

    List<WarningBean> dealWithWarningBeanList(List<WarningBean> warningBeanList);
}

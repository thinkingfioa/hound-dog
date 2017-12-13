package org.vlis.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.bean.DataWrapperBean;
import org.vlis.dog.bean.ListWarningDataBean;
import org.vlis.dog.bean.WarningBean;
import org.vlis.dog.config.Config;
import org.vlis.dog.constant.StartUpRoleEnum;
import org.vlis.dog.facade.DistributedFacade;
import org.vlis.dog.facade.ItfFacade;
import org.vlis.dog.facade.ThirdPartyFacade;
import org.vlis.dog.start.WarningDataFetch;

import java.io.IOException;
import java.util.List;

/**
 * @author thinking_fioa
 * @createTime 2017/12/13
 * @description 论文中实验二的测试Demo
 * 如果想测试论文中实验二，需要做以下几点:
 * 1. DistributedFacade.java中，修改最后一个链路节点
 * 2. DereplicationManager.java 部分执行逻辑不用执行
 */


public class Test2Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Test2Main.class);


    public static void main(String [] args) {
        long startTime = System.currentTimeMillis();

        String projectKey = "192.168.2.114";
        String from  = "2017-12-13 12:26:56";
        String to = "2017-12-14 20:28:56";

        try {
            Config.configInit();
            LOGGER.info(" Hound-Dog start....");
            ItfFacade rootCauseAnalysisFacade = getRootCauseAnalysisFacade();
            List<WarningBean> warningBeanList = WarningDataFetch.fetchAllWarningBeans(projectKey, from, to);
            long beforeWarningBeansSize = warningBeanList.size();
            DataWrapperBean rootWarningBeanList = rootCauseAnalysisFacade.startRootCauseAnalysis(ListWarningDataBean.valueOf(warningBeanList));
            long afterWarningBeansSize = rootWarningBeanList.size();

            LOGGER.warn("warning compress rate is ", (double)beforeWarningBeansSize / afterWarningBeansSize);
        } catch (IOException ioEx) {
            LOGGER.error(" happen error.", ioEx);
        } finally {
            LOGGER.info(" cost Time: {} ms", System.currentTimeMillis() - startTime);
        }
    }

    private static ItfFacade getRootCauseAnalysisFacade() {
        if(Config.getStartUpRole().equals(StartUpRoleEnum.DISTRIBUTED_FACADE.getStartUpRoleName())) {
            return new DistributedFacade();
        } else if( Config.getStartUpRole().equals(StartUpRoleEnum.THIRD_PARTY_FACADE.getStartUpRoleName())) {
            return new ThirdPartyFacade();
        } else {
            throw new NullPointerException("can't start Hound-dog");
        }
    }
}

package org.vlis.dog.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.bean.WarningBean;
import org.vlis.dog.config.Config;
import org.vlis.dog.constant.StartUpRoleEnum;
import org.vlis.dog.facade.DistributedFacade;
import org.vlis.dog.facade.ItfFacade;
import org.vlis.dog.facade.ThirdPartyFacade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 启动程序
 */


public class StartHoundDog {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartHoundDog.class);

    public static void main(String [] args) {
        long startTime = System.currentTimeMillis();

        try {
            Config.configInit();
            LOGGER.info(" Hound-Dog start....");
            ItfFacade rootCauseAnalysisFacade = getRootCauseAnalysisFacade();
            List<WarningBean> warningBeanList = new ArrayList<WarningBean>();
            long beforeWarningBeansSize = warningBeanList.size();
            List<WarningBean> rootWarningBeanList = rootCauseAnalysisFacade.startRootCauseAnalysis(warningBeanList);
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

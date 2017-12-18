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
import org.vlis.dog.util.DateTimeUtil;

import java.io.IOException;
import java.util.List;

/**
 * @author thinking_fioa
 * @createTime 2017/12/18
 * @description
 */


public class Test4Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Test2Main.class);

    private static final String DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
    private static final int INTERVAL = 6; // unit: minute

    public static void main(String [] args) {
        long startTime = System.currentTimeMillis();

        String projectKey = "192.168.2.114";
        String from  = "2017-12-13 12:26:56";
        String to = "2017-12-13 20:28:56";

        try {
            ItfFacade rootCauseAnalysisFacade = new DistributedFacade();
            long beforeWarningBeansSize = 0;
            long afterWarningBeansSize = 0;
            Config.configInit();
            LOGGER.info(" Hound-Dog start....");
            List<DateTimeUtil.StartEndTimeWrapper> timeList = DateTimeUtil.splitTime(DATE_FORMAT, from, to, INTERVAL);
            for(DateTimeUtil.StartEndTimeWrapper oneTime : timeList) {
                List<WarningBean> warningBeanList = WarningDataFetch.fetchAllWarningBeans(projectKey, oneTime.getStartTime(), oneTime.getEndTime());
                beforeWarningBeansSize += warningBeanList.size();
                DataWrapperBean rootWarningBeanList = rootCauseAnalysisFacade.startRootCauseAnalysis(ListWarningDataBean.valueOf(warningBeanList));
                afterWarningBeansSize += rootWarningBeanList.size();
            }

            LOGGER.warn("warning compress rate is ", (double)beforeWarningBeansSize / afterWarningBeansSize);
        } catch (IOException ioEx) {
            LOGGER.error(" happen error.", ioEx);
        } finally {
            LOGGER.info(" cost Time: {} ms", System.currentTimeMillis() - startTime);
        }
    }
}

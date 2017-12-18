package org.vlis.dog.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.bean.*;
import org.vlis.dog.bloomfilter.BloomFilter;
import org.vlis.dog.bloomfilter.impl.InMemoryBloomFilter;
import org.vlis.dog.constant.DataWrapperBeanTypeEnum;
import org.vlis.dog.constant.ManagerTypeEnum;
import org.vlis.dog.util.RedundancyAlgorithmUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 告警统计模块
 */


public class AddupManager extends AbstractManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddupManager.class);
    DataWrapperBean storeAfterCleanWarningBeans;
    // 集合方法倍数
    private static final int MULTIPLES = 10;
    // 错误率
    private static final double FPP = 0.01;

    public AddupManager(ItfManager successorManager) {
        super(successorManager, ManagerTypeEnum.DEREPLICATION_MANAGER, DataWrapperBeanTypeEnum.LIST_TYPE);
        storeAfterCleanWarningBeans = new ListWarningDataBean();
    }

    /**
     *
     * @param warningBeans 告警数据集
     * @return  {@code DataWrapperBean } 归一化数据集，也就是压缩所有相同的数据
     */
    @Override
    public DataWrapperBean cleanWarningBeans(DataWrapperBean warningBeans) {
        LOGGER.info("{} starting...", ManagerTypeEnum.DEREPLICATION_MANAGER.getDescription());

        storeAfterCleanWarningBeans.addDataWrapperBean(warningBeans);


        return storeAfterCleanWarningBeans;
    }

    /**
     * 进行汇总
     * @return
     */
    public static DataWrapperBean getAddupResult(DataWrapperBean warningBeans) {
        DataWrapperBean addupWarningBeans = new ListWarningDataBean();
        List<WarningBean> warningBeanList = ((ListWarningDataBean)warningBeans).getDataBeans();
        // 多线程下，这个bloomFilter是要注意的。warningBeans必须所有短时间片做完，保存最大集合
        BloomFilter<String> bloomFilter = new InMemoryBloomFilter<String>(MULTIPLES * warningBeanList.size(), FPP);
        for(WarningBean oneWarningBean : warningBeanList) {
            if(oneWarningBean instanceof WarningBeanWrapper) {
                List<WarningBean> warningBeanWrapperList = new ArrayList<WarningBean>();
                warningBeanWrapperList.addAll( ((WarningBeanWrapper)oneWarningBean).getMachineOrJvmList() );
                warningBeanWrapperList.addAll( ((WarningBeanWrapper)oneWarningBean).getTraceWarningBeanList() );

                if(!RedundancyAlgorithmUtil.isRedundancyWarningListOfAddupManager(bloomFilter, warningBeanWrapperList)) {
                    ((ListWarningDataBean)addupWarningBeans).add(oneWarningBean);
                }
            } else {
                if(!RedundancyAlgorithmUtil.isRedundancyWarningOfAddupManager(bloomFilter, oneWarningBean)) {
                    ((ListWarningDataBean)addupWarningBeans).add(oneWarningBean);
                }
            }
        }
        return addupWarningBeans;
    }

}

package org.vlis.dog.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.bean.DataWrapperBean;
import org.vlis.dog.bean.MapWarningDataBean;
import org.vlis.dog.bean.WarningBean;
import org.vlis.dog.bloomfilter.BloomFilter;
import org.vlis.dog.bloomfilter.impl.InMemoryBloomFilter;
import org.vlis.dog.constant.DataWrapperBeanTypeEnum;
import org.vlis.dog.constant.ManagerTypeEnum;
import org.vlis.dog.constant.WarningEnum;
import org.vlis.dog.util.RedundancyAlgorithmUtil;
import org.vlis.dog.util.WarningDataExtractUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 告警信息去重模块
 */


public final class DereplicationManager extends AbstractManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DereplicationManager.class);

    // 集合方法倍数
    private static final int MULTIPLES = 10;
    // 错误率
    private static final double FPP = 0.01;

    public DereplicationManager( ItfManager successorManager) {
        super(successorManager, ManagerTypeEnum.CONDENSE_MANAGER, DataWrapperBeanTypeEnum.MAP_TYPE);
    }

    /**
     * 去重模块，将分类后的数据，去重复
     * @param warningBeans 待处理的数据集
     * @return
     */
    @Override
    public DataWrapperBean cleanWarningBeans(DataWrapperBean warningBeans) {
        LOGGER.info("{} starting...", ManagerTypeEnum.CONDENSE_MANAGER.getDescription());
        DataWrapperBean storeAfterCleanWarningBeans = new MapWarningDataBean();

        // application 去重
        cleanWarningBeansOfApplication(storeAfterCleanWarningBeans, warningBeans);

        // Jvm 去重
        cleanWarningBeansOfJvm(storeAfterCleanWarningBeans, warningBeans);

        // DB 去重
        cleanWarningBeansOfDb(storeAfterCleanWarningBeans, warningBeans);

        // Machine 去重
        cleanWarningBeansOfMachine(storeAfterCleanWarningBeans, warningBeans);

        // 处理器最后一个处理节点
        return cleanWarningBeansOfEnd(storeAfterCleanWarningBeans, warningBeans);
    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.MACHINE} Machine机器类型
     * 第一步：根据AlarmParentType获取数据
     * 第二步：挑选出最大的值
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     */
    @Override
    public void cleanWarningBeansOfMachine(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(!canAcceptDataBeanType(pendingDataBean) || !(pendingDataBean instanceof MapWarningDataBean)
                || !(storeAfterClean instanceof MapWarningDataBean)) {
            throw new IllegalStateException("DataBean is error.");
        }

        // 选出最糟糕的值
        chooseWorstWarningBean( storeAfterClean, pendingDataBean, WarningEnum.MACHINE);

    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.DB} Db机器类型
     * 第一步：根据AlarmParentType获取数据
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     */
    @Override
    public void cleanWarningBeansOfDb(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(!canAcceptDataBeanType(pendingDataBean) || !(pendingDataBean instanceof MapWarningDataBean)
                || !(storeAfterClean instanceof MapWarningDataBean)) {
            throw new IllegalStateException("DataBean is error.");
        }

        // 第一步：
        // 获取分类后Db应用数据
        MapWarningDataBean dbAlarmTypeMap = WarningDataExtractUtil.extractWarningDataBean(pendingDataBean, WarningEnum.DB);

        // 这个留一个坑，不要问我为什么，我就是不愿意写的太好。

    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.JVM} Jvm机器类型
     * 第一步：根据AlarmParentType获取数据
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     */
    @Override
    public void cleanWarningBeansOfJvm(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(!canAcceptDataBeanType(pendingDataBean) || !(pendingDataBean instanceof MapWarningDataBean)
                || !(storeAfterClean instanceof MapWarningDataBean)) {
            throw new IllegalStateException("DataBean is error.");
        }

        // 选出最糟糕的值
        chooseWorstWarningBean( storeAfterClean, pendingDataBean, WarningEnum.JVM);
    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.APPLICATION} Application类型
     * 第一步：根据AlarmParentType获取数据
     * 第二步：
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     */
    @Override
    public void cleanWarningBeansOfApplication(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(!canAcceptDataBeanType(pendingDataBean) || !(pendingDataBean instanceof MapWarningDataBean)
                || !(storeAfterClean instanceof MapWarningDataBean)) {
            throw new IllegalStateException("DataBean is error.");
        }

        // 第一步：
        // 获取分类后Application应用数据
        MapWarningDataBean applicationAlarmTypeMap = WarningDataExtractUtil.extractWarningDataBean(pendingDataBean, WarningEnum.APPLICATION);

        // 第二步：利用布隆过滤器，过滤已经重复的值: Bloom Filter


        // 需要考虑的AlarmType类型有: sqlExecute、sqlConnection、exception、statusCode、call这5种类型
        Map<String, List<WarningBean>> warningBeansMap = applicationAlarmTypeMap.getDataBeans();
        Set<Map.Entry<String, List<WarningBean>>> warningBeanSet = warningBeansMap.entrySet();
        BloomFilter<String> bloomFilter = new InMemoryBloomFilter<String>(MULTIPLES * warningBeanSet.size(), FPP);

        for(Map.Entry<String, List<WarningBean>> warningBeanEntry : warningBeanSet) {
            String traceIdKey  = warningBeanEntry.getKey();
            List<WarningBean> warningBeanList = warningBeanEntry.getValue();
            // 判断是否已经处理过
            if(!RedundancyAlgorithmUtil.isRedundancyWarningList(bloomFilter, warningBeanList)) {
                // 第一次出现
                ((MapWarningDataBean)storeAfterClean).addWarningDataBeanList(traceIdKey, warningBeanList);
            }
        }
    }

    /**
     * 从值中选择最糟糕的值
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     * @param enumType {@link WarningEnum} 告警父类型
     */
    private void chooseWorstWarningBean(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean, WarningEnum enumType) {
        // 第一步：
        // 获取分类后WarningEnum类型的数据
        MapWarningDataBean  parentAlarmTypeMap = WarningDataExtractUtil.extractWarningDataBean(pendingDataBean, enumType);

        // 第二步：找出这段时间内最大的值
        Map<String, List<WarningBean>> warningBeansMap = parentAlarmTypeMap.getDataBeans();
        Set<Map.Entry<String, List<WarningBean>>> warningBeanSet =  warningBeansMap.entrySet();
        for(Map.Entry<String, List<WarningBean>> warningBeanEntry : warningBeanSet) {
            String warningBeanKey = warningBeanEntry.getKey();
            List<WarningBean> warningBeanList = warningBeanEntry.getValue();

            //挑选最大的值
            WarningBean bestWarningBean = null;
            for(WarningBean oneWarningBean : warningBeanList) {
                if(null == bestWarningBean || bestWarningBean.getFeature().compareToIgnoreCase(oneWarningBean.getFeature()) <0 ) {
                    bestWarningBean = oneWarningBean;
                }
            }
            if(null != bestWarningBean) {
                // 保存起来
                ((MapWarningDataBean)storeAfterClean).addWarningDataBean(warningBeanKey, bestWarningBean);
            }

        }

    }

}

package org.vlis.dog.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.bean.DataWrapperBean;
import org.vlis.dog.bean.MapWarningDataBean;
import org.vlis.dog.bean.WarningBean;
import org.vlis.dog.constant.DataWrapperBeanTypeEnum;
import org.vlis.dog.constant.ManagerTypeEnum;
import org.vlis.dog.constant.WarningEnum;
import org.vlis.dog.util.WarningDataExtractUtil;
import org.vlis.dog.util.WordJointUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author thinking_fioa
 * @createTime 2017/11/21
 * @description 合并相关的告警信息。比如，将机器信息添加到应用告警库中
 */


public class CombineManager extends AbstractManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(CombineManager.class);

    public CombineManager(ItfManager successorManager) {
        super(successorManager, ManagerTypeEnum.COMBINE_MANAGER, DataWrapperBeanTypeEnum.MAP_TYPE);
    }

    /**
     *
     * @param warningBeans 告警数据集
     * @return  {@code DataWrapperBean } 归一化数据集，也就是压缩所有相同的数据
     */
    @Override
    public DataWrapperBean cleanWarningBeans(DataWrapperBean warningBeans) {
        LOGGER.info("{} starting...", ManagerTypeEnum.COMBINE_MANAGER.getDescription());

        DataWrapperBean storeAfterCleanWarningBeans = new MapWarningDataBean();

        // application 合并
        cleanWarningBeansOfApplication(storeAfterCleanWarningBeans, warningBeans);

        // Jvm 合并
        cleanWarningBeansOfJvm(storeAfterCleanWarningBeans, warningBeans);

        // DB 合并
        cleanWarningBeansOfDb(storeAfterCleanWarningBeans, warningBeans);

        // Machine 合并
        cleanWarningBeansOfMachine(storeAfterCleanWarningBeans, warningBeans);


        return null;
    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.MACHINE} Machine机器类型接口
     * 第一步，抽取出Machine数据
     * 第二步，补充进入APPLICATION
     * 第三步，将未找到的machine数据，就加入{@code storeAfterClean}
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     */
    @Override
    protected void cleanWarningBeansOfMachine(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(! canAcceptDataBeanType(pendingDataBean)|| !(pendingDataBean instanceof MapWarningDataBean)
                || !(storeAfterClean instanceof MapWarningDataBean) ) {
            throw new IllegalStateException("DataBean is error.");
        }

        // 第一步:
        // 获取Machine数据
        MapWarningDataBean machineAlarmTypeMap = WarningDataExtractUtil.extractWarningDataBean(pendingDataBean, WarningEnum.MACHINE);
        List<String> visitedMachineKey = new ArrayList<String>();

        // 第二步:
        // 将硬件信息加入到Application中
        Map<String, List<WarningBean>> storeWarningBean = ((MapWarningDataBean)storeAfterClean).getDataBeans();
        Set<Map.Entry<String, List<WarningBean>>> storeWarningBeanSet = storeWarningBean.entrySet();
        for(Map.Entry<String, List<WarningBean>> oneStoreWarningBeanEntry : storeWarningBeanSet) {
            String traceIdKey = oneStoreWarningBeanEntry.getKey();
            List<WarningBean> warningBeanList = oneStoreWarningBeanEntry.getValue();

            for(WarningBean oneWarningBean : warningBeanList) {
                for(WarningEnum oneWarningEnum : WarningEnum.values()) {
                    if(WarningEnum.MACHINE.equals(oneWarningEnum.getParentWarningEnum()) ) {
                        String machineSourceIpKey = WordJointUtil.joinWords(oneWarningEnum.getParentWarningEnum().getAlarmType(),
                                oneWarningEnum.getAlarmType(), oneWarningBean.getApplicationKey());
                        if(machineAlarmTypeMap.containsKey(machineSourceIpKey)) {
                            // 找到对应的applicationKey，将JVM信息补充进去
                            ((MapWarningDataBean)storeAfterClean).addWarningDataBean(traceIdKey, oneWarningBean);
                            visitedMachineKey.add(machineSourceIpKey);
                        }
                    }
                }
            }
        }

        // 第三步:
        // 未补充的jvm数据补充到storeAfterClean中
        dealWithSurplusWarningBeans(storeAfterClean, machineAlarmTypeMap, visitedMachineKey);
    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.DB} Db类型接口
     * 第一步，抽取出Db数据
     * 第二步，补充进入APPLICATION
     * 第三步，将未找到的db数据，就加入{@code storeAfterClean}
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     */
    @Override
    protected void cleanWarningBeansOfDb(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(! canAcceptDataBeanType(pendingDataBean)|| !(pendingDataBean instanceof MapWarningDataBean)
                || !(storeAfterClean instanceof MapWarningDataBean) ) {
            throw new IllegalStateException("DataBean is error.");
        }

        //todo:: 应该不用。省略
    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.JVM} Jvm类型接口
     * 第一步，抽取出JVM数据
     * 第二步，遍历Application，将jvm数据补充进去
     * 第三步，将未找到的jvm数据，就加入{@code storeAfterClean}
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     */
    @Override
    protected void cleanWarningBeansOfJvm(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(! canAcceptDataBeanType(pendingDataBean)|| !(pendingDataBean instanceof MapWarningDataBean)
                || !(storeAfterClean instanceof MapWarningDataBean) ) {
            throw new IllegalStateException("DataBean is error.");
        }

        // 第一步：
        // 获取分类后Application应用数据
        MapWarningDataBean jvmAlarmTypeMap = WarningDataExtractUtil.extractWarningDataBean(pendingDataBean, WarningEnum.JVM);
        List<String> visitedJVMKey = new ArrayList<String>();

        // 第二步:
        // 将jvm数据加到Application中
        Map<String, List<WarningBean>> storeWarningBean = ((MapWarningDataBean)storeAfterClean).getDataBeans();
        Set<Map.Entry<String, List<WarningBean>>> storeWarningBeanSet = storeWarningBean.entrySet();
        for(Map.Entry<String, List<WarningBean>> oneStoreWarningBeanEntry : storeWarningBeanSet) {
            String traceIdKey = oneStoreWarningBeanEntry.getKey();
            List<WarningBean> warningBeanList = oneStoreWarningBeanEntry.getValue();

            for(WarningBean oneWarningBean : warningBeanList) {
                String applicationKey = WordJointUtil.warningEnumJointWord(WarningEnum.JVM, oneWarningBean.getApplicationKey());
                if(jvmAlarmTypeMap.containsKey(applicationKey)) {
                    // 找到对应的applicationKey，将JVM信息补充进去
                    ((MapWarningDataBean)storeAfterClean).addWarningDataBean(traceIdKey, oneWarningBean);
                    visitedJVMKey.add(applicationKey);
                }
            }
        }

        // 第三步:
        // 未补充的jvm数据补充到storeAfterClean中
        dealWithSurplusWarningBeans(storeAfterClean, jvmAlarmTypeMap, visitedJVMKey);
    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.APPLICATION} Application类型接口
     * 对于Application数据，是将其他的数据合并到Application中
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     */
    @Override
    public void cleanWarningBeansOfApplication(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {

        MapWarningDataBean applicationAlarmTypeMap = WarningDataExtractUtil.extractWarningDataBean(pendingDataBean, WarningEnum.APPLICATION);

        storeAfterClean.addDataWrapperBean(applicationAlarmTypeMap);
    }

    /**
     * 合并剩余部分找不到APPLICATION
     * @param storeAfterClean 存放的处理后数据
     * @param originWarningBean 原始数据
     * @param visitedApplicationKey 已经处理的数据
     */
    private void dealWithSurplusWarningBeans(DataWrapperBean storeAfterClean, MapWarningDataBean originWarningBean, List<String> visitedApplicationKey) {
        Map<String, List<WarningBean>> originWarningBeanMap = originWarningBean.getDataBeans();
        Set<Map.Entry<String, List<WarningBean>>> originWarningBeanSet = originWarningBeanMap.entrySet();
        for(Map.Entry<String, List<WarningBean>> oneWarningBeanEntry : originWarningBeanSet) {
            String key = oneWarningBeanEntry.getKey();
            if(!visitedApplicationKey.contains(key)) {
                List<WarningBean> surplusWarningBeanList = oneWarningBeanEntry.getValue();
                ((MapWarningDataBean) storeAfterClean).addWarningDataBeanList(key, surplusWarningBeanList);
            }
        }
    }

}

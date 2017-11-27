package org.vlis.dog.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.bean.DataWrapperBean;
import org.vlis.dog.bean.ListWarningDataBean;
import org.vlis.dog.bean.MapWarningDataBean;
import org.vlis.dog.bean.WarningBean;
import org.vlis.dog.constant.DataWrapperBeanTypeEnum;
import org.vlis.dog.constant.ManagerTypeEnum;
import org.vlis.dog.constant.WarningEnum;
import org.vlis.dog.util.WordJointUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 告警分类管理器
 */


public class ClassifyManager extends AbstractManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassifyManager.class);

    public ClassifyManager(ItfManager successorManager) {
        super(successorManager, ManagerTypeEnum.CLASSIFY_MANAGER, DataWrapperBeanTypeEnum.LIST_TYPE);
    }

    /**
     * 按照要求，对数据进行分类处理
     * @param warningBeans 提供规格的Map数据集
     * @return {@see org.vlis.dog.bean.MapWarningDataBean} 返回清理后的数据
     */
    @Override
    public DataWrapperBean cleanWarningBeans(DataWrapperBean warningBeans) {
        LOGGER.info("{} starting... ", ManagerTypeEnum.CLASSIFY_MANAGER.getDescription());
        DataWrapperBean storeAfterCleanWarningBeans = new MapWarningDataBean();

        // application 分类
        cleanWarningBeansOfApplication(storeAfterCleanWarningBeans, warningBeans);

        // Jvm 分类
        cleanWarningBeansOfJvm(storeAfterCleanWarningBeans, warningBeans);

        // DB 分类
        cleanWarningBeansOfDb(storeAfterCleanWarningBeans, warningBeans);

        // Machine 分类
        cleanWarningBeansOfMachine(storeAfterCleanWarningBeans, warningBeans);

        // 处理器最后一个处理节点
        return cleanWarningBeansOfEnd(storeAfterCleanWarningBeans, warningBeans);
    }


    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.MACHINE} Machine机器类型接口
     * 第一步：根据AlarmParentType获取数据
     * 第二步：机器类型种类比较多, 按照各个硬件资源维度来分类
     */
    @Override
    public void cleanWarningBeansOfMachine(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(! canAcceptDataBeanType(pendingDataBean)|| !(pendingDataBean instanceof ListWarningDataBean)
                || !(storeAfterClean instanceof MapWarningDataBean) ) {
            throw new IllegalStateException("DataBean is error.");
        }

        // 第一步:
        // machine对应的AlarmParentType，是:WarningEnum.MACHINE
        List<WarningBean> machineAlarmTypeBean = new ArrayList<WarningBean>();

        List<WarningBean> pendingWarningBeanList = ((ListWarningDataBean)pendingDataBean).getDataBeans();

        for(WarningBean oneWarningBean : pendingWarningBeanList) {
            if(WarningEnum.MACHINE.getAlarmType().equals(oneWarningBean.getAlarmParentType())) {
                machineAlarmTypeBean.add(oneWarningBean);
            }
        }

        // 第二步：
        // 硬件资源按照AlarmType和SourceIp作为Key，进行分类
        for(WarningBean oneWarningBean : machineAlarmTypeBean) {
            String sourceIpKey = WordJointUtil.joinWords(oneWarningBean.getAlarmParentType(), oneWarningBean.getAlarmType(), oneWarningBean.getSourceIp());
            ((MapWarningDataBean)storeAfterClean).addWarningDataBean(sourceIpKey, oneWarningBean);
        }

    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.DB} db机器类型接口
     * 第一步：根据AlarmParentType获取数据
     * 第二步：处理{@code org.vlis.dog.constant.WarningEnum.DB}和traceId为Key, 将数据库数据分割分类
     * @param storeAfterClean 存放的处理后数据
     * @param pendingDataBean 待处理数据
     */
    @Override
    public void cleanWarningBeansOfDb(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(! canAcceptDataBeanType(pendingDataBean) || !(pendingDataBean instanceof ListWarningDataBean)
                || !(storeAfterClean instanceof MapWarningDataBean) ) {
            throw new IllegalStateException("DataBean is error.");
        }

        //第一步：
        // database对应的AlarmParentType 是: WarningEnum.DB
        List<WarningBean> dbAlarmTypeBean = new ArrayList<WarningBean>();

        List<WarningBean> pendingWarningBeanList = ((ListWarningDataBean)pendingDataBean).getDataBeans();

        for(WarningBean oneWarningBean : pendingWarningBeanList) {
            if(WarningEnum.DB.getAlarmType().equals(oneWarningBean.getAlarmParentType())) {
                dbAlarmTypeBean.add(oneWarningBean);
            }
        }

        // 第二步：以traceId为Key,分类
        for(WarningBean oneWarningBean : dbAlarmTypeBean) {
            // 直接将traceID加入到Applicaiton。不要问我为啥不写DB，因为我一个人写，心情不好不想写的太好！！！！
            String traceIdKey = WordJointUtil.warningEnumJointWord(WarningEnum.APPLICATION, oneWarningBean.getTraceId());
            ((MapWarningDataBean)storeAfterClean).addWarningDataBean(traceIdKey, oneWarningBean);
        }

    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.JVM} jvm机器类型接口
     * 第一步：根据AlarmParentType获取数据
     * 第二步：处理{@code org.vlis.dog.constant.WarningEnum.JVM}和applicationKey作为Key, 将应用数据分割分类
     * @param storeAfterClean 存放的处理后数据，需要的类型是: {@see org.vlis.dog.bean.MapWarningDataBean}
     * @param pendingDataBean 待处理数据 需要的类型是: {@see org.vlis.dog.bean.ListWarningDataBean}
     */
    @Override
    public void cleanWarningBeansOfJvm(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(! canAcceptDataBeanType(pendingDataBean)|| !(pendingDataBean instanceof ListWarningDataBean)
                || !(storeAfterClean instanceof MapWarningDataBean) ){
            throw new IllegalStateException("DataBean is error.");
        }

        //第一步:
        // JVM对应的AlarmparentType是: WarningEnum.JVM
        List<WarningBean> jvmAlarmTypeBean = new ArrayList<WarningBean>();
        List<WarningBean> pendingWarningBeanList = ((ListWarningDataBean)pendingDataBean).getDataBeans();

        for(WarningBean oneWarningBean : pendingWarningBeanList) {
            if(WarningEnum.JVM.getAlarmType().equals(oneWarningBean.getAlarmParentType())) {
                jvmAlarmTypeBean.add(oneWarningBean);
            }
        }

        //第二步：以applicationKey为Key, 分类
        for(WarningBean oneWarningBean : jvmAlarmTypeBean) {
            String applicationKey = WordJointUtil.warningEnumJointWord(WarningEnum.JVM, oneWarningBean.getApplicationKey());
            ((MapWarningDataBean) storeAfterClean).addWarningDataBean(applicationKey, oneWarningBean);
        }
    }

    /**
     * 处理{@see org.vlis.dog.constant.WarningEnum.APPLICATION} Application类型接口
     * 第一步：根据AlarmParentType获取数据
     * 第二部：{@code org.vlis.dog.constant.WarningEnum.APPLICATION}和traceId作为Key，将分布式调用所产生的信息放到一起。
     * @param storeAfterClean 存放的处理后数据，需要的类型是: {@see org.vlis.dog.bean.MapWarningDataBean}
     * @param pendingDataBean 待处理数据 需要的类型是: {@see org.vlis.dog.bean.ListWarningDataBean}
     */
    @Override
    public void cleanWarningBeansOfApplication(DataWrapperBean storeAfterClean, DataWrapperBean pendingDataBean) {
        if(! canAcceptDataBeanType(pendingDataBean) || !(pendingDataBean instanceof ListWarningDataBean)
                || !(storeAfterClean instanceof MapWarningDataBean) ) {
            throw new IllegalStateException("DataBean is error.");
        }

        //第一步：
        // Application对应的AlarmParentType 是: WarningEnum.APPLICATION
        List<WarningBean> applicationAlarmTypeBean = new ArrayList<WarningBean>();

        List<WarningBean> pendingWarningBeanList = ((ListWarningDataBean)pendingDataBean).getDataBeans();

        for(WarningBean oneWarningBean : pendingWarningBeanList) {
            if(WarningEnum.APPLICATION.getAlarmType().equals(oneWarningBean.getAlarmParentType())) {
                applicationAlarmTypeBean.add(oneWarningBean);
            }
        }

        // 第二步：以traceId为Key,分类
        for(WarningBean oneWarningBean : applicationAlarmTypeBean) {
            String traceIdKey = WordJointUtil.warningEnumJointWord(WarningEnum.APPLICATION,oneWarningBean.getTraceId());
            ((MapWarningDataBean)storeAfterClean).addWarningDataBean(traceIdKey, oneWarningBean);
        }
    }
}

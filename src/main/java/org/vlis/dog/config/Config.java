package org.vlis.dog.config;


import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.constant.StartUpRoleEnum;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 配置文件读取
 */


public final class Config {
    private static final Logger LOGGER = LoggerFactory.getLogger(Config.class);

    private static final String HOUND_DOG_PROPERTIES = "hound-dog.properties";
    private static String startUpRole;


    private static final int DEFAULT_ES_PORT = 9300;
    private static String apmIndex = "apm2.0-";
    private static String clusterName = "Apm-Dev";
    private static String clientModel = "TRANSPORT";
    private static String hostStr = "10.10.102.101";

    private static String warningSource = "elasticsearch";

    private static final String WORD_JOINT_SIGN = "--thinking+fioa--";

    public static void configInit() throws IOException {
        LOGGER.info("Reading configuration:" + HOUND_DOG_PROPERTIES);

        InputStream in = Config.class.getClassLoader().getResourceAsStream(HOUND_DOG_PROPERTIES);
        Properties props = new Properties();
        try {
            props.load(in);
        } finally {
            in.close();
        }

        Map<String, Object> map = ConfigFileHelper.getConfigSettings(props);
        startUpRole = BaseConfig.getStringProperty(map, "start_up_role", StartUpRoleEnum.DISTRIBUTED_FACADE.getStartUpRoleName());

        apmIndex = props.getProperty("es_index_prefix");
        clusterName = props.getProperty("es_cluster_name");
        clientModel = props.getProperty("es_client_mode");
        hostStr = props.getProperty("es_host_address");

        warningSource = props.getProperty("warning_source");

        //zmqReceiveAddress = "tcp://0.0.0.0:" + BaseConfig.getIntProperty(map, "message_port", messageReceivePort);

    }

    public static String getStartUpRole() {
        return startUpRole;
    }

    public static int getDefaultEsPort() {
        return DEFAULT_ES_PORT;
    }

    public static String getApmIndex() {
        return apmIndex;
    }

    public static String getClusterName() {
        return clusterName;
    }

    public static String getClientModel() {
        return clientModel;
    }

    public static String getHostStr() {
        return hostStr;
    }

    public static String getWarningSource() {
        return warningSource;
    }

    public static String getWordJointSign() {
        return WORD_JOINT_SIGN;
    }
}

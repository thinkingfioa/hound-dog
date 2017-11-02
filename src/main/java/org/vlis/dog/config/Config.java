package org.vlis.dog.config;


import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author thinking_fioa
 * @createTime 2017/11/2
 * @description 配置文件读取
 */


public final class Config {
    private static final Logger LOGGER = LoggerFactory.getLogger(Config.class);

    private static final String HOUND_DOG_PROPERTIES = "hound-dog.properties";

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
        //zmqReceiveAddress = "tcp://0.0.0.0:" + BaseConfig.getIntProperty(map, "message_port", messageReceivePort);
    }
}

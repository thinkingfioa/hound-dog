package org.vlis.dog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.config.Config;

/**
 * @author thinking_fioa
 * @createTime 2017/11/16
 * @description
 */


public final class EsSearchHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsSearchHelper.class);

    private EsSearchHelper() {
    }

    /**
     * 生成Index的名字
     * @param projectKey 项目名字
     * @param type index的类型
     * @return indexName
     * @throws NullPointerException
     */
    public static String getIndexName(final String projectKey, final String type) throws NullPointerException{
        if(null == projectKey || null == type ) {
            throw new NullPointerException("parameters is null");
        }

        return Config.getApmIndex() + projectKey + "-" + type.toLowerCase() + "-1";
    }
}

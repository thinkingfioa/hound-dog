package org.vlis.dog.start;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vlis.dog.bean.WarningBean;
import org.vlis.dog.client.ConnectEs;
import org.vlis.dog.config.Config;
import org.vlis.dog.util.EsSearchHelper;
import org.vlis.dog.util.FileReadWriteUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author thinking_fioa
 * @createTime 2017/11/16
 * @description
 */


public final class WarningDataFetch {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarningDataFetch.class);

    private static Client client = ConnectEs.instance().getClient();
    private static final String INDEX_TYPE = "warning";

    private static final String ELASTICSEARCH_SOURCE = "elasticsearch";
    private static final String DISK_SOURCE = "disk";

    private static final String WARNING_DATA_FILE_ADDRESS = System.getProperty("user.dir")+"/src/main/resources/warningData.bk";
    /**
     * 获取时间段[from, to]的告警数据
     * @param projectKey projectKey，项目Key
     * @param from 起始时间
     * @param to 结束时间
     * @return
     */
    public static List<WarningBean> fetchAllWarningBeans(String projectKey, String from, String to) {
        if(null == projectKey || null == from || null == to || from.compareToIgnoreCase(to) > 0) {
            throw new IllegalArgumentException("parameters is wrong.");
        }

        if( ELASTICSEARCH_SOURCE.equals(Config.getWarningSource()) ) {
            return fetchAllWarningBeansFromEs(projectKey, from, to);
        }else if( DISK_SOURCE.equals(Config.getWarningSource()) ) {
            return fetchAllWarningBeansFromDisk();
        }else {
           return new ArrayList<WarningBean>();
        }
    }

    /**
     * 获取时间段[from, to]的告警数据，从Es中获取
     * @param projectKey projectKey，项目Key
     * @param from 起始时间
     * @param to 结束时间
     * @return
     */
    public static List<WarningBean> fetchAllWarningBeansFromEs(String projectKey, String from, String to) {
        if(null == projectKey || null == from || null == to || from.compareToIgnoreCase(to) > 0) {
            throw new IllegalArgumentException("parameters is wrong.");
        }


        String indexName = EsSearchHelper.getIndexName(projectKey, INDEX_TYPE);
        List<WarningBean> warningBeanList = new ArrayList<WarningBean>();

        try {

            QueryBuilder queryBuilder = QueryBuilders.boolQuery().filter(
                    QueryBuilders.rangeQuery("alarmTime").from(from).to(to));

            SearchResponse searchResponse = client.prepareSearch(indexName).setTypes(INDEX_TYPE)
                    .setQuery(queryBuilder).setSize(10000).execute().actionGet();

            System.out.println("address: " + WARNING_DATA_FILE_ADDRESS);

            if(searchResponse.getHits().getTotalHits() > 0) {
                for(SearchHit searchHit : searchResponse.getHits()) {
                    Map<String, Object> map = searchHit.getSource();
                    WarningBean warningBean = WarningBean.valueOfMap(map);

                    warningBeanList.add(warningBean);
                }
            }

            FileReadWriteUtil.getInstance().writeByBufferedWriter(JSON.toJSONString(warningBeanList), WARNING_DATA_FILE_ADDRESS);

            System.out.println("warningBeanList [size]" + warningBeanList.size());

        } catch (Exception ex) {
            LOGGER.error("fetch data error. [projectKey]{}, [from]{}, [to]{}", projectKey, from, to);
        }

        return warningBeanList;
    }

    /**
     * 获取告警数据，从磁盘获取
     * @return
     */
    public static List<WarningBean> fetchAllWarningBeansFromDisk() {

        System.out.println("address: " + WARNING_DATA_FILE_ADDRESS);

        String content = FileReadWriteUtil.getInstance().readByBuffeeredReader(WARNING_DATA_FILE_ADDRESS);
        @SuppressWarnings("unchecked")
        List<JSONObject> jsonObjectList = JSON.parseObject(content, List.class);

        List<WarningBean> warningBeanList = new ArrayList<WarningBean>();
        for(JSONObject jsonObject : jsonObjectList) {
            WarningBean warningBean = WarningBean.valueOfJSONObject(jsonObject);
            warningBeanList.add(warningBean);
        }

        System.out.println("warningBeanList [size]" + warningBeanList.size());
        return warningBeanList;
    }

    /**
     * 获取于某条traceId相关的告警信息
     * @param projectKey projectKey，项目Key
     * @param from 起始时间
     * @param to 结束时间
     * @param traceId 链路Id
     * @return
     */
    public static List<WarningBean> fetchAllWarningBeans(String projectKey, String from, String to, String traceId) {
        //todo:: 未开始
        return null;
    }

    public static void main(String [] args){
        String projectKey = "192.168.2.114";
        String from  = "2017-11-16 19:26:56";
        String to = "2017-11-16 19:28:56";

        WarningDataFetch.fetchAllWarningBeans(projectKey, from, to);
    }
}

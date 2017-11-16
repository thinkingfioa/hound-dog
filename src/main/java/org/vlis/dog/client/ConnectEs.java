package org.vlis.dog.client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.NodeBuilder;
import org.vlis.dog.config.Config;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author thinking_fioa
 * @createTime 2017/11/16
 * @description 连接ES的Client
 */


public final class ConnectEs {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectEs.class);
    private static ConnectEs instance = null;
    private Client client = null;

    private ConnectEs() {

        if (client == null) {
            if ("NODE".equals(Config.getClientModel())) {
                client = NodeBuilder.nodeBuilder().clusterName(Config.getClusterName()).client(true).node().client();
            } else if ("TRANSPORT".equals(Config.getClientModel())) {
                Settings settings = Settings.settingsBuilder().put("cluster.name", Config.getClusterName()).build();
                TransportClient transportClient = TransportClient.builder().settings(settings).build();
                String ip = Config.getHostStr();
                try {
                    transportClient.addTransportAddress(new InetSocketTransportAddress(
                            InetAddress.getByName(ip), Config.getDefaultEsPort()));
                } catch (UnknownHostException exception) {
                    LOGGER.error("UnknowHostException, " + ip + " can't be transformed to host.", exception);
                }
                client = transportClient;
            } else {
                LOGGER.warn("es clientModel doesn't exist !");
            }
            LOGGER.debug("es client establish !");
        }
    }

    /**
     * get an instance of ConnectEs.
     * @return an instance.
     */
    public static ConnectEs instance() {
        if (instance == null) {
            instance = new ConnectEs();
        }
        return instance;
    }

    public Client getClient() {
        return this.client;
    }
}

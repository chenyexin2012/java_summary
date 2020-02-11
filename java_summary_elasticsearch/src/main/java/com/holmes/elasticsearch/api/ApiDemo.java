package com.holmes.elasticsearch.api;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.*;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ApiDemo {

    private static final Logger log = LoggerFactory.getLogger(ApiDemo.class);

    private static final String HOST = "127.0.0.1";

    private static final int PORT = 9200;

    private RestHighLevelClient client = null;

    @Before
    public void init() {
        RestClientBuilder builder = RestClient.builder(new HttpHost(HOST, PORT, "http"));
        client = new RestHighLevelClient(builder);
    }

    @Test
    public void test() {
        log.info("client: {}", client);
    }

    /**
     * 新增索引
     */
    @Test
    public void createIndex() throws InterruptedException {

        // 1. 创建添加索引请求，索引名: book
        CreateIndexRequest request = new CreateIndexRequest("book");

        // 2. settings
        request.settings(Settings.builder()
                .put("index.number_of_shards", 3)   // 分片数
                .put("index.number_of_replicas", 2) // 副本数
//                .put("analysis.analyzer.default.tokenizer", "ik_smart") // 默认分词器
        );

        // 3. 设置mapping
        Map<String, Object> mapping = new HashMap<>();

        Map<String, Object> name = new HashMap<>();
        name.put("type", "text");
        Map<String, Object> price = new HashMap<>();
        price.put("type", "float");
        Map<String, Object> count = new HashMap<>();
        count.put("type", "integer");

        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);
        properties.put("price", price);
        properties.put("count", count);

        mapping.put("properties", properties);

        request.mapping(mapping);
        // 4. 设置索引别名
        request.alias(new Alias("book_alias"));

//        try {
//            // 5. 发送同步请求并获取响应
//            CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
//
//            // 6、处理响应
//            boolean acknowledged = response.isAcknowledged();
//            boolean shardsAcknowledged = response.isShardsAcknowledged();
//            log.info("acknowledged = " + acknowledged);
//            log.info("shardsAcknowledged = " + shardsAcknowledged);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // 5. 发送异步请求并监听
        Cancellable cancellable = client.indices().createAsync(request, RequestOptions.DEFAULT, new ActionListener<CreateIndexResponse>() {
            @Override
            public void onResponse(CreateIndexResponse createIndexResponse) {

                // 6、处理响应
                boolean acknowledged = createIndexResponse.isAcknowledged();
                boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();
                log.info("acknowledged = " + acknowledged);
                log.info("shardsAcknowledged = " + shardsAcknowledged);
            }

            @Override
            public void onFailure(Exception e) {
                log.error("创建索引失败", e);
            }
        });
        TimeUnit.SECONDS.sleep(3);

    }

    /**
     * 删除索引
     */
    @Test
    public void deleteIndex() {

        DeleteIndexRequest request = new DeleteIndexRequest("book");

        
    }

    @Test
    public void destroy() throws IOException {
        client.close();
    }
}

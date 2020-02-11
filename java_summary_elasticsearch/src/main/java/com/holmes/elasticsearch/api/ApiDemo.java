package com.holmes.elasticsearch.api;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
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
    public void deleteIndex() throws InterruptedException {

        DeleteIndexRequest request = new DeleteIndexRequest("book");
//        try {
//            // 同步删除
//            AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
//            boolean acknowledged = response.isAcknowledged();
//            log.info("acknowledged = " + acknowledged);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // 异步删除
        client.indices().deleteAsync(request, RequestOptions.DEFAULT, new ActionListener<AcknowledgedResponse>() {
            @Override
            public void onResponse(AcknowledgedResponse acknowledgedResponse) {
                boolean acknowledged = acknowledgedResponse.isAcknowledged();
                log.info("acknowledged = " + acknowledged);
            }

            @Override
            public void onFailure(Exception e) {
                log.error("删除索引失败", e);
            }
        });
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * 新增文档
     */
    @Test
    public void createDoc() {

        IndexRequest request = new IndexRequest("book");

        JSONObject json = new JSONObject();
        json.put("name", "高性能MYSQL");
        json.put("isbn", "9787121198854");
        json.put("count", "100");
        json.put("price", 128.00);
        request.source(json);

        // 可选参数 start

        // 设置路由值
//        request.routing("routing");
        // 设置超时，等待主分片变得可用的时间
//        request.timeout(TimeValue.timeValueSeconds(1));
        // 刷新策略
//        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        // 设置版本
//        request.version(1);
        // 设置版本类型
//        request.versionType(VersionType.INTERNAL);
        // 操作类型
//        request.opType(DocWriteRequest.OpType.CREATE);

        // 可选参数 end

        try {
            // 同步执行
            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
            log.info("_index: {}", response.getIndex());
            log.info("_type: {}", response.getType());
            log.info("_id: {}", response.getId());
            log.info("_version: {}", response.getVersion());
            log.info("result: {}", response.getResult());
            log.info("_shards: {}", response.getShardInfo());
            log.info("_seq_no: {}", response.getSeqNo());
            log.info("_primary_term: {}", response.getPrimaryTerm());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 异步执行
//        client.indexAsync(request, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
//            @Override
//            public void onResponse(IndexResponse indexResponse) {
//
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//
//            }
//        });
    }

    /**
     * 删除文档
     */
    @Test
    public void deleteDoc() {

        DeleteRequest deleteRequest = new DeleteRequest("book", "1");
        try {
            // 同步删除
            DeleteResponse response = client.delete(deleteRequest, RequestOptions.DEFAULT);
            log.info("_index: {}", response.getIndex());
            log.info("_type: {}", response.getType());
            log.info("_id: {}", response.getId());
            log.info("_version: {}", response.getVersion());
            log.info("result: {}", response.getResult());
            log.info("_shards: {}", response.getShardInfo());
            log.info("_seq_no: {}", response.getSeqNo());
            log.info("_primary_term: {}", response.getPrimaryTerm());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据id查找
     */
    @Test
    public void searchById() {

        GetRequest request = new GetRequest("book", "1");
        try {
            // 同步获取
            GetResponse response = client.get(request, RequestOptions.DEFAULT);
            log.info("_index: {}", response.getIndex());
            log.info("_type: {}", response.getType());
            log.info("_id: {}", response.getId());
            log.info("_version: {}", response.getVersion());
            log.info("_seq_no: {}", response.getSeqNo());
            log.info("_primary_term: {}", response.getPrimaryTerm());
            log.info("_source: {}", response.getSource());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改指定文档
     */
    @Test
    public void updateDoc() {

        UpdateRequest request = new UpdateRequest("book", "1");
        JSONObject json = new JSONObject();
        json.put("name", "高性能MYSQL");
        json.put("isbn", "9787121198854");
        json.put("count", "100");
        json.put("price", 199.00);
        request.doc(json);

        try {
            // 同步执行
            UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
            log.info("_index: {}", response.getIndex());
            log.info("_type: {}", response.getType());
            log.info("_id: {}", response.getId());
            log.info("_version: {}", response.getVersion());
            log.info("result: {}", response.getResult());
            log.info("_shards: {}", response.getShardInfo());
            log.info("_seq_no: {}", response.getSeqNo());
            log.info("_primary_term: {}", response.getPrimaryTerm());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void destroy() throws IOException {
        client.close();
    }
}

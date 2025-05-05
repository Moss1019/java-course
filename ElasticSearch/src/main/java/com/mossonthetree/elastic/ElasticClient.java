package com.mossonthetree.elastic;

import co.elastic.clients.elasticsearch.ElasticsearchClient;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ElasticClient<T> implements AutoCloseable {
    private final Class<T> cls;

    private final ElasticsearchClient client;

    private boolean inError;

    private String error;

    public ElasticClient(String host, String username, String password, Class<T> cls) {
        this.cls = cls;
        client = ElasticsearchClient.of((builder) -> builder
                .host(host)
                .usernameAndPassword(username, password));
    }

    public boolean isInError() {
        return inError;
    }

    public String getError() {
        return error;
    }

    public T index(String indexName, T obj) {
        var id = UUID.randomUUID().toString();
        try {
            client.index(ob -> ob.index(indexName)
                    .id(id)
                    .document(obj));
        } catch (Exception ex) {
            inError = true;
            error = ex.getMessage();
        }
        return obj;
    }

    public List<T> search(String indexName, String field, String query) {
        var res = new ArrayList<T>();
        try {
            var searchRes = client
                    .search(builder -> builder.index(indexName)
                            .query(q -> q.match(m -> m.field(field)
                                    .query(query))), cls);
            for(var h: searchRes.hits().hits()) {
                res.add(h.source());
            }
        } catch (Exception ex) {
            inError = true;
            error = ex.getMessage();
        }
        return res;
    }

    @Override
    public void close() {
        try {
            client.close();
        } catch (Exception ignored) {

        }
    }
}

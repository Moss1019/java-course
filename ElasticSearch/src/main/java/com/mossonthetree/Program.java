package com.mossonthetree;

import com.mossonthetree.elastic.ElasticClient;

public class Program {
    public static void main(String[] args) {
        var client = new ElasticClient<>("http://localhost:9200", "elastic", "secret123!",
                Person.class);

        var p = new Person();
        p.name = "Freddy";
        p.surname = "Jones";
        var person = client.search("people", "name", "Freddy");
        for(var ps: person) {
            System.out.println(ps.name);
        }

        client.close();
    }
}

package com.dgs.Dgs_Client.example;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {DgsAutoConfiguration.class,ShowsDataFetcher.class})
class ShowsDataFetcherTest {

    @Autowired
    DgsQueryExecutor dgsQueryExecutor;

    @Test
    void show(){
        String query ="{\n" +
                "    shows (titleFilter:\"Ozark\"){\n" +
                "        title\n" +
                "        releaseYear\n" +
                "    }\n" +
                "}";

        List<String> titles = dgsQueryExecutor.executeAndExtractJsonPath(query,"data.shows.title");
        Assertions.assertThat(titles).containsExactly("Ozark");
    }
}
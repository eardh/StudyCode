package com.dahuang;

import com.dahuang.domain.Phone;
import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;

import java.util.Map;


@SpringBootTest
class RouterApplicationTests {


    @Test
    void contextLoads() {

    }

    @Bean
    ConnectionFactory connectionFactory() {
        return MySqlConnectionFactory.from(MySqlConnectionConfiguration.builder()
                .host("127.0.0.1")
                .port(3306)
                .username("root")
                .password("123456")
                .database("test")
                // 额外的其它非必选参数省略
                .build());
    }

    private DatabaseClient databaseClient = DatabaseClient.create(connectionFactory());

    @Test
    void doDDL() {

        Flux<Map<String, Object>> all = databaseClient.sql("select * from phone")
                .fetch()
                .all();

        all.map(p ->
            p.entrySet()
        ).subscribe(System.out::println);
    }

}

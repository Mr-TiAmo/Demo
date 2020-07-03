package com.li.node;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @program: Demo
 * @description:
 * @author: 栗翱
 * @create: 2020-07-03 11:20
 **/
@Configuration
@EnableTransactionManagement
public class Neo4jConfig {

    /**
     * 开启事务
     * @param sessionFactory
     * @return
     */
    @Bean
    public Neo4jTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new Neo4jTransactionManager(sessionFactory);
    }
}

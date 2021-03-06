//package com.li.mapper;
//
//import com.baomidou.mybatisplus.core.MybatisConfiguration;
//import com.baomidou.mybatisplus.core.injector.ISqlInjector;
//import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
//import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import com.li.comm.config.DynamicDataSource;
//import com.li.comm.constant.DataSourceType;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.type.JdbcType;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.Profile;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @program: parking_client
// * @description:
// * @author: 栗翱
// * @create: 2020-04-09 11:15
// **/
//
//@Configuration
//@MapperScan("com.li.mapper")
//public class MyBatiesPlusConfiguration {
//
//    /**
//     * 分页插件，自动识别数据库类型
//     */
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//        return paginationInterceptor;
//    }
//
//    /**
//     * 逻辑删除
//     *
//     * @return
//     */
//    @Bean
//    public ISqlInjector sqlInjector() {
//        return new LogicSqlInjector();
//    }
//    /**
//     * SQL执行效率插件
//     * 设置 dev test 环境开启
//     */
//    @Bean
//    @Profile({"dev", "qa"})
//    public PerformanceInterceptor performanceInterceptor() {
//        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
//        performanceInterceptor.setMaxTime(1000);
//        performanceInterceptor.setFormat(true);
//        return performanceInterceptor;
//    }
//
//    @Bean(name = "db1")
//    @ConfigurationProperties(prefix = "spring.datasource.druid.db1")
//    public DataSource db1() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "db2")
//    @ConfigurationProperties(prefix = "spring.datasource.druid.db2")
//    public DataSource db2() {
//        return DataSourceBuilder.create().build();
//    }
//
//    /**
//     * 动态数据源配置
//     *
//     * @return
//     */
//    @Bean
//    @Primary
//    public DataSource multipleDataSource(@Qualifier("db1") DataSource db1, @Qualifier("db2") DataSource db2) {
//        DynamicDataSource dynamicDataSource = new DynamicDataSource();
//        Map<Object, Object> targetDataSources = new HashMap<>();
//        targetDataSources.put(DataSourceType.db1.getValue(), db1);
//        targetDataSources.put(DataSourceType.db2.getValue(), db2);
//        //添加数据源
//        dynamicDataSource.setTargetDataSources(targetDataSources);
//        //设置默认数据源db1
//        dynamicDataSource.setDefaultTargetDataSource(db1);
//        return dynamicDataSource;
//    }
//
//    /**
//     * 返回MybatisSqlSessionFactory
//     *
//     * @return
//     * @throws Exception
//     */
//    @Bean("sqlSessionFactory")
//    public MybatisSqlSessionFactoryBean sqlSessionFactory() throws Exception {
//        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
//        sqlSessionFactory.setDataSource(multipleDataSource(db1(), db2()));
//
//        /**application.yml文件中已经配置，无需再配置
//         sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*Mapper.xml"));
//         */
//
//        MybatisConfiguration configuration = new MybatisConfiguration();
//        configuration.setJdbcTypeForNull(JdbcType.NULL);
//        configuration.setMapUnderscoreToCamelCase(true);
//        configuration.setCacheEnabled(false);
//        sqlSessionFactory.setConfiguration(configuration);
//        sqlSessionFactory.setPlugins(new Interceptor[]{
//                paginationInterceptor()
//        });
//        return sqlSessionFactory;
//    }
//
////    /**
////     * 分页插件
////     *
////     * @return PaginationInterceptor
////     */
////    @Bean
////    public PaginationInterceptor paginationInterceptor() {
////        return new PaginationInterceptor();
////    }
////
////
////    /**
////     * 逻辑删除
////     *
////     * @return
////     */
////    @Bean
////    public ISqlInjector sqlInjector() {
////        return new LogicSqlInjector();
////    }
//
//    /**
//     * 乐观锁插件
//     * @return
//     */
//    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
//        return new OptimisticLockerInterceptor();
//    }
//
//
//}

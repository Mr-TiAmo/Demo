//package com.li.config;
//
//import com.baomidou.mybatisplus.core.injector.ISqlInjector;
//import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
//import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author stan
// * @date 2019/6/3
// * @Desc 加载配置文件
// */
//@Configuration
//@MapperScan("com.li.dao")
//public class MybatisPlusConfigurer {
//	/**
//	 * 分页插件
//	 *
//	 * @return PaginationInterceptor
//	 */
//	@Bean
//	public PaginationInterceptor paginationInterceptor() {
//		return new PaginationInterceptor();
//	}
//
//	/**
//	 * 逻辑删除
//	 *
//	 * @return
//	 */
//	@Bean
//	public ISqlInjector sqlInjector() {
//		return new LogicSqlInjector();
//	}
//}

package com.example.demo;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.example.demo.statistic.mapper"}, sqlSessionFactoryRef = "statisticSqlSessionFactory")
public class StatisticMPConfig {
    @Bean("statisticdb")
    @Qualifier("statisticdb")
    @ConfigurationProperties("statisticdb.datasource")
    public DataSource statisticdb() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "statisticSqlSessionFactory")
    public SqlSessionFactory statisticSqlSessionFactory(@Qualifier("statisticdb") DataSource datasource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(datasource);
        
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);

        sessionFactory.setConfiguration(configuration);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/example/demo/statistic/mapper/*.xml"));
        sessionFactory.setTypeAliasesPackage("com.example.demo.statistic.entity");
        return sessionFactory.getObject();
    }

    @Bean("statisticTransactionManager")
    public DataSourceTransactionManager statisticTransactionManager(@Qualifier("statisticdb") DataSource datasource) {
        return new DataSourceTransactionManager(datasource);
    }
}

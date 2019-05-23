package com.example.demo;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.example.demo.gk.CoreKeyGen;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.example.demo.core.mapper"}, sqlSessionFactoryRef = "coreSqlSessionFactory")
public class CoreMPConfig {
    @Bean("coredb")
    @Primary
    @Qualifier("coredb")
    @ConfigurationProperties("coredb.datasource")
    public DataSource getDateSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Autowired
    private CoreKeyGen coreKeyGen;

    @Bean(name = "coreSqlSessionFactory")
    @Primary
    public SqlSessionFactory coreSqlSessionFactory(@Qualifier("coredb") DataSource datasource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(datasource);

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.addKeyGenerator("coreKeyGen", coreKeyGen);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
//        configuration.setUseGeneratedKeys(true);

        sessionFactory.setConfiguration(configuration);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/example/demo/core/mapper/*.xml"));
        sessionFactory.setTypeAliasesPackage("com.example.demo.core.entity");
        return sessionFactory.getObject();
    }

    @Bean("coreTransactionManager")
    @Primary
    public DataSourceTransactionManager coreTransactionManager(@Qualifier("coredb") DataSource datasource) {
        return new DataSourceTransactionManager(datasource);
    }

}

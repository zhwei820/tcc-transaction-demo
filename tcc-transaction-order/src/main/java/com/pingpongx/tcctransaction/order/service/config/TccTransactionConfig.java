package com.pingpongx.tcctransaction.order.service.config;

import com.alibaba.druid.pool.DruidDataSource;
import javax.sql.DataSource;
import org.mengyun.tcctransaction.TransactionRepository;
import org.mengyun.tcctransaction.serializer.KryoTransactionSerializer;
import org.mengyun.tcctransaction.serializer.ObjectSerializer;
import org.mengyun.tcctransaction.spring.repository.SpringJdbcTransactionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author liuwei
 * @date 2018/5/8
 * @desc tcc事务控制配置
 */
@Configuration
@ImportResource(locations = "classpath:tcc-transaction.xml")
public class TccTransactionConfig {

    @Value("${tcc.db.url}")
    private String tccDbUrl;

    @Value("${tcc.db.username}")
    private String tccDbUsername;

    @Value("${tcc.db.password}")
    private String tccDbPassword;

    /*@Bean
    public DefaultRecoverConfig recoverConfig(){
        DefaultRecoverConfig defaultRecoverConfig = new DefaultRecoverConfig();
        defaultRecoverConfig.setMaxRetryCount(3);
        defaultRecoverConfig.setRecoverDuration(60);
        defaultRecoverConfig.setCronExpression("0 *//*1 * * * ?");
        return defaultRecoverConfig;
    }*/

    public DataSource tccDataSource(){
        return DataSourceBuilder.create().url("jdbc:mysql://"+tccDbUrl+"?useUnicode=true&characterEncoding=utf-8").username(tccDbUsername)
            .password(tccDbPassword).type(DruidDataSource.class).driverClassName("com.mysql.jdbc.Driver").build();
    }

    @Bean
    public ObjectSerializer<?> objectSerializer(){
        return new KryoTransactionSerializer();
    }

    @Bean
    public TransactionRepository transactionRepository(ObjectSerializer<?> serializer){
        SpringJdbcTransactionRepository repository = new SpringJdbcTransactionRepository();
        repository.setDataSource(tccDataSource());
        repository.setDomain("ORDER");
        repository.setTbSuffix("_ORDER");
        repository.setSerializer(serializer);
        return repository;
    }

}

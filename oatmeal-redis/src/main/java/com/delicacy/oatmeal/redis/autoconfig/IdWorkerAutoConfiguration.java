package com.delicacy.oatmeal.redis.autoconfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.delicacy.oatmeal.redis.cache.IdWorker;
import com.delicacy.oatmeal.redis.utils.IdWorkerUtil;

@Configuration
@ConditionalOnProperty(prefix = "idworker", name = {"workerId"})
@EnableConfigurationProperties(IdWorkerProperties.class)
public class IdWorkerAutoConfiguration {
    @Autowired
    private IdWorkerProperties idWorkerProperties;

    @Bean
    IdWorker idWorker() {
        IdWorker idWorker = new IdWorker(idWorkerProperties.getWorkerId());
        IdWorkerUtil.setIdWorker(idWorker);
        return idWorker;
    }
}

@ConfigurationProperties(prefix = "idworker")
@Getter
@Setter
class IdWorkerProperties {
    private int workerId;
}
package com.delicacy.oatmeal.redis.autoconfig;

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

    /**
     * 两种使用方式 1、spring上下文中有一个bean idWorker 2、这个实例已经被设置在工具类中 直接使用工具类
     * <p>
     * Title: idWorker<br>
     * Description: idWorker<br>
     * CreateDate: 2017年9月6日 下午4:40:38<br>
     *
     * @return
     * @category idWorker
     * @author woody
     */
    @Bean
    IdWorker idWorker() {
        IdWorker idWorker = new IdWorker(idWorkerProperties.getWorkerId());
        IdWorkerUtil.setIdWorker(idWorker);
        return idWorker;
    }
}

@ConfigurationProperties(prefix = "idworker")
class IdWorkerProperties {

    private int workerId;

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workId) {
        this.workerId = workId;
    }
}
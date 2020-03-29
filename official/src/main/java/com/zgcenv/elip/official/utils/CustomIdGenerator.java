package com.zgcenv.elip.official.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName CustomIdGenerator
 * @Description 自定义主键id
 * @Author Mr.Jangni
 * @Date 2020/3/6 14:20
 * @Version 1.0
 **/
@Slf4j
@Component
public class CustomIdGenerator implements IdentifierGenerator {

    @Value("snowFlake.workerId")
    private static long workerId;

    @Value("snowFlake.dataCenterId")
    private static long dataCenterId;

    private Snowflake snowflake = new Snowflake(workerId, dataCenterId);

    /**
     * 获取一个批次号，形如 2019071015301361000101237
     * 数据库使用 char(25) 存储
     *
     * @param tenantId 租户ID，5 位
     * @param module   业务模块ID，2 位
     * @return 返回批次号
     */
    public synchronized String batchId(int tenantId, int module) {
        String prefix = DateTime.now().toString(DatePattern.PURE_DATETIME_MS_PATTERN);
        return prefix + tenantId + module + RandomUtil.randomNumbers(3);
    }

    /**
     * 生成的是不带-的字符串，类似于：b17f24ff026d40949c85a24f4f375d42
     *
     * @return
     */
    public String simpleUUID() {
        return IdUtil.simpleUUID();
    }

    /**
     * 生成的UUID是带-的字符串，类似于：a5c8a5e8-df2b-4706-bea4-08d0939410e3
     *
     * @return
     */
    public String randomUUID() {
        return IdUtil.randomUUID();
    }

    public synchronized long snowflakeId() {
        return snowflake.nextId();
    }

    @Override
    public Long nextId(Object entity) {

        return snowflakeId();
    }
}
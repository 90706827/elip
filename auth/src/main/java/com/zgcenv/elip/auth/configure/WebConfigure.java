package com.zgcenv.elip.auth.configure;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DatasourceConfigure
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/15 20:17
 * @Version 1.0
 **/
@Configuration
public class WebConfigure {
    /**
     * @Author Mr.Jangni
     * @Description mybatis-plus分页插件
     **/
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();
        sqlParserList.add(new BlockAttackSqlParser());
        paginationInterceptor.setSqlParserList(sqlParserList);
        paginationInterceptor.setDialectType("mysql");
        return paginationInterceptor;
    }
}

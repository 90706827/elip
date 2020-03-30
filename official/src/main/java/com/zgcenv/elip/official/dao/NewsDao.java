package com.zgcenv.elip.official.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zgcenv.elip.official.entity.News;
import com.zgcenv.elip.official.module.NewsColumnsVo;
import com.zgcenv.elip.official.query.NewQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Mr.Jangni
 * @since 2020-03-24
 */
@Repository
public interface NewsDao extends BaseMapper<News> {

    IPage<NewsColumnsVo> listPageVo(@Param("pg") Page<NewsColumnsVo> page, @Param("query") NewQuery query);
}

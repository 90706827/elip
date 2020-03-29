package com.zgcenv.elip.official.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zgcenv.elip.official.entity.News;
import com.zgcenv.elip.official.query.NewSave;
import com.zgcenv.elip.official.query.NewUpdate;
import com.zgcenv.elip.official.query.NewQuery;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mr.Jangni
 * @since 2020-03-24
 */
public interface NewsService extends IService<News> {
    Map<String, Object> list(NewQuery query);

    void saveNews(NewSave news);

    void updateNews(NewUpdate news);

    void delNews(Long id);

    News selectNews(Long id);
}

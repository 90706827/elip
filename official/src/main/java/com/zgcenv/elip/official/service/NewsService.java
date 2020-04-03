package com.zgcenv.elip.official.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zgcenv.elip.official.entity.News;
import com.zgcenv.elip.official.query.NewQuery;
import com.zgcenv.elip.official.query.NewSave;
import com.zgcenv.elip.official.query.NewUpdate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
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

    void delNewsByColumnId(Long columnId);

    List<String> upload(List<MultipartFile> files, Long newId);
}

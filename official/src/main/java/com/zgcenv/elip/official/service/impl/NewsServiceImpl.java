package com.zgcenv.elip.official.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgcenv.elip.official.dao.NewsDao;
import com.zgcenv.elip.official.entity.News;
import com.zgcenv.elip.official.module.NewsColumnsVo;
import com.zgcenv.elip.official.query.NewQuery;
import com.zgcenv.elip.official.query.NewSave;
import com.zgcenv.elip.official.query.NewUpdate;
import com.zgcenv.elip.official.service.NewsService;
import org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Mr.Jangni
 * @since 2020-03-24
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsDao, News> implements NewsService {

    @Resource
    private NewsDao newsDao;

    @Override
    public Map<String, Object> list(NewQuery query) {
        Map<String, Object> map = new HashMap<>(50);
        Page<NewsColumnsVo> page = new Page<>(query.getPage(), query.getSize());
//        QueryWrapper<NewsCloumnsVo> wrapper = new QueryWrapper<>();
//        wrapper.eq("column_Id", query.getColumnId());
//        wrapper.like("title", query.getTitle());
//        IPage<News> iPage = baseMapper.selectPage(page, wrapper);
        IPage<NewsColumnsVo> iPage = baseMapper.listPageVo(page, query);
        map.put("list", iPage.getRecords());
        map.put("page", query.getPage());
        map.put("size", query.getSize());
        map.put("total", iPage.getTotal());
        return map;
    }

    @Override
    public void saveNews(NewSave news) {
        News news1 = new News();
        news1.setColumnId(news.getColumnId());
        news1.setTitle(news.getTitle());
        news1.setHeadPic(news.getHeadPic());
        news1.setDescription(news.getDescription());
        news1.setContext(news.getContext());
        news1.setLookSum(news.getLookSum());
        news1.setFollowSum(news.getFollowSum());
        news1.setInsertTime(new Date());
        news1.setUpdateTime(new Date());
        baseMapper.insert(news1);
    }

    @Override
    public void updateNews(NewUpdate news) {
        News news1 = new News();
        news1.setId(news.getId());
        news1.setColumnId(news.getColumnId());
        news1.setTitle(news.getTitle());
        news1.setHeadPic(news.getHeadPic());
        news1.setDescription(news.getDescription());
        news1.setContext(news.getContext());
        news1.setLookSum(news.getLookSum());
        news1.setFollowSum(news.getFollowSum());
        news1.setInsertTime(new Date());
        news1.setUpdateTime(new Date());
        baseMapper.updateById(news1);
    }

    @Override
    public void delNews(Long id) {
        baseMapper.deleteById(id);
    }

    @Override
    public News selectNews(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void delNewsByColumnId(Long columnId) {
        baseMapper.delete(new QueryWrapper<News>().eq("column_id",columnId));
    }
}

package com.zgcenv.elip.common.service.impl;

import com.zgcenv.elip.common.entity.News;
import com.zgcenv.elip.common.dao.NewsDao;
import com.zgcenv.elip.common.service.NewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr.Jangni
 * @since 2020-03-31
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsDao, News> implements NewsService {

}

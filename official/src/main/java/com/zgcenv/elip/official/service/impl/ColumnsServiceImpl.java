package com.zgcenv.elip.official.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgcenv.elip.official.dao.ColumnsDao;
import com.zgcenv.elip.official.entity.Columns;
import com.zgcenv.elip.official.query.ColumnSave;
import com.zgcenv.elip.official.query.ColumnQuery;
import com.zgcenv.elip.official.query.ColumnUpdate;
import com.zgcenv.elip.official.service.ColumnsService;
import org.springframework.stereotype.Service;

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
public class ColumnsServiceImpl extends ServiceImpl<ColumnsDao, Columns> implements ColumnsService {

    @Override
    public Map<String, Object> list(ColumnQuery query) {
        Map<String, Object> map = new HashMap<>(50);
        IPage<Columns> page = new Page<>(query.getPage(), query.getSize());
        QueryWrapper<Columns> wrapper = new QueryWrapper<>();
        wrapper.like("title", query.getTitle());
        IPage<Columns> iPage = baseMapper.selectPage(page, wrapper);
        map.put("list", iPage.getRecords());
        map.put("page", query.getPage());
        map.put("size", query.getSize());
        map.put("total", iPage.getTotal());
        return map;
    }

    @Override
    public void saveColumns(ColumnSave column) {
        Columns columns = new Columns();
        columns.setTitle(column.getTitle());
        columns.setDescription(column.getDescription());
        columns.setInsertTime(new Date());
        columns.setUpdateTime(new Date());
        baseMapper.insert(columns);
    }

    @Override
    public void updateColumns(ColumnUpdate column) {
        Columns columns = new Columns();
        columns.setId(column.getId());
        columns.setTitle(column.getTitle());
        columns.setDescription(column.getDescription());
        columns.setUpdateTime(new Date());
        baseMapper.updateById(columns);
    }

    @Override
    public void delColumns(Long id) {
        baseMapper.deleteById(id);
    }

    @Override
    public Columns selectColumns(Long id) {

        return baseMapper.selectById(id);
    }
}

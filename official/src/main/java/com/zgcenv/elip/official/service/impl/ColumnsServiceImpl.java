package com.zgcenv.elip.official.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgcenv.elip.official.dao.ColumnsDao;
import com.zgcenv.elip.official.dao.NewsDao;
import com.zgcenv.elip.official.entity.Columns;
import com.zgcenv.elip.official.entity.News;
import com.zgcenv.elip.official.handler.BusinessException;
import com.zgcenv.elip.official.module.SelectedColumnVo;
import com.zgcenv.elip.official.query.ColumnQuery;
import com.zgcenv.elip.official.query.ColumnSave;
import com.zgcenv.elip.official.query.ColumnUpdate;
import com.zgcenv.elip.official.service.ColumnsService;
import com.zgcenv.elip.official.utils.RespCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

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
    private static final Logger logger = LoggerFactory.getLogger(ColumnsServiceImpl.class);
    @Autowired
    private NewsDao newsDao;

    @Override
    public Map<String, Object> list(ColumnQuery query) {
        Map<String, Object> map = new HashMap<>(50);
        IPage<Columns> page = new Page<>(query.getPage(), query.getSize());
        QueryWrapper<Columns> wrapper = new QueryWrapper<Columns>();
        wrapper.eq("parent_id", query.getParentId());
        wrapper.like(!StringUtils.isEmpty(query.getTitle()), "title", query.getTitle());
        wrapper.orderByDesc("sorting");
        IPage<Columns> iPage = baseMapper.selectPage(page, wrapper);
        map.put("list", iPage.getRecords());
        map.put("page", query.getPage());
        map.put("size", query.getSize());
        map.put("total", iPage.getTotal());
        return map;
    }

    @Override
    public void saveColumns(ColumnSave column) throws BusinessException {
        if (!StringUtils.isEmpty(column.getParentId())) {
            int count = baseMapper.selectCount(new QueryWrapper<Columns>().eq("id", column.getParentId()));
            if (count == 0) {
                throw new BusinessException(RespCode.NOT_FIND_COLUMNS);
            }
        }
        Columns columns = new Columns();
        columns.setParentId(StringUtils.isEmpty(column.getParentId()) ? 0 : column.getParentId());
        columns.setTitle(column.getTitle());
        columns.setDescription(column.getDescription());
        columns.setSorting(column.getSorting());
        columns.setInsertTime(new Date());
        columns.setUpdateTime(new Date());
        baseMapper.insert(columns);
    }

    @Override
    public void updateColumns(ColumnUpdate column) throws BusinessException {
        if (!StringUtils.isEmpty(column.getParentId())) {
            int count = baseMapper.selectCount(new QueryWrapper<Columns>().eq("id", column.getParentId()));
            if (count == 0) {
                throw new BusinessException(RespCode.NOT_FIND_COLUMNS);
            }
        }
        Columns columns = new Columns();
        columns.setId(column.getId());
        columns.setParentId(StringUtils.isEmpty(column.getParentId()) ? 0 : column.getParentId());
        columns.setTitle(column.getTitle());
        columns.setDescription(column.getDescription());
        columns.setSorting(column.getSorting());
        columns.setUpdateTime(new Date());
        baseMapper.updateById(columns);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void delColumns(Long id) throws BusinessException {
        if (selectColumnCount(id)) {
            throw new BusinessException(RespCode.FIND_COLUMNS);
        }
        int count = newsDao.selectCount(new QueryWrapper<News>().eq("column_id", id));
        if (count != 0) {
            throw new BusinessException(RespCode.FIND_NEWS);
        }
        baseMapper.deleteById(id);
    }

    /**
     * @Author Mr.Jangni
     * @Description 查找菜单是否存在子菜单
     * @Date 2020/4/3 8:33
     * @Param [columnId]
     * @Return boolean
     **/
    private boolean selectColumnCount(Long columnId) {
        List<Columns> list = baseMapper.selectList(new QueryWrapper<Columns>().eq("parent_id", columnId));
        if (list != null && list.size() > 0) {
            return true;
        }
        for (Columns column : list) {
            return selectColumnCount(column.getId());
        }
        return false;
    }

    @Override
    public Columns selectColumns(Long id) {

        return baseMapper.selectById(id);
    }

    @Override
    public List<SelectedColumnVo> selectedColumns() {
        return selectedColumn(0L);

    }

    private  List<SelectedColumnVo> selectedColumn(Long columnId){
        List<Columns> list = baseMapper.selectList(new QueryWrapper<Columns>().eq("parent_id",columnId).orderByDesc("sorting"));
        logger.info("parent_id:{},list.siz:{}",columnId,list.size());
        List<SelectedColumnVo> selectedList=new ArrayList<>();
        for(Columns columns:list){
            SelectedColumnVo selectedColumnVo = new SelectedColumnVo();
            selectedColumnVo.setId(columns.getId());
            selectedColumnVo.setTitle(columns.getTitle());
            selectedColumnVo.setList( selectedColumn(columns.getId()));
            selectedList.add(selectedColumnVo);
        }
        return selectedList;
    }
}

package com.zgcenv.elip.official.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zgcenv.elip.official.entity.Columns;
import com.zgcenv.elip.official.query.ColumnSave;
import com.zgcenv.elip.official.query.ColumnQuery;
import com.zgcenv.elip.official.query.ColumnUpdate;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Mr.Jangni
 * @since 2020-03-24
 */
public interface ColumnsService extends IService<Columns> {

    Map<String, Object> list(ColumnQuery query);

    void saveColumns(ColumnSave column);

    void updateColumns(ColumnUpdate column);

    void delColumns(Long id);

    Columns selectColumns(Long id);

}

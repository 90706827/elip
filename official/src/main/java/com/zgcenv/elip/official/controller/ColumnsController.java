package com.zgcenv.elip.official.controller;

import com.zgcenv.elip.official.module.ResultJson;
import com.zgcenv.elip.official.query.ColumnQuery;
import com.zgcenv.elip.official.query.ColumnSave;
import com.zgcenv.elip.official.query.ColumnUpdate;
import com.zgcenv.elip.official.service.ColumnsService;
import com.zgcenv.elip.official.service.NewsService;
import com.zgcenv.elip.official.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;

/**
 * @ClassName ColumnsController
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/26 8:41
 * @Version 1.0
 **/
@Api(tags = "3、栏目管理")
@RestController
@RequestMapping(value = "/columns")
public class ColumnsController {
    private static final Logger logger = LoggerFactory.getLogger(ColumnsController.class);
    @Autowired
    private RedisService redisService;
    @Autowired
    private ColumnsService columnsService;
    @Autowired
    private NewsService newsService;

    @ApiOperation(value = "查询栏目列表")
    @ApiImplicitParam(paramType = "header", name = "token", value = "Token", required = false, dataType = "string")
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity<?> list(@RequestBody ColumnQuery query) {
        logger.info("page={},size={},title={}", query.getPage(), query.getSize(), query.getTitle());
        return ResultJson.success(columnsService.list(query));
    }

    @ApiOperation(value = "查询栏目")
    @ApiImplicitParam(paramType = "header", name = "token", value = "Token", required = false, dataType = "string")
    @ResponseBody
    @RequestMapping(value = "/selectColumns", method = RequestMethod.POST)
    public ResponseEntity<?> selectColumns(HttpServletRequest request, @RequestBody @Pattern(regexp = "[0-9]{19}", message = "ID不正确！") Long id) {
        return ResultJson.success(columnsService.selectColumns(id));
    }

    @ApiOperation(value = "新增栏目")
    @ApiImplicitParam(paramType = "header", name = "token", value = "Token", required = false, dataType = "string")
    @ResponseBody
    @RequestMapping(value = "/saveColumns", method = RequestMethod.POST)
    public ResponseEntity<?> saveColumns(HttpServletRequest request, @RequestBody @Validated ColumnSave column) {
        columnsService.saveColumns(column);
        return ResultJson.success();
    }

    @ApiOperation(value = "更新栏目")
    @ApiImplicitParam(paramType = "header", name = "token", value = "Token", required = false, dataType = "string")
    @ResponseBody
    @RequestMapping(value = "/updateColumns", method = RequestMethod.POST)
    public ResponseEntity<?> updateColumns(HttpServletRequest request, @RequestBody @Validated ColumnUpdate column) {
        columnsService.updateColumns(column);
        return ResultJson.success();
    }

    @ApiOperation(value = "删除栏目")
    @ApiImplicitParam(paramType = "header", name = "token", value = "Token", required = false, dataType = "string")
    @ResponseBody
    @RequestMapping(value = "/delColumns", method = RequestMethod.POST)
    public ResponseEntity<?> delColumns(HttpServletRequest request, @RequestBody @Pattern(regexp = "[0-9]{19}", message = "ID不正确！") Long id) {
        columnsService.delColumns(id);
        return ResultJson.success();
    }
}

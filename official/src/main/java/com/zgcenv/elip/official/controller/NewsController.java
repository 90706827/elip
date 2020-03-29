package com.zgcenv.elip.official.controller;

import com.zgcenv.elip.official.module.ResultJson;
import com.zgcenv.elip.official.query.NewQuery;
import com.zgcenv.elip.official.query.NewSave;
import com.zgcenv.elip.official.query.NewUpdate;
import com.zgcenv.elip.official.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;

/**
 * @ClassName NewsController
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/27 12:54
 * @Version 1.0
 **/
@Api(tags = "4、新闻")
@RestController
@RequestMapping(value = "/news")
public class NewsController {

    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsService newsService;

    @ApiOperation(value = "查询新闻列表")
    @ApiImplicitParam(paramType = "header", name = "token", value = "Token", required = false, dataType = "string")
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity<?> list(@RequestBody NewQuery query) {
        logger.info("page={},size={},title={}", query.getPage(), query.getSize(), query.getTitle());
        return ResultJson.success(newsService.list(query));
    }

    @ApiOperation(value = "查询新闻")
    @ApiImplicitParam(paramType = "header", name = "token", value = "Token", required = false, dataType = "string")
    @ResponseBody
    @RequestMapping(value = "/selectNews", method = RequestMethod.POST)
    public ResponseEntity<?> selectNews(@RequestBody @Pattern(regexp = "[0-9]{19}", message = "ID不正确！") Long id) {
        return ResultJson.success(newsService.selectNews(id));
    }

    @ApiOperation(value = "新增新闻")
    @ApiImplicitParam(paramType = "header", name = "token", value = "Token", required = false, dataType = "string")
    @ResponseBody
    @RequestMapping(value = "/saveNews", method = RequestMethod.POST)
    public ResponseEntity<?> saveNews(@RequestBody @Validated NewSave news) {
        newsService.saveNews(news);
        return ResultJson.success();
    }

    @ApiOperation(value = "更新新闻")
    @ApiImplicitParam(paramType = "header", name = "token", value = "Token", required = false, dataType = "string")
    @ResponseBody
    @RequestMapping(value = "/updateNews", method = RequestMethod.POST)
    public ResponseEntity<?> updateNews(@RequestBody @Validated NewUpdate news) {
        newsService.updateNews(news);
        return ResultJson.success();
    }

    @ApiOperation(value = "删除新闻")
    @ApiImplicitParam(paramType = "header", name = "token", value = "Token", required = false, dataType = "string")
    @ResponseBody
    @RequestMapping(value = "/delNews", method = RequestMethod.POST)
    public ResponseEntity<?> delNews(@RequestBody @Pattern(regexp = "[0-9]{19}", message = "ID不正确！") Long id) {
        newsService.delNews(id);
        return ResultJson.success();
    }

}

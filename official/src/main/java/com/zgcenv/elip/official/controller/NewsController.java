package com.zgcenv.elip.official.controller;

import com.zgcenv.elip.official.entity.News;
import com.zgcenv.elip.official.handler.BusinessException;
import com.zgcenv.elip.official.module.ResultJson;
import com.zgcenv.elip.official.query.NewQuery;
import com.zgcenv.elip.official.query.NewSave;
import com.zgcenv.elip.official.query.NewUpdate;
import com.zgcenv.elip.official.service.NewsService;
import com.zgcenv.elip.official.utils.RespCode;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @ClassName NewsController
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/27 12:54
 * @Version 1.0
 **/
@Api(tags = "4、新闻管理")
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

    @ApiOperation(value = "图片上传-PostMan测试")
    @ApiResponses({
            @ApiResponse(code = 704, message = "验证码失效！"),
            @ApiResponse(code = 705, message = "新闻不存在！"),
            @ApiResponse(code = 706, message = "上传图片失败！"),
    })
    @ApiImplicitParam(paramType = "header", name = "token", value = "Token", required = false, dataType = "string")
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = "multipart/form-data", headers = "content-type=multipart/form-data")
    public ResponseEntity<?> upload(@RequestParam(value = "pictures", required = true) List<MultipartFile> pictures,
                                    @RequestParam("newId") Long newId) throws BusinessException {

        if (pictures == null || pictures.size() == 0) {
            return ResultJson.failed(RespCode.NOT_IMAGE);
        }
        News news = newsService.getById(newId);
        if (StringUtils.isEmpty(news)) {
            return ResultJson.failed(RespCode.NOT_FIND_NEWS);
        }
        List<String> images = newsService.upload(pictures, newId);

        if (images.size() != pictures.size()) {
            throw new BusinessException(RespCode.UPLOAD_IMAGE_FAILED);
        }
        return ResultJson.success(images);
    }
}

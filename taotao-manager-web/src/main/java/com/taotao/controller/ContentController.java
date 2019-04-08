package com.taotao.controller;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContentController {
    @Autowired
    private ContentService contentService;
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("{REST_CONTENT_SYNC_URL}")
    private String REST_CONTENT_SYNC_URL;

    @RequestMapping("/content/save")
    @ResponseBody
    public TaotaoResult insertContent(TbContent content) {
        TaotaoResult result = contentService.insertContent(content);
        //调用删除redis缓存的服务
        HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
        return result;
    }

    @RequestMapping("/content/query/list")
    @ResponseBody
    public EasyUIDataGridResult getContentList(Long categoryId, Integer page, Integer rows) {
        return contentService.getContentList(categoryId, page, rows);
    }
}



package com.taotao.search.controller;

import com.taotao.common.pojo.ExceptionUtil;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @RequestMapping("/q")
    @ResponseBody
    public TaotaoResult search(@RequestParam(defaultValue = "")String keyword,
                               @RequestParam(defaultValue = "1")Integer page,
                               @RequestParam(defaultValue = "30")Integer rows)
    {
        try {
            //转换字符集
            keyword = new String(keyword.getBytes("iso8859-1"), "utf-8");
            SearchResult searchResult = (SearchResult) searchService.search(keyword, page, rows);
            return TaotaoResult.ok(searchResult);
        }catch (Exception e)
        {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
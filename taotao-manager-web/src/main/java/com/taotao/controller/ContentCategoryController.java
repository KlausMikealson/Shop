package com.taotao.controller;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(@RequestParam(value = "id", defaultValue = "0")Long parentId)
    {
        List<EasyUITreeNode> list = contentCategoryService.getContentCatList(parentId);
        System.out.println(list.size());
        return list;
    }

    @RequestMapping("/content/category/create")
    @ResponseBody
    public TaotaoResult createNodee(Long parentId, String name)
    {
        return contentCategoryService.insertCategory(parentId, name);
    }
}























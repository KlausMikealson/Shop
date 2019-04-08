package com.taotao.controller;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;

    @RequestMapping("/item/param/query/itemcatid/{cid}")
    @ResponseBody
    public TaotaoResult getItemCatByCid(@PathVariable Long cid) {
        return itemParamService.getItemParamByCid(cid);
    }

    @RequestMapping("/item/param/save/{cid}")
    @ResponseBody
    public TaotaoResult insertItemParam(@PathVariable Long cid, String paramData) {
        return itemParamService.insertItemParam(cid, paramData);
    }

    @RequestMapping("/item/param/list")
    @ResponseBody
    public EasyUIDataGridResult getItemParamList(Integer page, Integer rows) {
        return itemParamService.getItemParamList(page, rows);
    }
}



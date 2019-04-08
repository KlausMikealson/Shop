package com.taotao.rest.controller;

import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.service.ItemCatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    //第一种方法
    // @RequestMapping(value = "/itemcat/all",
    //         produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    // @ResponseBody
    // public String getItemCatList(String callback) {
    //     ItemCatResult result = itemCatService.getItemCatList();
    //     String json = JsonUtils.objectToJson(result);
    //     if (StringUtils.isBlank(callback)) {
    //         return json;
    //     }
    //     return callback + "(" + json + ")";
    // }

    // 第二种方法
    // 要求：springmvc版本是4.1以上
    @RequestMapping(value = "/itemcat/all")
    @ResponseBody
    public Object getItemCatList(String callback) {
        ItemCatResult result = itemCatService.getItemCatList();
        if (StringUtils.isBlank(callback)) {
            return result;
        }
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }
}

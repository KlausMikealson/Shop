package com.taotao.portal.controller;

import com.taotao.pojo.TbItem;
import com.taotao.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    public String showItemInfo(@PathVariable Long itemId, Model model) {
        TbItem item = itemService.getItemById(itemId);
        model.addAttribute("item", item);
        return "item";
    }

    @RequestMapping(value = "/item/desc/{itemId}",
            produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
    @ResponseBody
    public String showItemInfo(@PathVariable Long itemId) {
        return itemService.getItemDescById(itemId);
    }

    @RequestMapping(value = "/item/param/{itemId}",
            produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
    @ResponseBody
    public String showItemParam(@PathVariable Long itemId) {
        return itemService.getItemParamItemById(itemId);
    }
}

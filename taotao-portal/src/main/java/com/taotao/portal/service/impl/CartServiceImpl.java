package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;
import com.taotao.portal.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private ItemService itemService;
    @Value("${COOKIE_EXPIRE}")
    private Integer COOKIE_EXPIRE;

    @Override
    public TaotaoResult addCart(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
        List<CartItem> itemList = getCartItemList(request);
        boolean pan = false;
        for (CartItem cartItem:itemList)
        {
            if(cartItem.getId().longValue()==itemId.longValue())
            {
                cartItem.setNum(cartItem.getNum()+num);
                pan = true;
                break;
            }
        }
        if(!pan)
        {
            TbItem item = itemService.getItemById(itemId);
            CartItem cartItem = new CartItem();
            cartItem.setId(itemId);
            cartItem.setNum(num);
            cartItem.setPrice(item.getPrice());
            cartItem.setTitle(item.getTitle());
            if(!StringUtils.isEmpty(item.getImage()))
            {
                String image = item.getImage();
                String[] images = image.split(",");
                cartItem.setImage(images[0]);
            }
            itemList.add(cartItem);
        }
        CookieUtils.setCookie(request,response, "TT_CART", JsonUtils.objectToJson(itemList), COOKIE_EXPIRE, true);
        return TaotaoResult.ok();
    }

    @Override
    public List<CartItem> getCartItems(HttpServletRequest request) {
        return getCartItemList(request);
    }

    @Override
    public TaotaoResult update(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
        List<CartItem> itemList = getCartItemList(request);
        for (CartItem cartItem:itemList)
        {
            if(cartItem.getId().longValue()==itemId.longValue())
            {
                cartItem.setNum(num);
                break;
            }
        }
        CookieUtils.setCookie(request,response, "TT_CART", JsonUtils.objectToJson(itemList), COOKIE_EXPIRE, true);
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult delete(Long itemId, HttpServletRequest request, HttpServletResponse response) {
        List<CartItem> itemList = getCartItemList(request);
        for (CartItem cartItem:itemList)
        {
            if(cartItem.getId().longValue()==itemId.longValue())
            {
                itemList.remove(cartItem);
                break;
            }
        }
        CookieUtils.setCookie(request,response, "TT_CART", JsonUtils.objectToJson(itemList), COOKIE_EXPIRE, true);
        return TaotaoResult.ok();
    }

    private List<CartItem> getCartItemList(HttpServletRequest request)
    {
        String json = CookieUtils.getCookieValue(request, "TT_CART", true);
        if(json == null)
            return new ArrayList<CartItem>();
        return JsonUtils.jsonToList(json, CartItem.class);
    }
}

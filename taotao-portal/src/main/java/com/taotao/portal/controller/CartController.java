package com.taotao.portal.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping("/cart/add/{itemId}")
    public String addCart(@PathVariable Long itemId, @RequestParam("num") Integer num, HttpServletRequest request, HttpServletResponse response)
    {
        cartService.addCart(itemId, num, request, response);
        return "cartSuccess";
    }

    @RequestMapping("/cart/cart")
    public String showCartList(HttpServletRequest request, Model model)
    {
        List<CartItem> list = cartService.getCartItems(request);
        model.addAttribute("cartList", list);
        return "cart";
    }

    @RequestMapping(value = "/cart/update/num/{itemId}/{num}", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult updateCartList(@PathVariable Long itemId, @PathVariable Integer num, HttpServletRequest request, HttpServletResponse response)
    {
        return cartService.update(itemId, num, request, response);
    }

    @RequestMapping("/cart/delete/{itemId}")
    public String deteleCartItem(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response)
    {
        cartService.delete(itemId, request, response);
        return "redirect:/cart/cart.html";
    }
}

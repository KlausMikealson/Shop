package com.taotao.portal.service.impl;

import com.taotao.common.pojo.HttpClientUtil;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.OrderInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import utils.JsonUtils;

@Service
public class OrderServiceImpl implements OrderService {

    @Value("${ORDER_BASE_URL}")
    private String ORDER_BASE_URL;
    @Value("${ORDER_CREATE_URL}")
    private String ORDER_CREATE_URL;

    @Override
    public String createOrder(OrderInfo orderInfo) {
        String json = JsonUtils.objectToJson(orderInfo);
        String jsonResult = HttpClientUtil.doPostJson(ORDER_BASE_URL+ORDER_CREATE_URL, json);
        TaotaoResult taotaoResult = TaotaoResult.format(jsonResult);
        return taotaoResult.getData().toString();
    }
}

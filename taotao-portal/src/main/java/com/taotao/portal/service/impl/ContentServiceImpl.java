package com.taotao.portal.service.impl;

import com.taotao.common.pojo.HttpClientUtil;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.portal.pojo.AdNode;
import com.taotao.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import utils.JsonUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_CONTENT_URL}")
    private String REST_CONTENT_URL;
    @Value("${REST_CONTENT_AD1_CID}")
    private String REST_CONTENT_AD1_CID;

    @Override
    public String getAd1List() {
        //调用服务获得数据
        String json = HttpClientUtil.doGet(REST_BASE_URL+REST_CONTENT_URL+REST_CONTENT_AD1_CID);
        TaotaoResult taotaoResult = TaotaoResult.formatToList(json, TbContent.class);
        List<TbContent> contentList = (List<TbContent>) taotaoResult.getData();
        List<AdNode> resultList = new ArrayList<>();
        for(TbContent tbContent : contentList)
        {
            AdNode node = new AdNode();
            node.setHeight(240);
            node.setWidth(670);
            node.setSrc(tbContent.getPic());
            node.setHeigthB(240);
            node.setWidthB(550);
            node.setSrcB(tbContent.getPic2());
            node.setAlt(tbContent.getSubTitle());
            node.setHref(tbContent.getUrl());
            resultList.add(node);
        }
        String resultJson = JsonUtils.objectToJson(resultList);
        return resultJson;
    }
}

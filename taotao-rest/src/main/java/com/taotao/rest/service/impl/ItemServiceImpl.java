package com.taotao.rest.service.impl;

import java.util.List;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.rest.component.JedisClient;
import com.taotao.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_CONTENT_KEY}")
    private String REDIS_CONTENT_KEY;
    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;
    @Value("${ITEM_BASE_INFO_KEY}")
    private String ITEM_BASE_INFO_KEY;
    @Value("${ITEM_DESC_KEY}")
    private String ITEM_DESC_KEY;
    @Value("${ITEM_PARAM_KEY}")
    private String ITEM_PARAM_KEY;
    @Value("${ITEM_EXPIRE_SECOND}")
    private Integer ITEM_EXPIRE_SECOND;

    @Override
    public TbItem getItemById(Long itemId) {
        /*//查询缓存，如果有缓存，直接返回
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY+":"+itemId+":"+ITEM_BASE_INFO_KEY);
            //判断数据是否存在
            if(!StringUtils.isNullOrEmpty(json))
            {
                TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
                return item;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //根据商品ID查询商品基本信息
        TbItem item =  itemMapper.selectByPrimaryKey(itemId);
        //向redis中添加数据
        try {
            jedisClient.set(REDIS_ITEM_KEY+":"+itemId+":"+ITEM_BASE_INFO_KEY,
                    JsonUtils.objectToJson(item));
            //设置key的过期时间
            jedisClient.expire(REDIS_ITEM_KEY+":"+itemId+":"+ITEM_BASE_INFO_KEY, ITEM_EXPIRE_SECOND);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;*/


        //根据商品ID查询商品基本信息
        TbItem item = itemMapper.selectByPrimaryKey(itemId);
        return item;
    }

    @Override
    public TbItemDesc getItemDescById(Long itemId) {
        /*//查询缓存，如果有缓存，直接返回
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY+":"+itemId+":"+ITEM_DESC_KEY);
            //判断数据是否存在
            if(!StringUtils.isNullOrEmpty(json))
            {
                TbItemDesc itemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
                return itemDesc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //根据商品ID查询商品基本信息
        TbItemDesc itemDesc =  itemDescMapper.selectByPrimaryKey(itemId);
        //向redis中添加数据
        try {
            jedisClient.set(REDIS_ITEM_KEY+":"+itemId+":"+ITEM_DESC_KEY,
                    JsonUtils.objectToJson(itemDesc));
            //设置key的过期时间
            jedisClient.expire(REDIS_ITEM_KEY+":"+itemId+":"+ITEM_DESC_KEY, ITEM_EXPIRE_SECOND);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemDesc;*/


        //根据商品ID查询商品基本信息
        TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
        return itemDesc;
    }

    @Override
    public TbItemParamItem getItemParamItemById(Long itemId) {
        /*//查询缓存，如果有缓存，直接返回
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY+":"+itemId+":"+ITEM_PARAM_KEY);
            //判断数据是否存在
            if(!StringUtils.isNullOrEmpty(json))
            {
                TbItemParamItem itemParamItem = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
                return itemParamItem;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //根据商品ID查询商品基本信息
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list =  itemParamItemMapper.selectByExample(example);
        if(list!=null && list.size()>0)
        {
            TbItemParamItem itemParamItem = list.get(0);
            //向redis中添加数据
            try {
                jedisClient.set(REDIS_ITEM_KEY+":"+itemId+":"+ITEM_PARAM_KEY,
                        JsonUtils.objectToJson(itemParamItem));
                //设置key的过期时间
                jedisClient.expire(REDIS_ITEM_KEY+":"+itemId+":"+ITEM_PARAM_KEY, ITEM_EXPIRE_SECOND);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return itemParamItem;
        }
        return null;*/

        //根据商品ID查询商品基本信息
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if (list != null && list.size() > 0) {
            TbItemParamItem itemParamItem = list.get(0);
            return itemParamItem;
        }
        return null;
    }
}

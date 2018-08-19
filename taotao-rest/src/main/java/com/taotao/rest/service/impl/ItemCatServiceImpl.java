package com.taotao.rest.service.impl;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;
    @Override
    public ItemCatResult getItemCatList()
    {
        List list = getItemCatList((long)0);
        ItemCatResult result = new ItemCatResult();
        result.setData(list);
        return result;
    }

    private List getItemCatList(Long parentID)
    {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentID);
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        List resultList = new ArrayList();
        int index = 0;
        for(TbItemCat tbItemCat : list)
        {
            if(index>=14)
                break;;
            if(tbItemCat.getIsParent())
            {
                CatNode catNode = new CatNode();
                catNode.setUrl("/products/"+tbItemCat.getId()+".html");
                if(tbItemCat.getParentId()==0)
                {
                    catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
                    index++;
                }
                else
                    catNode.setName(tbItemCat.getName());
                catNode.setItems(getItemCatList(tbItemCat.getId()));
                resultList.add(catNode);
            }
            else
            {
                String item = "/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName();
                resultList.add(item);
            }
        }
        return resultList;
    }

}

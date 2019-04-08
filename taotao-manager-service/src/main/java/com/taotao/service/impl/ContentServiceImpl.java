package com.taotao.service.impl;

import java.util.Date;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public TaotaoResult insertContent(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);
        return TaotaoResult.ok();
    }

    @Override
    public EasyUIDataGridResult getContentList(Long categoryId, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        TbContentExample example = new TbContentExample();
        com.taotao.pojo.TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> list = contentMapper.selectByExample(example);
        PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(list);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}



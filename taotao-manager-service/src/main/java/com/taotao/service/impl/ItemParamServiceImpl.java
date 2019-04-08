package com.taotao.service.impl;

import java.util.Date;
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private TbItemParamMapper itemParamMapper;

    @Override
    public TaotaoResult getItemParamByCid(Long cid) {
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
        if (list != null && list.size() > 0) {
            TbItemParam itemParam = list.get(0);
            return TaotaoResult.ok(itemParam);
        }
        return TaotaoResult.ok();
    }

    @Override
    public EasyUIDataGridResult getItemParamList(int page, int rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        //查询
        TbItemParamExample tbItemParamExample = new TbItemParamExample();
        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);
        for (TbItemParam param : list) {
            String temp = param.getParamData();
            param.setParamData(temp.substring(temp.indexOf("\":\"") + 3, temp.indexOf("\",\"")));
        }
        //提取分页信息
        PageInfo<TbItemParam> pageInfo = new PageInfo<TbItemParam>(list);
        //返回处理结果
        EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
        easyUIDataGridResult.setTotal(pageInfo.getTotal());
        easyUIDataGridResult.setRows(list);
        return easyUIDataGridResult;
    }

    @Override
    public TaotaoResult insertItemParam(Long cid, String paramData) {
        TbItemParam itemParam = new TbItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
        itemParam.setParamData(paramData);
        itemParamMapper.insert(itemParam);
        return TaotaoResult.ok();
    }
}



package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.*;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.IDUtils;
import utils.JsonUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    public TbItem getItemById(Long itemId)
    {
        TbItem item = null;
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = itemMapper.selectByExample(example);
        if(list!=null && list.size()>0)
            item =  list.get(0);
        return item;
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        //查询
        TbItemExample tbItemExample = new TbItemExample();
        List<TbItem> list = itemMapper.selectByExample(tbItemExample);
        //提取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
        //返回处理结果
        EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
        easyUIDataGridResult.setTotal(pageInfo.getTotal());
        easyUIDataGridResult.setRows(list);
        return easyUIDataGridResult;
    }

    @Override
    public TaotaoResult createItem(TbItem item, String desc, String itemParam) {
        //生成商品ID
        long itemID = IDUtils.genItemId();
        //补全TbItem属性
        item.setId(itemID);
        //商品状态：1-正常，2-下架，3-删除
        item.setStatus((byte)1);
        //创建时间和更新时间
        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);
        //插入商品表
        itemMapper.insert(item);
        //商品描述
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemID);
        itemDesc.setCreated(date);
        itemDesc.setItemDesc(desc);
        itemDesc.setUpdated(date);
        itemDescMapper.insert(itemDesc);
        //添加商品规格参数
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(itemID);
        itemParamItem.setParamData(itemParam);
        itemParamItem.setCreated(date);
        itemParamItem.setUpdated(date);
        itemParamItemMapper.insert(itemParamItem);

        return TaotaoResult.ok();
    }

    @Override
    public String getItemParamHtml(Long itemId) {
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if(list==null || list.isEmpty())
            return "";
        TbItemParamItem itemParamItem = list.get(0);
        String paramData = itemParamItem.getParamData();
        List<Map> mapList = JsonUtils.jsonToList(paramData, Map.class);
        StringBuffer sb = new StringBuffer();

        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\">\n");
        sb.append("	<tbody>\n");
        for(Map map:mapList)
        {
            sb.append("		<tr>\n");
            sb.append("			<th colspan=\"2\">"+map.get("group")+"</th>\n");
            sb.append("		</tr>\n");
            List<Map> mapList2 = (List<Map>)map.get("params");
            for(Map map2 : mapList2)
            {
                sb.append("		<tr>\n");
                sb.append("			<td>"+map2.get("k") +"</td>\n");
                sb.append("			<td>"+map2.get("v")+"</td>\n");
                sb.append("		</tr>\n");
            }
        }
        sb.append("	</tbody>\n");
        sb.append("</table>");

        return sb.toString();
    }


}

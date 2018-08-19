package com.taotao.portal.service.impl;

import com.taotao.common.pojo.HttpClientUtil;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.PortalItem;
import com.taotao.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import utils.JsonUtils;

import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {
    @Value("${REST_ITEM_BASE_URL}")
    private String REST_ITEM_BASE_URL;
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_ITEM_DESC_URL}")
    private String REST_ITEM_DESC_URL;
    @Value("${REST_ITEM_PARAM_URL}")
    private String REST_ITEM_PARAM_URL;

    @Override
    public TbItem getItemById(Long itemId) {
        String json = HttpClientUtil.doGet(REST_BASE_URL+REST_ITEM_BASE_URL+itemId);
        TaotaoResult result = TaotaoResult.formatToPojo(json, PortalItem.class);
        TbItem item = (TbItem) result.getData();
        return item;
    }

    @Override
    public String getItemDescById(Long itemId) {
        String json = HttpClientUtil.doGet(REST_BASE_URL+REST_ITEM_DESC_URL+itemId);
        TaotaoResult result =  TaotaoResult.formatToPojo(json, TbItemDesc.class);
        TbItemDesc itemDesc = (TbItemDesc) result.getData();
        String desc = itemDesc.getItemDesc();
        return desc;
    }

    @Override
    public String getItemParamItemById(Long itemId) {
        String json = HttpClientUtil.doGet(REST_BASE_URL+REST_ITEM_PARAM_URL+itemId);
        //把json对象转换成java对象
        TaotaoResult result =  TaotaoResult.formatToPojo(json, TbItemParamItem.class);
        TbItemParamItem itemParamItem = (TbItemParamItem) result.getData();
        String param = itemParamItem.getParamData();
        List<Map> paramList = JsonUtils.jsonToList(param, Map.class);
        StringBuffer sb = new StringBuffer();
        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\">\n");
        sb.append("	<tbody>\n");
        for(Map map:paramList)
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

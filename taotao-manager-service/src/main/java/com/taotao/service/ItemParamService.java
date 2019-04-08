package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;

public interface ItemParamService {
    TaotaoResult getItemParamByCid(Long cid);
    EasyUIDataGridResult getItemParamList(int page, int rows);
    TaotaoResult insertItemParam(Long cid, String paramData);
}

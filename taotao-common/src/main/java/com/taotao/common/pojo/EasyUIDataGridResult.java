package com.taotao.common.pojo;

import java.util.List;

public class EasyUIDataGridResult {
    private long total;
    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}

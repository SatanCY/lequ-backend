package com.sanwei.lequ.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author：SatanCY
 * @Date：2024/9/20 17:19
 */
@Data
public class PageRequest implements Serializable {

    private static final long serialVersionUID = -7019517533050717815L;
    /**
     * 页面大小
     */
    protected int pageSize = 10;

    /**
     * 当前页码
     */
    protected int pageNum = 1;
}

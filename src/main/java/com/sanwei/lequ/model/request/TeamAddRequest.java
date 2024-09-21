package com.sanwei.lequ.model.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author：SatanCY
 * @Date：2024/9/20 20:45
 */
@Data
public class TeamAddRequest implements Serializable {
    private static final long serialVersionUID = -6873928417821380191L;

    /**
     * 队伍名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 最大人数
     */
    private Integer maxNum;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 0 - 公开，1 - 私有，2 - 加密
     */
    private Integer status;

    /**
     * 密码
     */
    private String password;
}

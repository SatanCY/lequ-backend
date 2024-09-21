package com.sanwei.lequ.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户加入队伍的请求体
 *
 * @Author：SatanCY
 * @Date：2024/9/21 11:46
 */
@Data
public class TeamJoinRequest implements Serializable {
    private static final long serialVersionUID = -5263626865667414500L;

    /**
     * id
     *
     */
    private long teamId;

    /**
     * 密码
     *
     */
    private String password;
}

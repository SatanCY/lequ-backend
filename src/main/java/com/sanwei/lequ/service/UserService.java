package com.sanwei.lequ.service;

import com.sanwei.lequ.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户服务
 *
 * @author <a href="https://github.com">SatanCY</a>
 * @from <a href="https://github.com">GitHub</a>
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @param planetCode    星球编号
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword, String planetCode);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     *
     * @param originUser
     * @return
     */
    User getSafetyUser(User originUser);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    int userLogout(HttpServletRequest request);

    /**
     * 根据标签搜索用户
     *
     * @param tagNameList 用户要拥有的标签
     * @return
     */
    List<User> searchUserByTags(List<String> tagNameList);

    /**
     * 获取当前登陆的用户
     *
     * @param request
     * @return
     */
    User getLogininUser(HttpServletRequest request);

    /**
     * 更新当前用户
     *
     * @param user 更新后的用户信息
     * @param loginUser 需要更新的当前登录用户信息
     * @return
     */
    int updateUser(User user, User loginUser);

    /**
     * 判断是否为管理员
     *
     * @param request http请求
     * @return
     */
    boolean isAdmin(HttpServletRequest request);

    /**
     * 判断是否为管理员
     *
     * @param loginUser 当前用户
     * @return
     */
    boolean isAdmin(User loginUser);
}

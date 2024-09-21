package com.sanwei.lequ.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sanwei.lequ.model.domain.UserTeam;
import com.sanwei.lequ.service.UserTeamService;
import com.sanwei.lequ.mapper.UserTeamMapper;
import org.springframework.stereotype.Service;

/**
* @author SatanCY
* @description 针对表【user_team(用户队伍关系)】的数据库操作Service实现
* @createDate 2024-09-20 17:16:36
*/
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
    implements UserTeamService{

}





package com.sanwei.lequ.service;

import com.sanwei.lequ.model.domain.Team;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sanwei.lequ.model.domain.User;
import com.sanwei.lequ.model.dto.TeamQuery;
import com.sanwei.lequ.model.request.TeamJoinRequest;
import com.sanwei.lequ.model.request.TeamUpdateRequest;
import com.sanwei.lequ.model.vo.TeamUserVo;

import java.util.List;

/**
* @author SatanCY
* @description 针对表【team(队伍)】的数据库操作Service
* @createDate 2024-09-20 17:12:51
*/
public interface TeamService extends IService<Team> {

    /**
     *   添加队伍
     * @param team
     * @param loginUser
     * @return
     */
    long addTeam(Team team, User loginUser);

    /**
     * 查询队伍
     *
     * @param teamQuery
     * @param isAdmin
     * @return
     */
    List<TeamUserVo> listTeams(TeamQuery teamQuery, boolean isAdmin);

    boolean updateTeam(TeamUpdateRequest team, User loginUser);

    Boolean joinTeam(TeamJoinRequest teamJoinRequest, User loginUser);
}

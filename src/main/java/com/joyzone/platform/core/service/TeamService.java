package com.joyzone.platform.core.service;

import com.github.pagehelper.Page;
import com.joyzone.platform.common.utils.R;
import com.joyzone.platform.core.base.BaseService;
import com.joyzone.platform.core.dto.CouponDto;
import com.joyzone.platform.core.dto.ShopTeamsDto;
import com.joyzone.platform.core.dto.TeamDto;
import com.joyzone.platform.core.dto.TeamRuleDto;
import com.joyzone.platform.core.mapper.ShopCouponMapper;
import com.joyzone.platform.core.mapper.TeamMapper;
import com.joyzone.platform.core.model.BaseModel;
import com.joyzone.platform.core.model.ShopCouponModel;
import com.joyzone.platform.core.model.ShopTypeModel;
import com.joyzone.platform.core.model.TeamModel;
import com.joyzone.platform.core.vo.AppTeamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TeamService extends BaseService<TeamModel> {

    @Autowired
    private TeamMapper teamMapper;
    
    @Autowired
    private GroupService groupService;

    public  List<TeamDto> getTeamList(TeamModel teamModel, Integer sort){
        return teamMapper.getTeamList(teamModel,sort);
    }

    public Map<String,Object> checkTeamIfSuccess(Long teamId){
        return teamMapper.checkTeamIfSuccess(teamId);
    }

    /**
     * 添加或更新组队信息
     * @param teamModel
     * @return
     */
    public int saveTeam(TeamModel teamModel){
        Date date = new Date();
        if(teamModel.getId() == null){//添加
            List<TeamModel> teamModelList = teamMapper.checkUserStartTeam(teamModel.getOwner(),teamModel.getShopId());
            if(teamModelList != null && teamModelList.size() > 0){
                return 999;
            }
            teamModel.setType(ShopTypeModel.SHOP_TYPE_ZD);  //组队店家
            teamModel.setStatus(0); //组队有效
            teamModel.setCreateTime(date);
            teamModel.setResult(0); //组队中
           //添加组队时创建聊天群
           String groupId = groupService.createTeamGroup(teamModel.getShopId());
           teamModel.setChatGroupId(groupId);
            return teamMapper.insertSelective(teamModel);
        }
        //更新
        teamModel.setUpdateTime(date);
        return teamMapper.updateByPrimaryKeySelective(teamModel);
    }

    /**
     * app获取组队信息列表
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public R getAppTeamList(Long userId,Integer pageNum,Integer pageSize){
        List<AppTeamVO> list = teamMapper.getAppTeamList(userId,pageNum,pageSize);
        if(list != null && list.size() > 0){
            Page page = new Page();
            page = (Page)list;
            return R.pageToData(page.getTotal(),page.getResult());
        }
        return R.pageToData(0L,list);
    }

    public List<ShopTeamsDto> getShopTeamListByShopId(Long shopId){
        return teamMapper.getShopTeamListByShopId(shopId);
    }

    public TeamRuleDto getTeamRuleList(Long teamId,Long userId){
        return teamMapper.getTeamRuleList(teamId,userId);
    }

}

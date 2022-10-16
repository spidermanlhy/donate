package com.donate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.donate.dto.reqdto.ApplyReqDTO;
import com.donate.dto.respdto.ApplyDetailRespDTO;
import com.donate.entity.*;
import com.donate.mapper.*;
import com.donate.service.IApplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ApplyServiceImpl implements IApplyService {

  @Autowired
  DtApplyMapper dtApplyMapper;

  @Autowired
  DtAppealMapper dtAppealMapper;

  @Autowired
  ChildListMapper childListMapper;

  @Autowired
  AppUserMapper appUserMapper;

  @Autowired
  DtRecordMapper dtRecordMapper;

  @Autowired
  DtApplyUsedMapper dtApplyUsedMapper;

  /**
   * 分页获取活动详情
   *
   * @param reqDTO@return
   */
  @Override
  public Map<String, Object> getApplyDetail(ApplyReqDTO reqDTO) {
    Map<String,Object> result=new HashMap<>();
    QueryWrapper<DtApplyEntity> queryWrapper=new QueryWrapper<>();
    if(!ObjectUtils.isEmpty(reqDTO.getUserId())){
      queryWrapper.eq("user_id",reqDTO.getUserId());
    }

    if(!ObjectUtils.isEmpty(reqDTO.getTitle())){
      queryWrapper.like("title",reqDTO.getTitle());
    }

    if(!ObjectUtils.isEmpty(reqDTO.getApplyType())){
      queryWrapper.eq("apply_type",reqDTO.getApplyType());
    }

    if(!ObjectUtils.isEmpty(reqDTO.getApplyState())&&reqDTO.getApplyState()>=0){
      if(reqDTO.getApplyState()<3){
        queryWrapper.eq("apply_state",reqDTO.getApplyState());
      }else if(reqDTO.getApplyState()==3){
        queryWrapper.eq("completed",0);
      }else if(reqDTO.getApplyState()==4){
        queryWrapper.eq("completed",2);
      }
    }

    if(!ObjectUtils.isEmpty(reqDTO.getCompleted())){
      queryWrapper.eq("completed",reqDTO.getCompleted());
    }

    if(reqDTO.getCity()!=null&&reqDTO.getCity().size()>0){
      queryWrapper.isNotNull("child_id");
      queryWrapper.gt("child_id",0);
      queryWrapper.eq("city_code",String.join("-",reqDTO.getCity()));
    }

    queryWrapper.eq("is_deleted",0);
    List<DtApplyEntity> list=new ArrayList<>();
    if(reqDTO.getPage()!=null&&reqDTO.getPage()>0){
      IPage page=new Page(reqDTO.getPage(), reqDTO.getSize());
      list=dtApplyMapper.selectPage(page,queryWrapper).getRecords();
    }else{
      list=dtApplyMapper.selectList(queryWrapper);
    }

    List<ApplyDetailRespDTO> data=new ArrayList<>();
    list.forEach(item->{
      ApplyDetailRespDTO applyDetailRespDTO=new ApplyDetailRespDTO();
      BeanUtils.copyProperties(item,applyDetailRespDTO);
      QueryWrapper<DtAppealEntity> appealEntityQueryWrapper=new QueryWrapper<>();
      appealEntityQueryWrapper.eq("apply_id",item.getId());
      DtAppealEntity appealEntity=dtAppealMapper.selectOne(appealEntityQueryWrapper);
      applyDetailRespDTO.setAppealCount(appealEntity.getAppealCount());
      applyDetailRespDTO.setCompleted(appealEntity.getCompleted());
      applyDetailRespDTO.setCompletedStatus(item.getCompleted());
      if(applyDetailRespDTO.getCompletedStatus()==0){
        applyDetailRespDTO.setStatusName("已完成");
      }else if(applyDetailRespDTO.getCompletedStatus()==2){
        applyDetailRespDTO.setStatusName("已下线");
      }else if(applyDetailRespDTO.getApplyState()==0){
        applyDetailRespDTO.setStatusName("待审核");
      }else if(applyDetailRespDTO.getApplyState()==1){
        applyDetailRespDTO.setStatusName("进行中");
      }else if(applyDetailRespDTO.getApplyState()==2){
        applyDetailRespDTO.setStatusName("已驳回");
      }

      data.add(applyDetailRespDTO);
    });

    Map<String,Object> dataMap=new HashMap<>();
    dataMap.put("list",data);
    dataMap.put("current",reqDTO.getPage());
    dataMap.put("size",reqDTO.getSize());
    dataMap.put("total",dtApplyMapper.selectCount(queryWrapper));
    result.put("data",dataMap);
    result.put("code","ACK");
    result.put("message","查询成功");
    result.put("nonBizError",false);
    return result;
  }

  @Override
  public Map<String, Object> getApplyLunbo(ApplyReqDTO reqDTO) {
    Map<String,Object> result=new HashMap<>();
    QueryWrapper<DtApplyEntity> queryWrapper=new QueryWrapper<>();
    if(!ObjectUtils.isEmpty(reqDTO.getUserId())){
      queryWrapper.eq("user_id",reqDTO.getUserId());
    }

    if(!ObjectUtils.isEmpty(reqDTO.getTitle())){
      queryWrapper.like("title",reqDTO.getTitle());
    }

    if(!ObjectUtils.isEmpty(reqDTO.getApplyType())){
      queryWrapper.eq("apply_type",reqDTO.getApplyType());
    }

    if(!ObjectUtils.isEmpty(reqDTO.getApplyState())){
      queryWrapper.eq("apply_state",reqDTO.getApplyState());
    }

    if(!ObjectUtils.isEmpty(reqDTO.getCompleted())){
      queryWrapper.eq("completed",reqDTO.getCompleted());
    }

    queryWrapper.eq("is_deleted",0);
    List<DtApplyEntity> list=new ArrayList<>();
    if(reqDTO.getPage()!=null&&reqDTO.getPage()>0){
      IPage page=new Page(reqDTO.getPage(), reqDTO.getSize());
      list=dtApplyMapper.selectPage(page,queryWrapper).getRecords();
    }else{
      list=dtApplyMapper.selectList(queryWrapper);
    }

    List<ApplyDetailRespDTO> data=new ArrayList<>();
    list.forEach(item->{
      ApplyDetailRespDTO applyDetailRespDTO=new ApplyDetailRespDTO();
      BeanUtils.copyProperties(item,applyDetailRespDTO);
      QueryWrapper<DtAppealEntity> appealEntityQueryWrapper=new QueryWrapper<>();
      appealEntityQueryWrapper.eq("apply_id",item.getId());
      DtAppealEntity appealEntity=dtAppealMapper.selectOne(appealEntityQueryWrapper);
      applyDetailRespDTO.setAppealCount(appealEntity.getAppealCount());
      applyDetailRespDTO.setCompleted(appealEntity.getCompleted());
      data.add(applyDetailRespDTO);
    });

    Map<String,Object> dataMap=new HashMap<>();
    result.put("data",data);
    result.put("code","ACK");
    result.put("message","查询成功");
    result.put("nonBizError",false);
    return result;
  }

  @Override
  public Map<String, Object> addApply(DtApplyEntity dtApplyEntity) {
    Map<String,Object> result=new HashMap<>();
    if(dtApplyEntity.getApplyType()==1){
      ChildListEntity childListEntity=childListMapper.selectById(dtApplyEntity.getChildId());
      dtApplyEntity.setCityCode(childListEntity.getCity());
    }
    dtApplyEntity.setGmtCreated(new Date());
    dtApplyEntity.setGmtModified(new Date());
    dtApplyEntity.setCompleted(1);
    dtApplyEntity.setApplyState(0);
    dtApplyEntity.setIsDeleted(0);
    dtApplyEntity.setUserName(appUserMapper.selectById(dtApplyEntity.getUserId()).getUsername());
    dtApplyMapper.insert(dtApplyEntity);

    DtAppealEntity dtAppealEntity=new DtAppealEntity();
    dtAppealEntity.setAppealCount(0f);
    dtAppealEntity.setAppealState(1);
    dtAppealEntity.setAppealType(dtApplyEntity.getApplyType());
    dtAppealEntity.setCompleted(0f);
    dtAppealEntity.setApplyId(dtApplyEntity.getId());
    dtAppealEntity.setAppealVerify(0);
    dtAppealEntity.setContent(dtApplyEntity.getContent());
    dtAppealEntity.setFile(dtApplyEntity.getFile());
    dtAppealEntity.setGmtCreated(new Date());
    dtAppealEntity.setGmtModified(new Date());
    dtAppealEntity.setGoal(dtApplyEntity.getGoal());
    dtAppealEntity.setIsDeleted(0);
    dtAppealEntity.setTitle(dtApplyEntity.getTitle());
    dtAppealEntity.setUserId(dtApplyEntity.getUserId());
    dtAppealEntity.setUserName(appUserMapper.selectById(dtApplyEntity.getUserId()).getUsername());
    dtAppealMapper.insert(dtAppealEntity);
    result.put("code","ACK");
    result.put("message","添加成功");
    result.put("nonBizError",false);
    return result;

  }

  @Override
  public Map<String, Object> updateApply(DtApplyEntity dtApplyEntity) {
    Map<String,Object> result=new HashMap<>();
    dtApplyEntity.setGmtModified(new Date());
    dtApplyEntity.setApplyState(0);
    dtApplyMapper.updateById(dtApplyEntity);

    QueryWrapper<DtAppealEntity> queryWrapper=new QueryWrapper<>();
    queryWrapper.eq("apply_id",dtApplyEntity.getId());
    DtAppealEntity dtAppealEntity=dtAppealMapper.selectOne(queryWrapper);
    dtAppealEntity.setAppealState(1);
    dtAppealEntity.setAppealType(dtApplyEntity.getApplyType());
    dtAppealEntity.setApplyId(dtApplyEntity.getId());
    dtAppealEntity.setAppealVerify(0);
    dtAppealEntity.setContent(dtApplyEntity.getContent());
    dtAppealEntity.setFile(dtApplyEntity.getFile());
    dtAppealEntity.setGmtModified(new Date());
    dtAppealEntity.setGoal(dtApplyEntity.getGoal());
    dtAppealEntity.setIsDeleted(0);
    dtAppealEntity.setTitle(dtApplyEntity.getTitle());
    dtAppealMapper.updateById(dtAppealEntity);
    result.put("code","ACK");
    result.put("message","修改成功");
    result.put("nonBizError",false);
    return result;
  }

  @Override
  public Map<String, Object> getApplyById(int id) {
    Map<String,Object> result=new HashMap<>();
    DtApplyEntity applyEntity=dtApplyMapper.selectById(id);

    List<ApplyDetailRespDTO> data=new ArrayList<>();

    ApplyDetailRespDTO applyDetailRespDTO=new ApplyDetailRespDTO();
    BeanUtils.copyProperties(applyEntity,applyDetailRespDTO);
    QueryWrapper<DtAppealEntity> appealEntityQueryWrapper=new QueryWrapper<>();
    appealEntityQueryWrapper.eq("apply_id",applyEntity.getId());
    DtAppealEntity appealEntity=dtAppealMapper.selectOne(appealEntityQueryWrapper);
    applyDetailRespDTO.setAppealCount(appealEntity.getAppealCount());
    applyDetailRespDTO.setCompleted(appealEntity.getCompleted());
    applyDetailRespDTO.setCompletedStatus(applyEntity.getCompleted());
    if(applyDetailRespDTO.getCompletedStatus()==0){
      applyDetailRespDTO.setStatusName("已完成");
    }else if(applyDetailRespDTO.getCompletedStatus()==2){
      applyDetailRespDTO.setStatusName("已下线");
    }else if(applyDetailRespDTO.getApplyState()==0){
      applyDetailRespDTO.setStatusName("待审核");
    }else if(applyDetailRespDTO.getApplyState()==1){
      applyDetailRespDTO.setStatusName("进行中");
    }else if(applyDetailRespDTO.getApplyState()==2){
      applyDetailRespDTO.setStatusName("已驳回");
    }

    Map<String,Object> dataMap=new HashMap<>();
    dataMap.put("data",applyEntity);
    result.put("data",dataMap);
    result.put("code","ACK");
    result.put("message","查询成功");
    result.put("nonBizError",false);
    return result;
  }

  @Override
  public Map<String, Object> delApply(int id) {
    Map<String,Object> result=new HashMap<>();
    DtApplyEntity applyEntity=dtApplyMapper.selectById(id);
    applyEntity.setIsDeleted(1);
    dtApplyMapper.updateById(applyEntity);
    result.put("code","ACK");
    result.put("message","删除成功");
    result.put("nonBizError",false);
    return result;
  }

  @Override
  public Map<String, Object> approveApply(int id, int status) {
    Map<String,Object> result=new HashMap<>();
    DtApplyEntity applyEntity=dtApplyMapper.selectById(id);
    applyEntity.setApplyState(status);
    dtApplyMapper.updateById(applyEntity);
    result.put("code","ACK");
    result.put("message","修改成功");
    result.put("nonBizError",false);
    return result;
  }

  @Override
  public Map<String, Object> componentApply(int id, int status) {
    Map<String,Object> result=new HashMap<>();
    DtApplyEntity applyEntity=dtApplyMapper.selectById(id);
    applyEntity.setCompleted(status);
    dtApplyMapper.updateById(applyEntity);
    result.put("code","ACK");
    result.put("message","修改成功");
    result.put("nonBizError",false);
    return result;
  }

  @Override
  public Map<String, Object> getApplyInfo(int id, Integer userId) {
    Map<String,Object> result=new HashMap<>();
    Map<String,Object> data=new HashMap<>();
    //查询详细信息
    DtApplyEntity applyEntity=dtApplyMapper.selectById(id);
    data.put("apply",applyEntity);
    //查询捐款汇总
    QueryWrapper<DtAppealEntity> appealEntityQueryWrapper=new QueryWrapper<>();
    appealEntityQueryWrapper.eq("apply_id",id);
    DtAppealEntity appealEntity=dtAppealMapper.selectOne(appealEntityQueryWrapper);
    data.put("appeal",appealEntity);
    //查询捐款列表
    QueryWrapper<DtRecordEntity> recordEntityQueryWrapper=new QueryWrapper<>();
    recordEntityQueryWrapper.eq("receiver_id",id);
    List<DtRecordEntity> list=dtRecordMapper.selectList(recordEntityQueryWrapper);
    data.put("record",list);
    //查询儿童信息
    if(applyEntity.getChildId()!=null&&applyEntity.getChildId()>0){
      ChildListEntity childListEntity=childListMapper.selectById(applyEntity.getChildId());
      data.put("child",childListEntity);
      data.put("isChild",true);
    }else{
      data.put("isChild",false);
    }
    //查询捐款用途
    QueryWrapper<DtApplyUsedEntity> applyUsedEntityQueryWrapper=new QueryWrapper<>();
    applyUsedEntityQueryWrapper.eq("apply_id",id);
    List<DtApplyUsedEntity> applyUsedEntities=dtApplyUsedMapper.selectList(applyUsedEntityQueryWrapper);
    data.put("used",applyUsedEntities);
    //获取是否个人活动
    if(applyEntity.getUserId()==userId){
      data.put("isSelf",true);
    }else{
      data.put("isSelf",false);
    }

    if(userId>0){
      data.put("login",true);
    }else{
      data.put("login",false);
    }

    data.put("canOper",false);
    //状态
    String stateName="";
    if(applyEntity.getCompleted()==0){
      stateName="已完成";
    }else if(applyEntity.getCompleted()==2){
      stateName="已下线";
    }else if(applyEntity.getApplyState()==0){
      stateName="待审核";
    }else if(applyEntity.getApplyState()==1){
      stateName="进行中";
      data.put("canOper",true);
    }else if(applyEntity.getApplyState()==2){
      stateName="已驳回";
    }
    data.put("stateName",stateName);

    AtomicReference<Float> nowUsed= new AtomicReference<>(0f);
    applyUsedEntities.forEach(item->{
      nowUsed.set(nowUsed.get() + item.getUsedMoney());
    });
    if(nowUsed.get()>=appealEntity.getCompleted()){
      data.put("canUsed",false);
    }else{
      data.put("canUsed",true);
    }
    data.put("nowUsed",nowUsed.get());
    data.put("can",appealEntity.getCompleted()-nowUsed.get());

    result.put("code","ACK");
    result.put("data",data);
    result.put("message","修改成功");
    result.put("nonBizError",false);
    return result;

  }
}

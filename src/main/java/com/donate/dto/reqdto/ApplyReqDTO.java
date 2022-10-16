package com.donate.dto.reqdto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ApplyReqDTO {
  /**
   * 申请人Id
   **/
  private Integer userId;
  /**
   * 用户名
   **/
  private String userName;
  /**
   * 审核人id
   **/
  private Integer verifyUserId;
  /**
   * 标题
   **/
  private String title;
  /**
   * 内容
   **/
  private String content;
  /**
   * 类型：0公益；1个人
   **/
  private Integer applyType;
  /**
   * 状态：0，审核中；1，通过；2拒绝
   **/
  private Integer applyState;
  /**
   * 完成：0已经，1进行，2下线
   **/
  private Integer completed;

  /**
   * 儿童ID
   **/
  private Integer childId;

  /**
   * 城市编码
   **/
  private String cityCode;

  private Integer page;

  private Integer size;

  private String search;

  private List<String> city;


}

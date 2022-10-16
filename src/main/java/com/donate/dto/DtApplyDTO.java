package com.donate.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DtApplyDTO {
  /**
   * 申请表Id
   **/
  private Integer id;
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
   * 目标
   **/
  private Float goal;
  /**
   * 完成：0已经，1进行，2下线
   **/
  private Integer completed;
  /**
   * 创建时间
   **/
  private Date gmtCreated;
  /**
   * 修改时间
   **/
  private Date gmtModified;
  /**
   * 照片链接
   **/
  private String file;
  /**
   * 是否删除
   **/
  private Integer isDeleted;
}

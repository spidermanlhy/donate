package com.donate.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DtRecordDTO {
  /**
   *
   **/
  private Integer id;
  /**
   * 捐赠人Id
   **/
  private Integer createdUserId;
  /**
   * 捐赠者姓名
   **/
  private String createdUserName;
  /**
   * 接收人Id
   **/
  private Integer receiverId;
  /**
   * 接收人姓名
   **/
  private String receiverName;
  /**
   * 校验人Id
   **/
  private Integer verifyUserId;
  /**
   * 标题
   **/
  private String title;
  /**
   * 目标
   **/
  private Float goal;
  /**
   * 善款金额
   **/
  private Float money;
  /**
   * 捐赠状态
   **/
  private Integer recordState;
  /**
   * 捐赠序列号
   **/
  private String payOrder;
  /**
   * 创建时间
   **/
  private Date gmtCreated;
  /**
   * 修改时间
   **/
  private Date gmtModified;
  /**
   *
   **/
  private Integer isDeleted;
}

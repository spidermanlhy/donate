package com.donate.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DtAppealDTO {
  /**
   * 求助表：个人；公益
   **/
  private Integer id;
  /**
   * 申请ID
   **/
  private Integer applyId;
  /**
   * 审批人Id
   **/
  private Integer userId;
  /**
   * 审批人名字
   **/
  private String userName;
  /**
   * 求职标题
   **/
  private String title;
  /**
   * 求助内容
   **/
  private String content;
  /**
   * 求助类型：0，公益；1个人
   **/
  private Integer appealType;
  /**
   * 状态：0，完成；1，进行中；
   **/
  private Integer appealState;
  /**
   * 通过：0，审核中；1，审核通过；2审核未通过
   **/
  private Integer appealVerify;
  /**
   * 捐赠次数
   **/
  private Float appealCount;
  /**
   * 封面
   **/
  private String file;
  /**
   * 目标金额
   **/
  private Float goal;
  /**
   * 完成金额
   **/
  private Float completed;
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

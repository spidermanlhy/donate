package com.donate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

;
;
;
;
;
;

@Data
@TableName("dt_appeal")
public class DtAppealEntity {
  /**
   * 求助表：个人；公益
   **/
  @TableId(type = IdType.AUTO, value = "id")
  private Integer id;
  /**
   * 申请ID
   **/
  @TableField(value = "apply_id")
  private Integer applyId;
  /**
   * 审批人Id
   **/
  @TableField(value = "user_id")
  private Integer userId;
  /**
   * 审批人名字
   **/
  @TableField(value = "user_name")
  private String userName;
  /**
   * 求职标题
   **/
  @TableField(value = "title")
  private String title;
  /**
   * 求助内容
   **/
  @TableField(value = "content")
  private String content;
  /**
   * 求助类型：0，公益；1个人
   **/
  @TableField(value = "appeal_type")
  private Integer appealType;
  /**
   * 状态：0，完成；1，进行中；
   **/
  @TableField(value = "appeal_state")
  private Integer appealState;
  /**
   * 通过：0，审核中；1，审核通过；2审核未通过
   **/
  @TableField(value = "appeal_verify")
  private Integer appealVerify;
  /**
   * 捐赠次数
   **/
  @TableField(value = "appeal_count")
  private Float appealCount;
  /**
   * 封面
   **/
  @TableField(value = "file")
  private String file;
  /**
   * 目标金额
   **/
  @TableField(value = "goal")
  private Float goal;
  /**
   * 完成金额
   **/
  @TableField(value = "completed")
  private Float completed;
  /**
   * 创建时间
   **/
  @TableField(value = "gmt_created")
  private Date gmtCreated;
  /**
   * 修改时间
   **/
  @TableField(value = "gmt_modified")
  private Date gmtModified;
  /**
   *
   **/
  @TableField(value = "is_deleted")
  private Integer isDeleted;
}

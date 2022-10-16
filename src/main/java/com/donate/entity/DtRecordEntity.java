package com.donate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("dt_record")
public class DtRecordEntity {
  /**
   *
   **/
  @TableId(type = IdType.AUTO, value = "id")
  private Integer id;
  /**
   * 捐赠人Id
   **/
  @TableField(value = "created_user_id")
  private Integer createdUserId;
  /**
   * 捐赠者姓名
   **/
  @TableField(value = "created_user_name")
  private String createdUserName;
  /**
   * 接收人Id
   **/
  @TableField(value = "receiver_id")
  private Integer receiverId;
  /**
   * 接收人姓名
   **/
  @TableField(value = "receiver_name")
  private String receiverName;
  /**
   * 校验人Id
   **/
  @TableField(value = "verify_user_id")
  private Integer verifyUserId;
  /**
   * 标题
   **/
  @TableField(value = "title")
  private String title;
  /**
   * 目标
   **/
  @TableField(value = "goal")
  private Float goal;
  /**
   * 善款金额
   **/
  @TableField(value = "money")
  private Float money;
  /**
   * 捐赠状态
   **/
  @TableField(value = "record_state")
  private Integer recordState;
  /**
   * 捐赠序列号
   **/
  @TableField(value = "pay_order")
  private String payOrder;
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

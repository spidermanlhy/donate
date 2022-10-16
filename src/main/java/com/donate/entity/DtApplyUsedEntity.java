package com.donate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("dt_apply_used")
public class DtApplyUsedEntity {

  @TableId(type = IdType.AUTO)
  private Long id;

  @TableField("apply_id")
  private Integer applyId;

  @TableField("used_money")
  private Float usedMoney;

  @TableField("used_content")
  private String usedContent;

  @TableField("add_user_id")
  private Integer addUserId;

  @TableField("used_state")
  private Integer usedState;

  @TableField("create_date")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createDate;
}

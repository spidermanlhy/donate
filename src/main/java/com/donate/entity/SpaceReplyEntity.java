package com.donate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("space_reply")
public class SpaceReplyEntity {

  @TableId(type = IdType.AUTO)
  private Integer id;

  @TableField("space_id")
  private Integer spaceId;

  @TableField("replay_id")
  private Integer replayId;

  @TableField("content")
  private String content;

  @TableField("create_date")
  private Date createDate;

  @TableField("add_user_id")
  private Integer addUserId;

  @TableField("add_user_name")
  private String addUserName;
}

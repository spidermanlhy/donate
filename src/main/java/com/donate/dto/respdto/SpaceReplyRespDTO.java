package com.donate.dto.respdto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SpaceReplyRespDTO {
  private Integer id;

  private Integer spaceId;

  private Integer replayId;

  private String content;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createDate;

  private Integer addUserId;

  private String addUserName;

  private String replyUserName;

  private boolean canEdit;
}

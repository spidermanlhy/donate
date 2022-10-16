package com.donate.dto.respdto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ApplyDetailRespDTO {
  private Integer id;

  private String title;

  private Integer type;

  private String file;

  private Float appealCount;

  private Float completed;

  private Float goal;

  private String statusName;

  /**
   * 类型：0公益；1个人
   **/
  private Integer applyType;
  /**
   * 状态：0，审核中；1，通过；2拒绝
   **/
  private Integer applyState;

  private Integer completedStatus;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date gmtModified;
}

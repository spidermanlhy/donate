package com.donate.dto.reqdto;

import lombok.Data;

@Data
public class NewsReqDTO {
  private Integer status;

  private Integer page;

  private Integer size;

  private Integer type;

  private String title;
}

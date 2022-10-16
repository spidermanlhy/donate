package com.donate.dto.reqdto;

import lombok.Data;

@Data
public class RecodeReqDTO {
  private Integer page;

  private Integer size;

  private String search;
}

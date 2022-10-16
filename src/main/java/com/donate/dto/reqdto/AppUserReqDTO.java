package com.donate.dto.reqdto;

import lombok.Data;

@Data
public class AppUserReqDTO {
  private Integer page;

  private Integer size;

  private String nickname;

  private String type;
}

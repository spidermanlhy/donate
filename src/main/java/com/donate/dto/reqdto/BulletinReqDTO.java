package com.donate.dto.reqdto;

import lombok.Data;

@Data
public class BulletinReqDTO {

  private Integer page;

  private Integer size;

  private String search;

  private Integer isShow;
}

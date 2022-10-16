package com.donate.dto.reqdto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginReqDTO {

  @NotBlank(message = "请您填写用户名")
  @NotNull(message = "请您填写用户名")
  private String username;

  @NotBlank(message = "请您填写密码")
  @NotNull(message = "请您填写密码")
  private String password;
}

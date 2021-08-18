package com.lyndexter.bookshop.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {
  @NotNull private String username;
  @NotNull private String password;
}

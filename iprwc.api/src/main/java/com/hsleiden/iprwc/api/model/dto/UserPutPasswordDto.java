package com.hsleiden.iprwc.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserPutPasswordDto extends UserDto {

    String oldPassword;

    String newPassword;
}


package com.cos.controllerdemo.web.dto.user;

import com.cos.controllerdemo.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {
    private int isPageOwner;
    private User user;


}

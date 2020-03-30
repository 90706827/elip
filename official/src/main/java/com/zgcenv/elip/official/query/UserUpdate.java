package com.zgcenv.elip.official.query;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * @ClassName userUpdate
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/29 23:32
 * @Version 1.0
 **/
@Data
public class UserUpdate {

    @NotNull(message = "ID不正确！")
    @Positive(message = "ID不正确！")
    private Long id;

    @NotBlank(message = "用户名称不能为空！")
    @Pattern(regexp = "[0-9a-zA-Z]{6,20}", message = "用户名称由6至20位数字和字母组成！")
    private String username;

    @Pattern(regexp = "[0-9a-zA-Z]{6,20}", message = "密码由6至20位数字和字母组成！")
    private String password;

    @Email(message = "邮箱格式不正确！")
    private String email;

    @Pattern(regexp = "1[0-9]{10}",message = "手机号码不正确！")
    private String phone;
}

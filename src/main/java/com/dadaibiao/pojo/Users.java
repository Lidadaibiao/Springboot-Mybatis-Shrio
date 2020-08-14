package com.dadaibiao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xuedong Li
 * @version V1.0
 * @Package com.dadaibiao.shiro.pojo
 * @date 2020/8/14 14:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private Integer id;
    private String userName;
    private String passWord;
    private String perms;
}

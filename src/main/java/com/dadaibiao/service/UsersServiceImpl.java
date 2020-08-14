package com.dadaibiao.service;


import com.dadaibiao.mapper.UsersMapper;
import com.dadaibiao.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xuedong Li
 * @version V1.0
 * @Package com.dadaibiao.service
 * @date 2020/8/14 14:25
 */
@Service
public class UsersServiceImpl {

    @Autowired
    UsersMapper usersMapper;
    public Users queryUserByUserName(String userName) {
        return usersMapper.queryUserByUserName(userName);
    }
}

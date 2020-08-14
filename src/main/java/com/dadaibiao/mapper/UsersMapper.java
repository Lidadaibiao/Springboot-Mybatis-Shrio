package com.dadaibiao.mapper;


import com.dadaibiao.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author xuedong Li
 * @version V1.0
 * @Package com.dadaibiao.shiro.mapper
 * @date 2020/8/14 14:18
 */
@Repository
@Mapper
public interface UsersMapper {
    Users queryUserByUserName(String userName);
}

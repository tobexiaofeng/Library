package com.indi.mapper.login;

import com.indi.bean.login.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.lang.annotation.Documented;

/**
 * @author 华峰
 * @create: 2020-04-07 13:58
 */
public interface LoginMapper {

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Insert("insert into library_user (`account`,`password`) values(#{account},#{password})")
    void register(User user);

    /**
     * 判断用户是否存在数据库中
     * @param user
     * @return
     */
    User isExist(User user);
}

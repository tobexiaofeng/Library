package com.indi.service.loginservice;

import com.indi.bean.login.User;
import com.indi.mapper.login.LoginMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 华峰
 * @create: 2020-04-07 14:00
 */
@Service
public class LoginService {

    SqlSessionFactory sqlSessionFactory;

    @Autowired
    LoginMapper loginMapper;

    public LoginService(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     *
     * @param user
     * @return
     */
    public int isExist(User user){
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            LoginMapper mapper = sqlSession.getMapper(LoginMapper.class);
            User dbUser = mapper.isExist(user);
        }
        return 0;
    }

    /**
     *
     * @param user
     */
    public int registerUser(User user){
//        try(SqlSession sqlSession= sqlSessionFactory.openSession();){
//            LoginMapper mapper = sqlSession.getMapper(LoginMapper.class);
//            mapper.register(user);
//        }catch (Exception e){
//            e.printStackTrace();
//            return 0;
//        }
        loginMapper.register(user);
        return 1;
    }
}

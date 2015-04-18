package com.circular.browser.dao.mapper;

import com.circular.browser.dao.base.MysqlMapper;
import com.circular.browser.dao.model.User;
import com.circular.browser.dao.model.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends MysqlMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
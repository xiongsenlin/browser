package com.circular.browser.auth.dao.mapper;

import com.circular.browser.auth.base.MysqlMapper;
import com.circular.browser.auth.dao.model.Account;
import com.circular.browser.auth.dao.model.AccountExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountMapper extends MysqlMapper {
    int countByExample(AccountExample example);

    int deleteByExample(AccountExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(Account record);

    int insertSelective(Account record);

    List<Account> selectByExample(AccountExample example);

    Account selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountExample example);

    int updateByExample(@Param("record") Account record, @Param("example") AccountExample example);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}
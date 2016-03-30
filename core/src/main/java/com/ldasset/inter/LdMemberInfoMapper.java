package com.ldasset.inter;

import com.ldasset.models.LdMemberInfo;

public interface LdMemberInfoMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(LdMemberInfo record);

    int insertSelective(LdMemberInfo record);

    LdMemberInfo selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(LdMemberInfo record);

    int updateByPrimaryKey(LdMemberInfo record);
}
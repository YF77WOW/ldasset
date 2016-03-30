package com.ldasset.inter;

import com.ldasset.models.Members;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IMember {

    public Members login(@Param("userName")String userName, @Param("password")String password);

    public Members getByUserPhone(String userPhone);

    public List<Members> findMembers();

}


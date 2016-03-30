package com.ldasset.service;

import com.github.pagehelper.PageInfo;
import com.ldasset.models.Members;

import java.util.List;


public interface IMemberService {

      Members login(String userName,String password);

      Members getByUserPhone(String userPhone);

      PageInfo<Members> findMembers();

      boolean checkPhoneUnique(String userPhone);

}

package com.ldasset.service.spring;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ldasset.inter.IMember;
import com.ldasset.models.Members;
import com.ldasset.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService implements IMemberService {

    @Autowired
    private IMember iMember;

    @Override
    public Members login(String userName, String password) {
        return iMember.login(userName, password);
    }

    @Override
    public Members getByUserPhone(String userPhone) {
        return iMember.getByUserPhone(userPhone);
    }

    @Override
    public PageInfo<Members>  findMembers() {
        PageHelper.startPage(1, 5);
        List<Members> memberList = iMember.findMembers();
        PageInfo<Members> pageInfo = new PageInfo<Members>(memberList);
        return pageInfo;
    }

    @Override
    public boolean checkPhoneUnique(String userPhone) {
        Members members = iMember.getByUserPhone(userPhone);
        if (members == null) {
            return false;
        }
        return true;
    }
}

package com.ldasset.controller.restful;

import com.ldasset.common.RestfulResult;
import com.ldasset.exception.ServiceException;
import com.ldasset.models.Members;
import com.ldasset.service.IMemberService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/app/member")
public class MemberController {

    @Autowired
    private IMemberService memberService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public RestfulResult login(
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "userName", required = true) String userName,
            @RequestParam(value = "userPassword", required = true) String userPassword) {
        RestfulResult restfulResult = new RestfulResult();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, userPassword, false);
        Members members = null;
        try {
            SecurityUtils.getSubject().login(token);
            members = getCurrentUser();
            restfulResult.setData(members);
            restfulResult.setSuccess(true);
        } catch (ServiceException ex) {
            restfulResult.setMsg(ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            restfulResult.setMsg("接口异常");
        }
        return restfulResult;
    }

    @RequestMapping(value = "/checkPhoneUnique", method = RequestMethod.POST)
    @ResponseBody
    public RestfulResult checkPhoneUnique(
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "userPhone", required = true) String userPhone) {
        RestfulResult restfulResult = new RestfulResult();
        try {
            boolean flag = memberService.checkPhoneUnique(userPhone);
            if (!flag) {
                throw new ServiceException("手机号码已存在");
            }
            restfulResult.setSuccess(true);
        } catch (ServiceException ex) {
            restfulResult.setMsg(ex.getMessage());
        } catch (Exception e) {
            restfulResult.setMsg("接口异常");
        }
        return restfulResult;
    }

    private Members getCurrentUser() {
        return (Members) SecurityUtils.getSubject().getPrincipal();
    }

}

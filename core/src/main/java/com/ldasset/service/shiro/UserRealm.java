package com.ldasset.service.shiro;


import com.ldasset.models.Members;
import com.ldasset.service.IMemberService;
import com.ldasset.utils.SecurityHelper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.Sha256CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
public class UserRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private IMemberService memberService;


    public UserRealm() {
        setName("userRealm");
        setCredentialsMatcher(new Sha256CredentialsMatcher());
      /*  setAuthorizationCachingEnabled(true);
        setAuthenticationCachingEnabled(false);
        setCacheManager(new MemoryConstrainedCacheManager());*/
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        if (!(authcToken instanceof UsernamePasswordToken)) {
            return null;
        }
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        Members members = memberService.login(token.getUsername(), SecurityHelper.digestPassword(new String(token.getPassword())));
        if (members != null) {
            return new SimpleAuthenticationInfo(members, members.getUserPass(), getName());
        } else {
            return null;
        }
    }

    //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("begin to get authorization :" + new Date());

        Collection realmCollection = principals.fromRealm(getName());
        if (realmCollection == null || realmCollection.isEmpty()) {
            return null;
        }
        Members members = (Members) realmCollection.iterator().next();
        if (members == null) {
            return null;
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole(Members.class.getSimpleName());

        Collection<String> roles = null;
        Collection<String> permissions = null;
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        logger.info("authorization to complete :" + new Date());

        return info;
    }

}


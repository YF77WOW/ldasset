package com.ldasset.core.service.spring;

import com.github.pagehelper.PageInfo;
import com.ldasset.models.Members;
import com.ldasset.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

@Test
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class MemberServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private IMemberService memberService;

    @Test
    public void testGetByUserPhone() throws Exception {
        Members members = memberService.getByUserPhone("18118300200");
        assertNotNull(members);
    }

    @Test
    public void testFindMembers() throws Exception {
        PageInfo<Members> pageInfo = memberService.findMembers();
        assertNotNull(pageInfo);
    }
}
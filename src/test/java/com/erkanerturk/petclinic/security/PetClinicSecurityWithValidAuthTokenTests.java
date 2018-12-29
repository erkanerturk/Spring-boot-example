package com.erkanerturk.petclinic.security;

import com.erkanerturk.petclinic.model.Owner;
import com.erkanerturk.petclinic.service.PetClinicService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(properties="spring.profiles.active=dev")
public class PetClinicSecurityWithValidAuthTokenTests {

    @Autowired
    private PetClinicService petClinicService;

    @Before
    public void setUp() {
        TestingAuthenticationToken auth = new TestingAuthenticationToken("xxx", "secret","ROLE_USER");
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @After
    public void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    public void testFindOwners() {
        List<Owner> owners = petClinicService.findOwners();
        MatcherAssert.assertThat(owners.size(), Matchers.equalTo(10));
    }
}
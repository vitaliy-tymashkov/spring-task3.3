package com.epam.learn;

import com.epam.learn.model.UserAccount;
import com.epam.learn.service.UserAccountServiceClient;
import com.epam.learn.service.UsersService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(UserAccountServiceClient.class)
public class UserAccountServiceClientTest {

    @Autowired
    private UsersService usersService;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Gson gson;

    @Before
    public void setUp() {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(Long.valueOf("1"));
        userAccount.setName("Ivan");
        userAccount.setPhoneNumber("11111");
        userAccount.setPhoneOperator("Orange");
        userAccount.setBalance(111111);



        String detailsString = gson.toJson(userAccount);



        this.server.expect(requestTo("/user/1"))
                .andRespond(withSuccess(detailsString, MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenCallingGetUserDetails_thenClientMakesCorrectCall() {

        UserAccount userDetails = this.usersService.findUserById("1");

        assertEquals(userDetails.getName(),"Ivan");
    }
}

package com.mapigateway.token;

import com.mapigateway.user.model.MyUser;
import com.mapigateway.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = true)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TokenAPITests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserService users;

    @Test
    public void callAuthenticatedAPI() throws Exception {


        users.createUser(new MyUser(null, "mailuser.com", "usernameUser", "usernameUser", "password", "firstNameU",
                  "lastNameU", "0606060606", "ROLE_USER", 1000.)).subscribe();

        String token = obtainAccessToken("usernameUser", "pass1");

        mockMvc.perform(get("/secured")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content()
                        .contentType("text/plain"))
                .andExpect(content().string("Hi secured home !"));
    }

    @Test
    public void callUnauthenticatedAPI() throws Exception {
        mockMvc.perform(get("/home"))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content()
                        .contentType("text/plain"))
                .andExpect(content().string("Hi home !"));
    }

    private String obtainAccessToken(String username, String password) throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", "userName");
        params.add("username", username);
        params.add("password", password);

        ResultActions result
                = mockMvc.perform(post("api/token")
                        .params(params)
                        .with(httpBasic(username,password)))
                .andExpect(status().isOk());

        return result.andReturn().getResponse().getHeader("Token");
    }
}

package br.com.wallet.controller;

import br.com.wallet.domain.User;
import br.com.wallet.dto.UserDTO;
import br.com.wallet.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class UserControllerTest {

    public static final Long ID = 1050L;
    public static final String NAME = "User Test";
    public static final String PASSWORD = "senha123";
    public static final String EMAIL = "email@teste.com.br";
    public static final String URL = "/user";

    @MockBean
    UserService service;

    @Autowired
    MockMvc mvc;

    @Test
    public void testSave() throws Exception {
        BDDMockito.given(service.save(Mockito.any(User.class))).willReturn(getMockUser());
        mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, NAME, EMAIL, PASSWORD))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").value(ID))
                .andExpect(jsonPath("$.data.name").value(NAME))
                .andExpect(jsonPath("$.data.email").value(EMAIL))
                .andExpect(jsonPath("$.data.password").doesNotExist());
    }

    @Test
    public void testSaveInvalidUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, NAME, "email", PASSWORD))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("Email invalido"));
    }

    public User getMockUser() {
        User user = new User();
        user.setId(ID);
        user.setName(NAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);
        return user;
    }

    public String getJsonPayload(Long id, String name, String email, String password) throws JsonProcessingException {
        UserDTO dto = new UserDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setEmail(email);
        dto.setPassword(password);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(dto);
    }
}
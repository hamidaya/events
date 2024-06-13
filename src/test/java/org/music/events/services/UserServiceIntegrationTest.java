package org.music.events.integrationtests;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.music.events.dtos.UserDto;
import org.music.events.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class UserServiceIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    private UserDto userDto;
    @BeforeEach
    public void setUp() {
        userDto = new UserDto();
        userDto.setUsername("hamid");
        userDto.setPassword("password");
        userDto.setEmail("hamid@voorbeeldje.nl");
        userDto.setEnabled(true);
    }
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void createUser() throws Exception {
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("hamid"));
    }
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void getUser() throws Exception {
        userService.createUser(userDto);
        mockMvc.perform(get("/users/{username}", "hamid")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("hamid"));
    }
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void deleteUser() throws Exception {
        userService.createUser(userDto);
        mockMvc.perform(delete("/users/{username}", "hamid"))
                .andExpect(status().isNoContent());
        mockMvc.perform(get("/users/{username}", "hamid")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void updateUser() throws Exception {
        userService.createUser(userDto);
        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setUsername("hamid");
        updatedUserDto.setPassword("nieuwepassword");
        updatedUserDto.setEmail("newemail@example.com");
        updatedUserDto.setEnabled(true);
        mockMvc.perform(put("/users/{username}", "hamid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUserDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("hamid@voorbeeldje.n"));
    }
}
package ru.urlshortcut.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.urlshortcut.dto.SiteResponseDto;
import ru.urlshortcut.service.SiteService;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc()
class SiteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SiteService siteService;

    @Test
    public void whenRegistrationIsDoneThenGetResponseRegistrationTrueAndLoginPassword() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        SiteResponseDto siteResponseDto = new SiteResponseDto(
                true, "login", "pass");
        when(siteService.siteRegistration(any())).thenReturn(siteResponseDto);
        this.mockMvc.perform(post("/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"site\":\"test.ru\"}")
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(mapper.writeValueAsString(siteResponseDto))));
    }
}

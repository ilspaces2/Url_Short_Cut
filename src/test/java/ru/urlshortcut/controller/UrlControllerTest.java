package ru.urlshortcut.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.urlshortcut.dto.*;
import ru.urlshortcut.service.SiteService;
import ru.urlshortcut.service.UrlService;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UrlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlService urlService;

    @MockBean
    private SiteService siteService;

    @Test
    @WithMockUser
    public void whenUrlRegistrationThenGetCode() throws Exception {
        when(urlService.urlRegistration(any(), any())).thenReturn(new CodeResponseDto("genCode"));
        this.mockMvc.perform(post("/convert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"url\":\"https://test.ru/test\"}")
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"code\":\"genCode\"}")));
    }

    @Test
    public void whenRedirectByCodeThenGetUrl() throws Exception {
        when(urlService.redirectToUrl(any())).thenReturn("someUrl");
        this.mockMvc.perform(get("/redirect/{code}", "code"))
                .andExpect(status().is3xxRedirection())
                .andExpect(content().string(equalTo("someUrl")));
    }

    @Test
    @WithMockUser
    public void whenGetStatistic() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<UrlStatisticResponseDto> urls = List.of(
                new UrlStatisticResponseDto("1 url", 2),
                new UrlStatisticResponseDto("2 url", 0),
                new UrlStatisticResponseDto("3 url", 10)
        );
        when(urlService.urlStatistic(any())).thenReturn(urls);
        this.mockMvc.perform(get("/statistic"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(mapper.writeValueAsString(urls))));
    }
}

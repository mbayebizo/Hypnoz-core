package net.hypnoz.core.services.builder;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import net.hypnoz.core.controller.FonctionsController;
import net.hypnoz.core.dto.FonctionsDto;
import net.hypnoz.core.mapper.EntityMapper;
import net.hypnoz.core.mapper.FonctionsMapper;
import net.hypnoz.core.models.Fonctions;
import net.hypnoz.core.service.FonctionsService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;

@Transactional
public class FonctionsControllerTest {
    /*private static final String ENDPOINT_URL = "/api/fonctions";
    @InjectMocks
    private FonctionsController fonctionsController;
    @Mock
    private FonctionsService fonctionsService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(fonctionsController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<FonctionsDto> page = new PageImpl<>(Collections.singletonList(FonctionsBuilder.getDto()));

        Mockito.when(fonctionsService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(fonctionsService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(fonctionsService);

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(fonctionsService.findById(ArgumentMatchers.anyLong())).thenReturn(FonctionsBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(fonctionsService, Mockito.times(1)).findById("1");
        Mockito.verifyNoMoreInteractions(fonctionsService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(fonctionsService.save(ArgumentMatchers.any(FonctionsDto.class))).thenReturn(FonctionsBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(FonctionsBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(fonctionsService, Mockito.times(1)).save(ArgumentMatchers.any(FonctionsDto.class));
        Mockito.verifyNoMoreInteractions(fonctionsService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(fonctionsService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(FonctionsBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(FonctionsBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(fonctionsService, Mockito.times(1)).update(ArgumentMatchers.any(FonctionsDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(fonctionsService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(fonctionsService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CustomUtils.asJsonString(FonctionsBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(fonctionsService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(fonctionsService);
    }*/
}
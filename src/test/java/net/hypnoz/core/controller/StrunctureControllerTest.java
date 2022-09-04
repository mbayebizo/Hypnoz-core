package net.hypnoz.core.controller;

import net.hypnoz.core.dto.pojo.StructureInitPojo;
import net.hypnoz.core.service.StructuresService;
import net.hypnoz.core.services.builder.CustomUtils;
import net.hypnoz.core.services.builder.StructuresBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;


@Transactional
class StrunctureControllerTest {
    private static final String ENDPOINT_URL="/structures";

    @InjectMocks
    private StructuresController structuresController;
    @Mock
    private StructuresService structuresService;
    private MockMvc mockMvc;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(structuresController)
                .build();
    }

    @Test
    void initSysteme() throws Exception {
        Mockito.when(structuresService.initConfigStructure(ArgumentMatchers.any(StructureInitPojo.class))).thenReturn(StructuresBuilder.getStructureInitPojo());

        mockMvc.perform(
                MockMvcRequestBuilders.post(ENDPOINT_URL+"/initSysteme")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CustomUtils.asJsonString(StructuresBuilder.getStrPojo())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(structuresService,Mockito.times(1)).initConfigStructure(ArgumentMatchers.any(StructureInitPojo.class));
        Mockito.verifyNoMoreInteractions(structuresService);
    }

}

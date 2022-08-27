package net.hypnoz.core.services.builder;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class FonctionsControllerTest {
   /* private static final String ENDPOINT_URL = "/api/fonctions";
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
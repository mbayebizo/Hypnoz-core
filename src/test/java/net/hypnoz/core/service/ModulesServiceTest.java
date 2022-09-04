package net.hypnoz.core.service;

import net.hypnoz.core.dto.ModulesDto;
import net.hypnoz.core.mapper.ModulesMapper;
import net.hypnoz.core.models.Modules;
import net.hypnoz.core.repository.ModulesRepository;
import net.hypnoz.core.repository.ModulesStructureRepository;
import net.hypnoz.core.repository.StructuresRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ModulesServiceTest {

    @Mock
    private ModulesRepository mockRepository;
    @Mock
    private StructuresRepository structuresRepository;
    @Mock
    private ModulesMapper mockModulesMapper;
    @Mock private ModulesStructureRepository modulesStructureRepository;

    private ModulesService modulesServiceUnderTest;

    @BeforeEach
    void setUp() {
        modulesServiceUnderTest = new ModulesService(mockRepository, mockModulesMapper, modulesStructureRepository);
    }

    @Test
    void testSave() {
        // Setup
        final ModulesDto modulesDto = ModulesDto.builder()
                .code("M0")
                .libCode("Administration")
                .build();
        final ModulesDto expectedResult = ModulesDto.builder()
                .code("M0")
                .libCode("Administration")
                .build();
        when(mockModulesMapper.toEntity(ModulesDto.builder()
                .code("M0")
                .libCode("Administration")
                .build())).thenReturn(new Modules());
        when(mockRepository.save(any(Modules.class))).thenReturn(new Modules());
        when(mockModulesMapper.toDto(any(Modules.class))).thenReturn(ModulesDto.builder()
                .code("M0")
                .libCode("Administration")
                .build());

        // Run the test
        final ModulesDto result = modulesServiceUnderTest.save(modulesDto);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDeleteById() {
        // Setup
        // Run the test
        modulesServiceUnderTest.deleteById(1L);

        // Verify the results
        verify(mockRepository).deleteById(1L);
    }

    @Test
    void testFindById() {
        // Setup
        final ModulesDto expectedResult = ModulesDto.builder()
                .code("M0")
                .libCode("Administration")
                .build();
        when(mockRepository.findById(1L)).thenReturn(Optional.of(new Modules()));
        when(mockModulesMapper.toDto(any(Modules.class))).thenReturn(ModulesDto.builder()
                .code("M0")
                .libCode("Administration")
                .build());

        // Run the test
        final ModulesDto result = modulesServiceUnderTest.findById(1L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_ModulesRepositoryReturnsAbsent() {
        // Setup
        when(mockRepository.findById(1L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> modulesServiceUnderTest.findById(1L)).isInstanceOf(ResourceNotFoundException.class);
    }



    @Test
    void testUpdate() {
        // Setup
        final ModulesDto modulesDto = ModulesDto.builder()
                .id(1L)
                .code("M0")
                .libCode("Administration")
                .build();
        final ModulesDto expectedResult = ModulesDto.builder()
                .id(1L)
                .code("M0")
                .libCode("Administration")
                .build();
        when(mockRepository.findById(1L)).thenReturn(Optional.of(new Modules()));
        when(mockModulesMapper.toDto(any(Modules.class))).thenReturn(ModulesDto.builder()
                .id(1L)
                .code("M0")
                .libCode("Administration")
                .build());
        when(mockModulesMapper.toEntity(ModulesDto.builder()
                .id(1L)
                .code("M0")
                .libCode("Administration")
                .build())).thenReturn(new Modules());
        when(mockRepository.save(any(Modules.class))).thenReturn(new Modules());

        // Run the test
        final ModulesDto result = modulesServiceUnderTest.update(modulesDto, 1L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdate_ModulesRepositoryFindByIdReturnsAbsent() {
        // Setup
        final ModulesDto modulesDto = ModulesDto.builder()
                .id(1L)
                .code("M0")
                .libCode("Administration")
                .build();
        when(mockRepository.findById(1L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> modulesServiceUnderTest.update(modulesDto, 1L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void initModule(){

    }
}

package net.hypnoz.core.service;

import net.hypnoz.core.dto.StructuresDto;
import net.hypnoz.core.mapper.StructuresMapper;
import net.hypnoz.core.models.Structures;
import net.hypnoz.core.repository.StructuresRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StructuresServiceTest {

    @Mock
    private StructuresRepository mockRepository;
    @Mock
    private StructuresMapper mockStructuresMapper;

    private StructuresService structuresServiceUnderTest;

    @BeforeEach
    void setUp() {
        structuresServiceUnderTest = new StructuresService(mockRepository, mockStructuresMapper);
    }

    @Test
    void testSave() {
        // Setup
        final StructuresDto structuresDto = StructuresDto.builder()
                .sigle("teste")
                .raisonSocial("raison social")
                .build();
        final StructuresDto expectedResult = StructuresDto.builder()
                .sigle("teste")
                .raisonSocial("raison social")
                .build();
        when(mockStructuresMapper.toEntity(StructuresDto.builder()
                .sigle("teste")
                .raisonSocial("raison social")
                .build())).thenReturn(Structures.builder().build());
        when(mockRepository.save(Structures.builder()
                        .sigle("teste")
                        .raisonSocial("raison social")
                .build())).thenReturn(Structures.builder()
                .sigle("teste")
                .raisonSocial("raison social")
                .build());
        when(mockStructuresMapper.toDto(Structures.builder()
                .sigle("teste")
                .raisonSocial("raison social")
                .build())).thenReturn(StructuresDto.builder()
                .sigle("teste")
                .raisonSocial("raison social")
                .build());

        // Run the test
        final StructuresDto result = structuresServiceUnderTest.save(structuresDto).getBody();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDeleteById() {
        // Setup
        // Run the test
        structuresServiceUnderTest.deleteById(1L);

        // Verify the results
        verify(mockRepository).deleteById(1L);
    }

    @Test
    void testFindById() {
        // Setup
        final StructuresDto expectedResult = StructuresDto.builder().build();

        // Configure StructuresRepository.findById(...).
        final Optional<Structures> structures = Optional.of(Structures.builder().build());
        when(mockRepository.findById(1L)).thenReturn(structures);

        when(mockStructuresMapper.toDto(Structures.builder().build())).thenReturn(StructuresDto.builder().build());

        // Run the test
        final StructuresDto result = structuresServiceUnderTest.findById(1L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_StructuresRepositoryReturnsAbsent() {
        // Setup
        when(mockRepository.findById(1L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> structuresServiceUnderTest.findById(1L)).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void testFindByCondition() {
        // Setup
        final StructuresDto structuresDto = StructuresDto.builder().build();

        // Configure StructuresRepository.findAll(...).
        final Page<Structures> structures = new PageImpl<>(List.of(Structures.builder().build()));
        when(mockRepository.findAll(any(Pageable.class))).thenReturn(structures);

        when(mockStructuresMapper.toDto(List.of(Structures.builder().build())))
                .thenReturn(List.of(StructuresDto.builder().build()));

        // Run the test
        final Page<StructuresDto> result = structuresServiceUnderTest.findByCondition(structuresDto,
                PageRequest.of(0, 1));

        // Verify the results
    }



    @Test
    void testFindByCondition_StructuresMapperReturnsNoItems() {
        // Setup
        final StructuresDto structuresDto = StructuresDto.builder().build();

        // Configure StructuresRepository.findAll(...).
        final Page<Structures> structures = new PageImpl<>(List.of(Structures.builder().build()));
        when(mockRepository.findAll(any(Pageable.class))).thenReturn(structures);

        when(mockStructuresMapper.toDto(List.of(Structures.builder().build()))).thenReturn(Collections.emptyList());

        // Run the test
        final Page<StructuresDto> result = structuresServiceUnderTest.findByCondition(structuresDto,
                PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    void testUpdate() {
        // Setup
        final StructuresDto structuresDto = StructuresDto.builder()
                .sigle("teste")
                .raisonSocial("raison social")
                .build();
        final StructuresDto expectedResult = StructuresDto.builder()
                .sigle("teste")
                .raisonSocial("raison social")
                .build();

        // Configure StructuresRepository.findById(...).
        final Optional<Structures> structures = Optional.of(Structures.builder()
                .sigle("teste")
                .raisonSocial("raison social")
                .build());
        when(mockRepository.findById(1L)).thenReturn(structures);

        when(mockStructuresMapper.toDto(Structures.builder()
                .sigle("teste")
                .raisonSocial("raison social")
                .build())).thenReturn(StructuresDto.builder()
                .sigle("teste")
                .raisonSocial("raison social")
                .build());
        when(mockStructuresMapper.toEntity(StructuresDto.builder()
                .sigle("teste")
                .raisonSocial("raison social")
                .build())).thenReturn(Structures.builder().build());
        when(mockRepository.save(Structures.builder().build())).thenReturn(Structures.builder()
                .sigle("teste")
                .raisonSocial("raison social")
                .build());

        // Run the test
        final StructuresDto result = structuresServiceUnderTest.update(structuresDto, 1L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdate_StructuresRepositoryFindByIdReturnsAbsent() {
        // Setup
        final StructuresDto structuresDto = StructuresDto.builder().build();
        when(mockRepository.findById(1L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> structuresServiceUnderTest.update(structuresDto, 1L))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}

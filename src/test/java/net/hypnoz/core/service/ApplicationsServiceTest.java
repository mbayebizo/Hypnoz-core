package net.hypnoz.core.service;

import net.hypnoz.core.dto.ApplicationsDto;
import net.hypnoz.core.mapper.ApplicationsMapper;
import net.hypnoz.core.models.Applications;
import net.hypnoz.core.repository.ApplicationsRepository;
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
class ApplicationsServiceTest {

    @Mock
    private ApplicationsRepository mockRepository;
    @Mock
    private ApplicationsMapper mockApplicationsMapper;

    private ApplicationsService applicationsServiceUnderTest;

    @BeforeEach
    void setUp() {
        applicationsServiceUnderTest = new ApplicationsService(mockRepository, mockApplicationsMapper);
    }

    @Test
    void testSave() {
        // Setup
        final ApplicationsDto applicationsDto = ApplicationsDto.builder().build();
        final ApplicationsDto expectedResult = ApplicationsDto.builder().build();
        when(mockApplicationsMapper.toEntity(ApplicationsDto.builder().build()))
                .thenReturn(Applications.builder().build());
        when(mockRepository.save(any(Applications.class))).thenReturn(Applications.builder().build());
        when(mockApplicationsMapper.toDto(any(Applications.class))).thenReturn(ApplicationsDto.builder().build());

        // Run the test
        final ApplicationsDto result = applicationsServiceUnderTest.save(applicationsDto);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDeleteById() {
        // Setup
        // Run the test
        applicationsServiceUnderTest.deleteById(0L);

        // Verify the results
        verify(mockRepository).deleteById(0L);
    }

    @Test
    void testFindById() {
        // Setup
        final ApplicationsDto expectedResult = ApplicationsDto.builder().build();

        // Configure ApplicationsRepository.findById(...).
        final Optional<Applications> applications = Optional.of(Applications.builder().build());
        when(mockRepository.findById(0L)).thenReturn(applications);

        when(mockApplicationsMapper.toDto(any(Applications.class))).thenReturn(ApplicationsDto.builder().build());

        // Run the test
        final ApplicationsDto result = applicationsServiceUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_ApplicationsRepositoryReturnsAbsent() {
        // Setup
        when(mockRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> applicationsServiceUnderTest.findById(0L))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void testFindByCondition() {
        // Setup
        final ApplicationsDto applicationsDto = ApplicationsDto.builder().build();

        // Configure ApplicationsRepository.findAll(...).
        final Page<Applications> applications = new PageImpl<>(List.of(Applications.builder().build()));
        when(mockRepository.findAll(any(Pageable.class))).thenReturn(applications);

        // Configure ApplicationsMapper.toDto(...).
        final List<ApplicationsDto> applicationsDtos = List.of(ApplicationsDto.builder().build());
        when(mockApplicationsMapper.toDto(List.of(Applications.builder().build()))).thenReturn(applicationsDtos);

        // Run the test
        final Page<ApplicationsDto> result = applicationsServiceUnderTest.findByCondition(applicationsDto,
                PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    void testFindByCondition_ApplicationsRepositoryReturnsNoItems() {
        // Setup
        final ApplicationsDto applicationsDto = ApplicationsDto.builder().build();
        when(mockRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Configure ApplicationsMapper.toDto(...).
        final List<ApplicationsDto> applicationsDtos = List.of(ApplicationsDto.builder().build());
        when(mockApplicationsMapper.toDto(List.of(Applications.builder().build()))).thenReturn(applicationsDtos);

        // Run the test
        final Page<ApplicationsDto> result = applicationsServiceUnderTest.findByCondition(applicationsDto,
                PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    void testFindByCondition_ApplicationsMapperReturnsNoItems() {
        // Setup
        final ApplicationsDto applicationsDto = ApplicationsDto.builder().build();

        // Configure ApplicationsRepository.findAll(...).
        final Page<Applications> applications = new PageImpl<>(List.of(Applications.builder().build()));
        when(mockRepository.findAll(any(Pageable.class))).thenReturn(applications);

        when(mockApplicationsMapper.toDto(List.of(Applications.builder().build()))).thenReturn(Collections.emptyList());

        // Run the test
        final Page<ApplicationsDto> result = applicationsServiceUnderTest.findByCondition(applicationsDto,
                PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    void testUpdate() {
        // Setup
        final ApplicationsDto applicationsDto = ApplicationsDto.builder().build();
        final ApplicationsDto expectedResult = ApplicationsDto.builder().build();

        // Configure ApplicationsRepository.findById(...).
        final Optional<Applications> applications = Optional.of(Applications.builder().build());
        when(mockRepository.findById(0L)).thenReturn(applications);

        when(mockApplicationsMapper.toDto(any(Applications.class))).thenReturn(ApplicationsDto.builder().build());
        when(mockApplicationsMapper.toEntity(ApplicationsDto.builder().build()))
                .thenReturn(Applications.builder().build());
        when(mockRepository.save(any(Applications.class))).thenReturn(Applications.builder().build());

        // Run the test
        final ApplicationsDto result = applicationsServiceUnderTest.update(applicationsDto, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdate_ApplicationsRepositoryFindByIdReturnsAbsent() {
        // Setup
        final ApplicationsDto applicationsDto = ApplicationsDto.builder().build();
        when(mockRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> applicationsServiceUnderTest.update(applicationsDto, 0L))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}

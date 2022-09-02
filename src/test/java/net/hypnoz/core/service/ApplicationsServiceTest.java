package net.hypnoz.core.service;

import net.hypnoz.core.dto.ApplicationsDto;
import net.hypnoz.core.mapper.ApplicationsMapper;
import net.hypnoz.core.models.Applications;
import net.hypnoz.core.repository.ApplicationsRepository;
import net.hypnoz.core.services.builder.ApplicationsBuilder;
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
class ApplicationsServiceTest {

    @Mock
    private ApplicationsRepository mockRepository;
    @Mock
    private ApplicationsMapper mockApplicationsMapper;

    private ApplicationsService applicationsServiceUnderTest;

    @BeforeEach
    void setUp() {
        applicationsServiceUnderTest = new ApplicationsService(mockRepository, mockApplicationsMapper, modulesRepository);
    }

    @Test
    void testSave() {
        // Setup
        final ApplicationsDto applicationsDto = ApplicationsBuilder.getDto();
        final ApplicationsDto expectedResult = ApplicationsBuilder.getDto();
        when(mockApplicationsMapper.toEntity(ApplicationsBuilder.getDto()))
                .thenReturn(Applications.builder().build());
        when(mockRepository.save(any(Applications.class))).thenReturn(Applications.builder().build());
        when(mockApplicationsMapper.toDto(any(Applications.class))).thenReturn(ApplicationsBuilder.getDto());

        // Run the test
        final ApplicationsDto result = applicationsServiceUnderTest.save(applicationsDto);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDeleteById() {
        // Setup
        // Run the test
        applicationsServiceUnderTest.deleteById(1L);

        // Verify the results
        verify(mockRepository).deleteById(1L);
    }


    @Test
    void testFindById_ApplicationsRepositoryReturnsAbsent() {
        // Setup
        when(mockRepository.findById(1L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> applicationsServiceUnderTest.findById(1L))
                .isInstanceOf(ResourceNotFoundException.class);
    }





    @Test
    void testUpdate_ApplicationsRepositoryFindByIdReturnsAbsent() {
        // Setup
        final ApplicationsDto applicationsDto = ApplicationsBuilder.getDto();
        when(mockRepository.findById(1L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> applicationsServiceUnderTest.update(applicationsDto, 1L))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}

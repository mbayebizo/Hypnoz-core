package net.hypnoz.core.service;

import net.hypnoz.core.dto.GroupesDto;
import net.hypnoz.core.mapper.GroupesMapper;
import net.hypnoz.core.models.Groupes;
import net.hypnoz.core.repository.GroupesRepository;
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
class GroupesServiceTest {

    @Mock
    private GroupesRepository mockRepository;
    @Mock
    private GroupesMapper mockGroupesMapper;

    private GroupesService groupesServiceUnderTest;

    @BeforeEach
    void setUp() {
        groupesServiceUnderTest = new GroupesService(mockRepository, mockGroupesMapper);
    }

    @Test
    void testSave() {
        // Setup
        final GroupesDto groupesDto = GroupesDto.builder().build();
        final GroupesDto expectedResult = GroupesDto.builder().build();
        when(mockGroupesMapper.toEntity(GroupesDto.builder().build())).thenReturn(Groupes.builder().build());
        when(mockRepository.save(Groupes.builder().build())).thenReturn(Groupes.builder().build());
        when(mockGroupesMapper.toDto(Groupes.builder().build())).thenReturn(GroupesDto.builder().build());

        // Run the test
        final GroupesDto result = groupesServiceUnderTest.save(groupesDto);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDeleteById() {
        // Setup
        // Run the test
        groupesServiceUnderTest.deleteById(0L);

        // Verify the results
        verify(mockRepository).deleteById(0L);
    }

    @Test
    void testFindById() {
        // Setup
        final GroupesDto expectedResult = GroupesDto.builder().build();
        when(mockRepository.findById(0L)).thenReturn(Optional.of(Groupes.builder().build()));
        when(mockGroupesMapper.toDto(Groupes.builder().build())).thenReturn(GroupesDto.builder().build());

        // Run the test
        final GroupesDto result = groupesServiceUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_GroupesRepositoryReturnsAbsent() {
        // Setup
        when(mockRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> groupesServiceUnderTest.findById(0L)).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void testFindByCondition() {
        // Setup
        final GroupesDto groupesDto = GroupesDto.builder().build();

        // Configure GroupesRepository.findAll(...).
        final Page<Groupes> groupes = new PageImpl<>(List.of(Groupes.builder().build()));
        when(mockRepository.findAll(any(Pageable.class))).thenReturn(groupes);

        when(mockGroupesMapper.toDto(List.of(Groupes.builder().build())))
                .thenReturn(List.of(GroupesDto.builder().build()));

        // Run the test
        final Page<GroupesDto> result = groupesServiceUnderTest.findByCondition(groupesDto, PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    void testFindByCondition_GroupesRepositoryReturnsNoItems() {
        // Setup
        final GroupesDto groupesDto = GroupesDto.builder().build();
        when(mockRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));
        when(mockGroupesMapper.toDto(List.of(Groupes.builder().build())))
                .thenReturn(List.of(GroupesDto.builder().build()));

        // Run the test
        final Page<GroupesDto> result = groupesServiceUnderTest.findByCondition(groupesDto, PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    void testFindByCondition_GroupesMapperReturnsNoItems() {
        // Setup
        final GroupesDto groupesDto = GroupesDto.builder().build();

        // Configure GroupesRepository.findAll(...).
        final Page<Groupes> groupes = new PageImpl<>(List.of(Groupes.builder().build()));
        when(mockRepository.findAll(any(Pageable.class))).thenReturn(groupes);

        when(mockGroupesMapper.toDto(List.of(Groupes.builder().build()))).thenReturn(Collections.emptyList());

        // Run the test
        final Page<GroupesDto> result = groupesServiceUnderTest.findByCondition(groupesDto, PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    void testUpdate() {
        // Setup
        final GroupesDto groupesDto = GroupesDto.builder().build();
        final GroupesDto expectedResult = GroupesDto.builder().build();
        when(mockRepository.findById(0L)).thenReturn(Optional.of(Groupes.builder().build()));
        when(mockGroupesMapper.toDto(Groupes.builder().build())).thenReturn(GroupesDto.builder().build());
        when(mockGroupesMapper.toEntity(GroupesDto.builder().build())).thenReturn(Groupes.builder().build());
        when(mockRepository.save(Groupes.builder().build())).thenReturn(Groupes.builder().build());

        // Run the test
        final GroupesDto result = groupesServiceUnderTest.update(groupesDto, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdate_GroupesRepositoryFindByIdReturnsAbsent() {
        // Setup
        final GroupesDto groupesDto = GroupesDto.builder().build();
        when(mockRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> groupesServiceUnderTest.update(groupesDto, 0L))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}

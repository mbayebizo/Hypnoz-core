package net.hypnoz.core.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.hypnoz.core.dto.GroupesDto;
import net.hypnoz.core.service.GroupesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/groupes")
@RestController
@Slf4j
@Api("groupes")
public class GroupesController {
    private final GroupesService groupesService;

    public GroupesController(GroupesService groupesService) {
        this.groupesService = groupesService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated GroupesDto groupesDto) {
        groupesService.save(groupesDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupesDto> findById(@PathVariable("id") Long id) {
        GroupesDto groupes = groupesService.findById(id);
        return ResponseEntity.ok(groupes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(groupesService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new ResourceNotFoundException();
        });
        groupesService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<GroupesDto>> pageQuery(GroupesDto groupesDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<GroupesDto> groupesPage = groupesService.findByCondition(groupesDto, pageable);
        return ResponseEntity.ok(groupesPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated GroupesDto groupesDto, @PathVariable("id") Long id) {
        groupesService.update(groupesDto, id);
        return ResponseEntity.ok().build();
    }
}
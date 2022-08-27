package net.hypnoz.core.controller;


import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.hypnoz.core.dto.StructuresDto;
import net.hypnoz.core.service.StructuresService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/structures")
@RestController
@Slf4j
@Api("structures")
public class StructuresController {
    private final StructuresService structuresService;

    public StructuresController(StructuresService structuresService) {
        this.structuresService = structuresService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated StructuresDto structuresDto) {
        structuresService.save(structuresDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StructuresDto> findById(@PathVariable("id") Long id) {
        StructuresDto structures = structuresService.findById(id);
        return ResponseEntity.ok(structures);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(structuresService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new ResourceNotFoundException();
        });
        structuresService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<StructuresDto>> pageQuery(StructuresDto structuresDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<StructuresDto> structuresPage = structuresService.findByCondition(structuresDto, pageable);
        return ResponseEntity.ok(structuresPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated StructuresDto structuresDto, @PathVariable("id") Long id) {
        structuresService.update(structuresDto, id);
        return ResponseEntity.ok().build();
    }
}
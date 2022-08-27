package net.hypnoz.core.controller;


import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.hypnoz.core.dto.ApplicationsDto;
import net.hypnoz.core.service.ApplicationsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/applications")
@RestController
@Slf4j
@Api("applications")
public class ApplicationsController {
    private final ApplicationsService applicationsService;

    public ApplicationsController(ApplicationsService applicationsService) {
        this.applicationsService = applicationsService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated ApplicationsDto applicationsDto) {
        applicationsService.save(applicationsDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationsDto> findById(@PathVariable("id") Long id) {
        ApplicationsDto applications = applicationsService.findById(id);
        return ResponseEntity.ok(applications);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(applicationsService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new ResourceNotFoundException();
        });
        applicationsService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<ApplicationsDto>> pageQuery(ApplicationsDto applicationsDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ApplicationsDto> applicationsPage = applicationsService.findByCondition(applicationsDto, pageable);
        return ResponseEntity.ok(applicationsPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated ApplicationsDto applicationsDto, @PathVariable("id") Long id) {
        applicationsService.update(applicationsDto, id);
        return ResponseEntity.ok().build();
    }
}
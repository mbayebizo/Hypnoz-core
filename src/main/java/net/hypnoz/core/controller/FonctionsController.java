package net.hypnoz.core.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.hypnoz.core.dto.FonctionsDto;
import net.hypnoz.core.service.FonctionsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/fonctions")
@RestController
@Slf4j
@Api("fonctions")
public class FonctionsController {
    private final FonctionsService fonctionsService;

    public FonctionsController(FonctionsService fonctionsService) {
        this.fonctionsService = fonctionsService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated FonctionsDto fonctionsDto) {
        fonctionsService.save(fonctionsDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FonctionsDto> findById(@PathVariable("id") Long id) {
        FonctionsDto fonctions = fonctionsService.findById(id);
        return ResponseEntity.ok(fonctions);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try{
            fonctionsService.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (ResourceNotFoundException e){
            log.error("Unable to delete non-existent data！");
            throw new ResourceNotFoundException();
        }
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<FonctionsDto>> pageQuery(FonctionsDto fonctionsDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<FonctionsDto> fonctionsPage = fonctionsService.findByCondition(fonctionsDto, pageable);
        return ResponseEntity.ok(fonctionsPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated FonctionsDto fonctionsDto, @PathVariable("id") Long id) {
        fonctionsService.update(fonctionsDto, id);
        return ResponseEntity.ok().build();
    }
}
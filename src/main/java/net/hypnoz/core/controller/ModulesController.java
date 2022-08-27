package net.hypnoz.core.controller;


import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.hypnoz.core.dto.ModulesDto;
import net.hypnoz.core.service.ModulesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/modules")
@RestController
@Slf4j
@Api("modules")
public class ModulesController {
    private final ModulesService modulesService;

    public ModulesController(ModulesService modulesService) {
        this.modulesService = modulesService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated ModulesDto modulesDto) {
        modulesService.save(modulesDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModulesDto> findById(@PathVariable("id") Long id) {
        ModulesDto modules = modulesService.findById(id);
        return ResponseEntity.ok(modules);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try{
            modulesService.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (ResourceNotFoundException e){
            log.error("Unable to delete non-existent data！");
            throw new ResourceNotFoundException();
        }
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<ModulesDto>> pageQuery(ModulesDto modulesDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ModulesDto> modulesPage = modulesService.findByCondition(modulesDto, pageable);
        return ResponseEntity.ok(modulesPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated ModulesDto modulesDto, @PathVariable("id") Long id) {
        modulesService.update(modulesDto, id);
        return ResponseEntity.ok().build();
    }
}
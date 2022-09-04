package net.hypnoz.core.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.hypnoz.core.dto.GroupesDto;
import net.hypnoz.core.service.GroupesService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        try{
            groupesService.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (ResourceNotFoundException e){
            log.error("Unable to delete non-existent data！");
            throw new ResourceNotFoundException();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated GroupesDto groupesDto, @PathVariable("id") Long id) {
        groupesService.update(groupesDto, id);
        return ResponseEntity.ok().build();
    }
}
package net.hypnoz.core.controller;


import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.hypnoz.core.dto.ApplicationsDto;
import net.hypnoz.core.service.ApplicationsService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
       try{
           applicationsService.deleteById(id);
           return ResponseEntity.ok().build();
       }catch (ResourceNotFoundException e){
           log.error("Unable to delete non-existent data！");
            throw new ResourceNotFoundException();
       }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated ApplicationsDto applicationsDto, @PathVariable("id") Long id) {
        applicationsService.update(applicationsDto, id);
        return ResponseEntity.ok().build();
    }
}
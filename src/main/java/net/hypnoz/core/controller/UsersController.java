package net.hypnoz.core.controller;


import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.hypnoz.core.dto.UsersDto;
import net.hypnoz.core.service.UsersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
@Slf4j
@Api("users")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated UsersDto usersDto) {
        usersService.save(usersDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersDto> findById(@PathVariable("id") Long id) {
        UsersDto users = usersService.findById(id);
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            usersService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            log.error("Unable to delete non-existent data！");
            throw new ResourceNotFoundException();
        }
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<UsersDto>> pageQuery(UsersDto usersDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<UsersDto> usersPage = usersService.findByCondition(usersDto, pageable);
        return ResponseEntity.ok(usersPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated UsersDto usersDto, @PathVariable("id") Long id) {
        usersService.update(usersDto, id);
        return ResponseEntity.ok().build();
    }
}
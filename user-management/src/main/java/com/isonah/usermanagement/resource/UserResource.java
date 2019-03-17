package com.isonah.usermanagement.resource;

import com.isonah.usermanagement.mapper.UserMapper;
import com.isonah.usermanagement.model.MystoryUser;
import com.isonah.usermanagement.model.UserDTO;
import com.isonah.usermanagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * @author ielksseyer
 */
@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger(UserResource.class);

    @PostMapping()
    public Mono<ResponseEntity<MystoryUser>> create(@RequestBody UserDTO userDTO) {
        return Mono.just(userDTO)
                   .map(UserMapper::map)
                   .map(u -> userService.create(u))
                   .map(u -> new ResponseEntity<MystoryUser>(UserMapper.map(userDTO), CREATED));
    }

    @GetMapping()
    public Flux<MystoryUser> find(Principal principal) {
        logger.info("getting user {}", principal);
        return userService.find();
    }

    @GetMapping("/{id}")
    public Mono<MystoryUser> findById(@PathVariable String id) {
        return userService.findById(id);
    }
}

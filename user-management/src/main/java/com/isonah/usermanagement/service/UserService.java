package com.isonah.usermanagement.service;

import com.isonah.usermanagement.exception.MyStoryException;
import com.isonah.usermanagement.model.MystoryUser;
import com.isonah.usermanagement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author ielksseyer
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public synchronized Mono<MystoryUser> create(MystoryUser user) {
        logger.info("inserting {}", user);
        return Mono.just(user)
                   .map(u -> userRepository.findById(u.getUsername()))
                   .filter(u -> !u.isPresent())
                   .switchIfEmpty(Mono.error(new MyStoryException(format("The user %s already exists", user.getUsername()), CONFLICT)))
                   .map(u -> userRepository.save(user));

    }

    public Flux<MystoryUser> find() {
        return Flux.fromIterable(userRepository.findAll());
    }

    public Mono<MystoryUser> findById(String id) {
        return Mono.just(id)
                   .doOnSuccess(i -> logger.info("finding user with id {}", i))
                   .map(i -> userRepository.findById(i))
                   .flatMap(i -> i.map(Mono::just).orElseGet(Mono::empty))
                   .switchIfEmpty(Mono.error(new MyStoryException(format("The user %s does not exist", id), NOT_FOUND)))
                   .doOnSuccess(s -> logger.debug("user {} retrieved with id {} ", s, id));
    }
}

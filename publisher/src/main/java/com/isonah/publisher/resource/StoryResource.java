package com.isonah.publisher.resource;

import com.isonah.publisher.model.entity.Story;
import com.isonah.publisher.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.isonah.publisher.Constants.REST_TOPIC_PATH_BASE;

/**
 * @author ielksseyer
 */
@RestController
@RequestMapping(REST_TOPIC_PATH_BASE)
public class StoryResource {

    @Autowired
    StoryService storyService;

    @PostMapping()
    public Story create(Story story) {
        return Optional.ofNullable(story)
                       .map(s -> storyService.pushStory(s))
                       .orElse(null);
    }
    //TODO webflux implementation,
    // Todo Kotlin implentation,
    //tODO reactor implementaion.

}

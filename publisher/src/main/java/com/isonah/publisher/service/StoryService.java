package com.isonah.publisher.service;

import com.isonah.publisher.model.entity.Story;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.isonah.publisher.Constants.KAFKA_TOPIC_NAME;

/**
 * @author ielksseyer
 */
@Service
public class StoryService {

    @Autowired
    private KafkaTemplate<String, Story> kafkaTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(StoryService.class);

    public Story pushStory(Story story) {
        LOGGER.info("sending object {} to kafka", story);
        return Optional.ofNullable(story)
                       .map(this::sendMessage)
                       .orElse(null);

    }

    private Story sendMessage(Story story) {
        return Optional.ofNullable(story)
                       .map(s -> kafkaTemplate.send(KAFKA_TOPIC_NAME, story))
                       .map(s -> story)
                       .orElse(null);
    }
}

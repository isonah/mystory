package com.isonah.mystory.service

import com.isonah.mystory.model.entity.Story
import com.isonah.mystory.repository.StoryRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono


/**
 *
 *
 * @author ielksseyer
 */

@Service
class StoryService {

    val logger = LoggerFactory.getLogger(StoryService::class.java)

    @Autowired
    lateinit var storyRepository: StoryRepository

    fun create(story: Story): Mono<Story> {
        return story.toMono()
                .doOnNext { logger.info("creating entry for {}", it) }
                .map { storyRepository.save(it) }
                .doOnError { logger.error("could not save {} to database", it) }
                .doOnSuccess { logger.debug("entry {} successfly added", it)
                        .to}
    }

    fun findAll() = storyRepository.findAll().toFlux()
}
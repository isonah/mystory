package com.isonah.mystory.resource

import com.isonah.mystory.model.entity.Story
import com.isonah.mystory.model.entity.StoryDTO
import com.isonah.mystory.service.StoryService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 *
 *
 * @author ielksseyer
 */
@RestController
@RequestMapping("/stories")
class StoryRessource {

    @Autowired
    lateinit var storyService: StoryService


    @PostMapping
    fun create(@RequestBody story: StoryDTO): Mono<Story> {
        val st = Story(ObjectId(story.id), story.content)
        return storyService.create(st)
    }

    @GetMapping
    fun findAll(): Flux<Story> {
        return storyService.findAll()
    }


}
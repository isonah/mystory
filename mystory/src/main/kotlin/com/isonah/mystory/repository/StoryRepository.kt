package com.isonah.mystory.repository

import com.isonah.mystory.model.entity.Story
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 *
 *
 * @author ielksseyer
 */

@Repository
interface StoryRepository : MongoRepository<Story, ObjectId> {

}

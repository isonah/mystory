package com.isonah.mystory.model.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 *
 *
 * @author ielksseyer
 */

@Document
data class Story(@Id var id: ObjectId, var content: String)
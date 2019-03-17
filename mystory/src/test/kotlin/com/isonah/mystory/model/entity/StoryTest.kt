package com.isonah.mystory.model.entity

import org.assertj.core.api.Assertions.assertThat
import org.bson.types.ObjectId

/**
 * @author ielksseyer
 */
class StoryTest {

    val contentTest = "This a the content of my story!"
    val idTest = ObjectId()

    lateinit var story: Story

    @Before
    fun initializeTest() {
        story = Story(idTest, contentTest)
    }

    @Test
    fun `function getId should return id setted juste before`() {
        assertThat(story.id).isEqualTo(idTest)
    }

    @Test
    fun `function setId should set id`() {
        val id = ObjectId()
        story.id = id
        assertThat(story.id).isEqualTo(id)

    }

    @Test
    fun getContent() {
    }

    @Test
    fun setContent() {
    }


}

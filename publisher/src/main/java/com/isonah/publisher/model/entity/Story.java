package com.isonah.publisher.model.entity;

import java.util.Objects;

/**
 * @author ielksseyer
 */
public class Story {

    private String id;

    private String content;

    public String getId() {
        return id;
    }

    public Story setId(String id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Story setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Story{");
        sb.append("id='").append(id).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Story)) {
            return false;
        }
        Story story = (Story) o;
        return Objects.equals(getId(), story.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

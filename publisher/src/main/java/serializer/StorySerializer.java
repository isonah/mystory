package serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isonah.publisher.model.entity.Story;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * @author ielksseyer
 */
public class StorySerializer implements Serializer<Story> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, Story data) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(data).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;

    }

    @Override
    public void close() {
    }
}

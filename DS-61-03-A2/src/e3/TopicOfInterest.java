package e3;

import java.util.Objects;

public class TopicOfInterest {

    String topic;

    public TopicOfInterest(String topic) {
        this.topic = topic;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TopicOfInterest that)) return false;
        return Objects.equals(topic, that.topic);
    }

    @Override
    public String toString() {
        return topic;
    }
}

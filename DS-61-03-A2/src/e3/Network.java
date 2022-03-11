package e3;

import java.util.ArrayList;
import java.util.List;

public class Network {

    NetworkManager manager;

    public Network(NetworkManager manager) {
        this.manager = manager;
    }

    void addUser(String user, String... values){

        List<TopicOfInterest> topicList= new ArrayList<>();

        if (user==null || values.length<1 )
            throw new IllegalArgumentException();

        for (String value : values) {

            if (value==null) throw new IllegalArgumentException();
            topicList.add(new TopicOfInterest(value));
        }

        manager.addUser(user,topicList);
    }

    void removeUser(String user){
        if (user==null) throw new IllegalArgumentException();
        manager.removeUser(user);
    }

    void addInterest(String user, String topic){
        if (topic == null || user == null)
            throw new IllegalArgumentException();

        manager.addInterest(user, new TopicOfInterest(topic));
    }

    void removeInterest(String user, String topic){

        if (user == null || topic == null)
            throw new IllegalArgumentException();

        manager.removeInterest(user,new TopicOfInterest(topic));

    }

    List<TopicOfInterest> getUserInterests(String user){
        return manager.getInterestsUser(user);
    }

    List<String> getUsersByInterest(String topic){

        if (topic == null)
            throw new IllegalArgumentException();

        TopicOfInterest topicOfInterest=new TopicOfInterest(topic);
        List<String> userList= manager.getUsers();
        List<String> usersInterested = new ArrayList<>();

        for (String user : userList){

            if (manager.getInterestsUser(user).contains(topicOfInterest))
                usersInterested.add(user);
        }
        return usersInterested;
    }

    List<TopicOfInterest> getCommonInterests(String user1, String user2){

        if (user1 == null || user2 == null)
            throw new IllegalArgumentException();

        List<TopicOfInterest> user1Topics = manager.getInterestsUser(user1);
        List<TopicOfInterest> user2Topics = manager.getInterestsUser(user2);


        user1Topics.retainAll(user2Topics);

        return user1Topics; //returns the user1Topics list without the elements that are also in user2

    }

    List<TopicOfInterest> getAllTopics(){
        return manager.getInterests();
    }

    @Override
    public String toString() {
        return manager.toString();
    }

}



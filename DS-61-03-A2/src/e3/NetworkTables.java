package e3;

import java.util.ArrayList;
import java.util.List;

public class NetworkTables implements NetworkManager{

    ArrayList<String> userList;
    ArrayList<ArrayList<TopicOfInterest>> topicList;

    public NetworkTables() {
        userList = new ArrayList<>();
        topicList = new ArrayList<>();
    }

    @Override
    public void addUser(String user, List<TopicOfInterest> topicsOfInterest) {

        if (userList.contains(user)) throw new IllegalArgumentException();

        userList.add(user);
        topicList.add((ArrayList<TopicOfInterest>) topicsOfInterest);

    }

    @Override
    public void removeUser(String user) {

        int i;
        if ( (i = userList.indexOf(user)) == -1 )
            throw new IllegalArgumentException();

        userList.remove(i);
        topicList.remove(i);

    }

    @Override
    public void addInterest(String user, TopicOfInterest topicOfInterest) {

        int i;
        if ( (i = userList.indexOf(user)) == -1 )
            throw new IllegalArgumentException();

        ArrayList<TopicOfInterest> userTopics=topicList.get(i);

        userTopics.add(topicOfInterest);
        topicList.set(i,userTopics);
    }

    @Override
    public void removeInterest(String user, TopicOfInterest topicOfInterest) {

        int i;
        if ( (i = userList.indexOf(user)) == -1 )
            throw new IllegalArgumentException();

        ArrayList<TopicOfInterest> userTopics=topicList.get(i);

        userTopics.remove(topicOfInterest);
        topicList.set(i,userTopics);

    }

    @Override
    public List<String> getUsers() {
        return userList;
    }

    @Override
    public List<TopicOfInterest> getInterests() {

        List<TopicOfInterest> aux, allTopics;
        allTopics = new ArrayList<>();

        for (ArrayList<TopicOfInterest> topicOfInterests : topicList) {

            aux = topicOfInterests;

            for (TopicOfInterest topic : aux) {

                if (!allTopics.contains(topic))
                    allTopics.add(topic);
            }
        }
        return allTopics;
    }

    @Override
    public List<TopicOfInterest> getInterestsUser(String user) {

        int i;
        if ( (i = userList.indexOf(user)) == -1 )
            throw new IllegalArgumentException();

        return topicList.get(i);
    }

    @Override
    public String toString() {
        StringBuilder total= new StringBuilder();
        List<TopicOfInterest> aux;

        total.append("USERNAME: INTERESTS\n---------------------\n");

        for (int i=0; i<userList.size();++i){

            total.append(userList.get(i)).append(": ");
            aux=topicList.get(i);

            for (int j=0; j<aux.size();++j){
                total.append(aux.get(j));
                if (j!=aux.size()-1)
                    total.append(", ");
            }
            if (i!=userList.size()-1)
                total.append("\n");
        }

        return total.toString();
    }
}



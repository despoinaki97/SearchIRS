/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import gr.uoc.csd.hy463.Topic;
import gr.uoc.csd.hy463.TopicType;
import gr.uoc.csd.hy463.TopicsReader;
import java.util.ArrayList;

/**
 *
 * @author ΔΕΣΠΟΙΝΑ
 */
public class TopicsQueries {

    public static int Number;
    public static TopicType Type;
    public static String Summary;
    public static String Description;

    TopicsQueries() {
        Number = 0;
        Type = null;
        Summary = "";
        Description = "";
    }

    public static ArrayList<Topic> readtopics() throws Exception {
        TopicsQueries TQ = new TopicsQueries();
        ArrayList<Topic> topics = TopicsReader.readTopics("../../Resources/topics.xml");
        for (Topic topic : topics) {
            Number = topic.getNumber();
            Type = topic.getType();
            Summary = topic.getSummary();
            Description = topic.getDescription();
        }
        return topics;
    }
}

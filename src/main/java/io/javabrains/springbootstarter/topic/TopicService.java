package io.javabrains.springbootstarter.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
	
	@Autowired
	private TopicRepository topicRepository;
	private List<Topic> topics = new ArrayList<>(Arrays.asList(
			new Topic("a", "b", "d"),
			new Topic("s", "b", "c")
			));
	public List<Topic> getAllTopics()
	{
		List<Topic>topics = (List<Topic>)topicRepository.findAll(); 
		//return topics;
		return topics;
	}
	public Optional<Topic> getTopic(String id) {
		//return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
		return topicRepository.findById(id);
	}
	public void addTopic(Topic topic) {
		topicRepository.save(topic);
		//topics.add(topic);
	}
	public void updateTopic(String id, Topic topic) {
		
		topicRepository.save(topic);
	}
	public void deleteTopic(String id) {
		topicRepository.deleteById(id);		
	}
}

package com.example.Mock.listener;

import com.example.Mock.entity.Message;
import com.example.Mock.repository.MessageRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {

    @Autowired
    private MessageRepository messageRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "topic", groupId = "cons")
    public void listen(String message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            String msgId = jsonNode.get("msg_id").asText();
            String timestamp = jsonNode.get("timestamp").asText();

            Message messageEntity = new Message();
            messageEntity.setMsgId(msgId);
            messageEntity.setTimeRq(timestamp);

            messageRepository.save(messageEntity);

            System.out.println("Сообщение сохранено: msg_id=" + msgId + ", timestamp=" + timestamp);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
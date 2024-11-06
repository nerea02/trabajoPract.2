package com.example.Messages.Services;

import com.example.Messages.modelo.Message;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public List<Message> messages = new ArrayList<>();
    public Long nextId = 1L;

    public MessageService() {
        messages.add(new Message(nextId++, "Yair", "Quiero dinero", LocalDateTime.now()));
        messages.add(new Message(nextId++, "Enzo", "Quiero un trabajo", LocalDateTime.now()));
    }
    
    public List<Message> getAllMessages(){
        return messages;
    }
    public Optional<Message> getMessageById(Long id) {
        return messages.stream().filter(msg->msg.getId().equals(id)).findFirst();
    }
    public Message addMessage(Message msg) {
    msg.setId(nextId++);
    messages.add(msg);
    return msg;
    }
    public Message updateMessage(Long id, Message updateMessage){
        Optional<Message> oldMessage = getMessageById(id);
        if (oldMessage.isPresent()){
            Message msg = oldMessage.get();
            msg.setUser(updateMessage.getUser());
            msg.setMessage(updateMessage.getMessage());
            return msg;
        }
        return null;
    }
    
    public boolean deleteMessage(Long id){
        return messages.removeIf(msg->msg.getId().equals(id));
    }
   
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Messages.Controller;


import com.example.Messages.Services.MessageService;
import com.example.Messages.modelo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class Messagecontroller {
    @Autowired
    private MessageService messageService;
    @GetMapping("/messages")
    public List<Message> getAllMessages(){
        return messageService.getAllMessages();
    }
    @GetMapping("message/{id}")
    public ResponseEntity<Message>getMessageById(@PathVariable Long id) {

        Optional<Message> message = messageService.getMessageById(id);
        return message.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
    @PostMapping("/message")
    public ResponseEntity<Message> addMessage(@RequestBody Message message) {
        Message newMessage = messageService.addMessage(message);
        return
                ResponseEntity.status(HttpStatus.CREATED).body(newMessage);
    }

    @PutMapping("message/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable Long id, @RequestBody Message updatedMessage) {
        Message message = messageService.updateMessage(id,updatedMessage);
        return message != null ? ResponseEntity.ok(message) :
                ResponseEntity.notFound().build();
    }
    @DeleteMapping("message/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id){
        return messageService.deleteMessage(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}
 

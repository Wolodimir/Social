package com.Social.controllers.sweater;

import com.Social.models.Message;
import com.Social.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.GeneratedValue;
import java.util.List;

@Controller
public class SweaterController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("main-sweater")
    public String sweaterMainPage(Model model){
        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);

        return "sweater/main-sweater";
    }

    @PostMapping("main-sweater")
    public String add(@RequestParam String text,
                      @RequestParam String tag, Model model){
        Message message = new Message(tag, text);
        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);

        return "sweater/main-sweater";
    }

    @PostMapping("filter")
    public String add(@RequestParam String filter, Model model) {
        Iterable<Message> messages;

        if(filter != null && !filter.isEmpty()){
            messages = messageRepo.findByTag(filter);
        }else {
            messages = messageRepo.findAll();
        }
        model.addAttribute("messages", messages);

        return "sweater/main-sweater";
    }

}

package com.zglu.websocket;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zglu
 */
@Log4j2
@Controller
@AllArgsConstructor
public class TestController {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/join")
    @SendTo("/listen")
    public String join(String body) {
        return body;
    }

    @MessageMapping("/say")
    @SendTo("/listen")
    public String sayByMessageMapping(String body) {
        return body;
    }

    @ResponseBody
    @GetMapping("/say/{msg}")
    public void say(@PathVariable String msg) {
        messagingTemplate.convertAndSend("/listen", msg);
    }

    @ResponseBody
    @GetMapping("/say/{msg}/{user}")
    public void say(@PathVariable String msg, @PathVariable String user) {
        messagingTemplate.convertAndSend("/" + user + "/listen", msg);
    }
}

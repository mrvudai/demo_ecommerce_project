package daipv.controller;

import daipv.email.itf.IEmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/email")
@RequiredArgsConstructor
public class TestMailController {
    private final IEmailSender emailSender;

    @GetMapping("/{email}")
    public void sendEmail(@PathVariable("email") String email){
        String body = "bạn đã bị trừ 1 triệu trong tài khoản!";
        String topic = "xin chúc mừng!";
        emailSender.send(email, body, topic);
    }

}

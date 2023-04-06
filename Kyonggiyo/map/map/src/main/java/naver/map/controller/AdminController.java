package naver.map.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")

public class AdminController {

    @GetMapping("")
    public String adminHome() {
        return "admin/home";
    }

    @GetMapping("/userManage")
    public String userManage() {
        return "admin/userManage";
    }

    @GetMapping("/mapManage")
    public String mapManage() {
        return "admin/mapManage";
    }
}

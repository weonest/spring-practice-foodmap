package naver.map.controller;

import naver.map.domain.User;
import naver.map.dto.UserRequestDto;
import naver.map.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @GetMapping("/register")
    public String register(Model model, UserRequestDto param) {
        model.addAttribute("user", param);

        return "account/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid UserRequestDto param, BindingResult bindingResult, Model model) {

        userService.validate(param, bindingResult);

        if (bindingResult.hasErrors()) {
            return "account/register";
        }

        userService.save(param);
        return "redirect:/";
    }

//    @PostMapping("/register")
//    public String register(@Valid UserRequestDto param, BindingResult bindingResult, Model model) {
//
//        userService.validate(param, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("user", param);
//            return "account/register";
//        }
//
//        userService.save(param);
//        return "redirect:/";
//    }
}

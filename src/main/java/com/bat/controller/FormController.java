package com.bat.controller;

import com.bat.model.User;
import com.bat.model.UserForm;
import com.bat.service.IUserService;
import com.bat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {
    @Autowired
    private IUserService userService = new UserService();

    @GetMapping("/")
    public String showCreateForm(Model model){
        model.addAttribute("userForm",new UserForm());
        return "/create";
    }
    @PostMapping("/")
    public String checkValidation(@Validated @ModelAttribute("userForm")UserForm userForm, BindingResult bindingResult, Model model){
        new UserForm().validate(userForm,bindingResult);
        if (bindingResult.hasFieldErrors()){
            return "/create";
        }else {
            model.addAttribute("userForm",userForm);
            String firstName = userForm.getFirstName();
            String lastName = userForm.getLastName();
            String phone = userForm.getPhoneNumber();
            int age = userForm.getAge();
            String email = userForm.getEmail();
            User user = new User(firstName,lastName,phone,age,email);
            userService.save(user);
            return "/result";
        }
    }
}

package com.example.sahayogi_aama.controller;


import com.example.sahayogi_aama.entity.Aama;
import com.example.sahayogi_aama.entity.Hire;
import com.example.sahayogi_aama.pojo.AamaPojo;
import com.example.sahayogi_aama.pojo.HirePojo;
import com.example.sahayogi_aama.pojo.UserPojo;
import com.example.sahayogi_aama.services.AamaService;
import com.example.sahayogi_aama.services.HireService;
import com.example.sahayogi_aama.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AamaService aamaService;
    private final HireService hireService;
    @GetMapping("/test")
    public String getSetting(Model model) {
        return "User/test";
    }
    @GetMapping("/index")
    public String gethomepage(Model model,        Authentication authentication) {

        if (authentication!=null){
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("Admin")) {
                return "redirect:/admin/dashboard";
            }
        }
    }
        List<Aama> aamaList=aamaService.findAll();
        model.addAttribute("aamaList",aamaList);
        return "User/index";
    }


    @GetMapping("/register")
    public String GetRegister(Model model){
        model.addAttribute("user", new UserPojo());
        return "User/register";
    }

    @PostMapping("/saveregister")
    public String GetRegister(@Valid UserPojo userPojo){
        userService.save(userPojo);
        return "redirect:/login";
    }

    @GetMapping("/aamaprofile/{id}")
    public String GetAamaProfile(@PathVariable("id") Integer id , Model model, Principal principal){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "User/login";
        }
        Aama aama= aamaService.fetchById(id);
        model.addAttribute("hire", new HirePojo());
        model.addAttribute("aamainfo",new AamaPojo(aama));
        model.addAttribute("aamadata",aama);
        model.addAttribute("info",userService.findByEmail(principal.getName()));
//        List<Aama> aamaList=aamaService.findAll();
//        model.addAttribute("aamaList",aamaList);
        return "User/aamaprofile";
    }

    @PostMapping("/savehire")
    public String getHire(@Valid HirePojo hirePojo){
        hireService.save(hirePojo);
        return "redirect:/login";
    }


    @GetMapping("/profile")
    public String Profile(Model model,Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "User/login";
        }
        model.addAttribute("update", new UserPojo());
        model.addAttribute("loggeduser",userService.findByEmail(principal.getName()));

        return "User/Profile";
    }



    @GetMapping("/appledhiring/{id}")
    public  String getAppliedJos(@PathVariable("id") Integer id, Model model,Principal principal){
        List<Hire> hireList= hireService.findApplicationById(id);
        model.addAttribute("myhiring",hireList);
        model.addAttribute("userdata",userService.findByEmail(principal.getName()));
        return "User/ViewMyHiring";
    }


    @GetMapping("/deletehi/{id}")
    public String deleteContact(@PathVariable("id") Integer id) {
        hireService.deleteByID(id);
        return "redirect:/user/profile";
    }


    @PostMapping("/updateuser")
    public String updateUser(@Valid UserPojo userPojo){
        userService.save(userPojo);
        return "redirect:/user/profile";
    }





    @GetMapping("/aboutus")
    public String GetAbout(Model model){
        return "User/about";
    }


    @GetMapping("/faq")
    public String getFaq(Model model){
        return "User/faq";
    }

    @GetMapping("/features")
    public String getFeature(Model model){
        return "User/features";
    }
}

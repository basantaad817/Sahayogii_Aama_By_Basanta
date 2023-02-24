package com.example.sahayogi_aama.controller;

import com.example.sahayogi_aama.entity.Aama;
import com.example.sahayogi_aama.entity.Hire;
import com.example.sahayogi_aama.pojo.AamaPojo;
import com.example.sahayogi_aama.services.AamaService;
import com.example.sahayogi_aama.services.HireService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AamaService aamaService;
    private final HireService hireService;


    @GetMapping("/aamacreate")
    public String getSetting(Model model) {
        model.addAttribute("aamacreate",new AamaPojo());
        return "Admin/AddAama";
    }

    @PostMapping("/saveaama")
    public String GetRegister(@Valid AamaPojo aamaPojo,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
        Map<String, String> requestError = validateRequest(bindingResult);
        if (requestError != null) {
            redirectAttributes.addFlashAttribute("requestError", requestError);
            return "redirect:/admin/aamacreate";
        }
        aamaService.save(aamaPojo);
        redirectAttributes.addFlashAttribute("successMsg", "User saved successfully");
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/editaama/{id}")
    public String editMembers(@PathVariable("id") Integer id, Model model) {
        Aama aama = aamaService.fetchById(id);
        model.addAttribute("aamacreate", new AamaPojo(aama));
        return "Admin/AddAama";
    }

    @GetMapping("/deleteaama/{id}")
    public String deleteApplication(@PathVariable("id") Integer id) {
        aamaService.deleteById(id);
        return "redirect:/admin/dashboard";
    }



    @GetMapping("/dashboard")
    public String getDashboard(Model model){
        List<Aama> aamaList=aamaService.findAll();
        model.addAttribute("aamaList",aamaList);
        return "/Admin/admindashboard";}


    @GetMapping("/listofhiring")
    public String gethiring(Model model){
        List<Hire> hireList=hireService.findAll();
        model.addAttribute("hirelist",hireList);
        return "/Admin/ViewHire";
    }




    public Map<String, String> validateRequest(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return null;
        }
        Map<String, String> errors = new HashMap<>();
        bindingResult.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;

    }

}

 package com.app.dictapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.dictapp.dto.AdminLoginDto;
import com.app.dictapp.dto.DictionaryDto;
import com.app.dictapp.model.AdminLogin;
import com.app.dictapp.model.Dictionary;
import com.app.dictapp.service.AdminLoginRepo;
import com.app.dictapp.service.DictionaryRepo;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    AdminLoginRepo alrepo;

    @Autowired
    DictionaryRepo drepo;

    // Show the home page (index.html)
    @GetMapping("/")
    public String showIndex(Model model) {
        DictionaryDto dto = new DictionaryDto();  // Create DTO for binding data
        model.addAttribute("dto", dto);  // Add DTO to the model to be accessed in the template
        return "index";  // This will map to the 'index.html' template in resources/templates
    }

    // Handle search action
    @PostMapping("/")
    public String search(@ModelAttribute DictionaryDto dto, Model model) {
        Dictionary d = drepo.getByWord(dto.getWord());  // Search for the word in the dictionary
        model.addAttribute("dto", dto);  // Add the input DTO back to the model
        model.addAttribute("d", d);  // Add the result (Dictionary entry) to the model
        return "index";  // Redirect to the 'index.html' page
    }

    // Show the admin login page (adminlogin.html)
    @GetMapping("/adminlogin")
    public String adminLogin(Model model) {
        AdminLoginDto dto = new AdminLoginDto();  // Create a DTO for admin login
        model.addAttribute("dto", dto);  // Add the DTO to the model for rendering in the template
        return "adminlogin";  // This will map to the 'adminlogin.html' template in resources/templates
    }

    // Handle admin login validation
    @PostMapping("/adminlogin")
    public String validate(@ModelAttribute AdminLoginDto dto, RedirectAttributes attrib, HttpSession session) {
        try {
            // Try to find the admin by ID
            AdminLogin al = alrepo.findById(dto.getAdminid()).orElse(null);  // Use Optional to avoid NullPointerException

            if (al != null) {
                // Check if the password matches
                if (al.getPassword().equals(dto.getPassword())) {
                    session.setAttribute("adminid", al.getAdminid());  // Store admin ID in the session
                    return "redirect:/admin/adminhome";  // Redirect to admin home page
                } else {
                    attrib.addFlashAttribute("msg", "Invalid password");  // Set flash message for invalid password
                }
            } else {
                attrib.addFlashAttribute("msg", "Admin does not exist");  // Set flash message if admin not found
            }
        } catch (Exception ex) {
            attrib.addFlashAttribute("msg", "An error occurred. Please try again.");  // Handle any unexpected errors
        }
        return "redirect:/adminlogin";  // Redirect back to the login page if validation fails
    }
}

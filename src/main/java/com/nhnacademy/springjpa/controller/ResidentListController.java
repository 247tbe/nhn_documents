package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.service.ResidentService;
import java.security.Principal;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResidentListController {
    private final ResidentService residentService;

    public ResidentListController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping("/residentList")
    public String residentList(Principal principal,
                               Model model) {
        String id = principal.getName();
        List<Resident> residentList = residentService.getAllResidentsById(id);

        model.addAttribute("residentList", residentList);

        return "residentList";
    }
}

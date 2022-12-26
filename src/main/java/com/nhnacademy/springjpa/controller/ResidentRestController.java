package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.ResidentDto;
import com.nhnacademy.springjpa.domain.ResidentRegisterRequest;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.service.ResidentService;
import java.security.Principal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/residents")
public class ResidentRestController {
    private final ResidentService residentService;

    public ResidentRestController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Resident createResident(@RequestBody ResidentRegisterRequest residentRegisterRequest) {
        return residentService.createResident(residentRegisterRequest);
    }

    @PutMapping("/{serialNumber}")
    public Resident modifyResident(@PathVariable("serialNumber") Long serialNumber,
                                   @RequestBody ResidentRegisterRequest residentRegisterRequest) {
        return residentService.modifyResident(serialNumber, residentRegisterRequest);
    }

    @GetMapping("/{serialNumber}")
    public ResidentDto getResident(@PathVariable("serialNumber") Long serialNumber) {
        return residentService.getResident(serialNumber);
    }

    @GetMapping
    public String getResidents(Principal principal,
                               Model model) {
        String id = principal.getName();
        List<Resident> residents = residentService.getAllResidentsById(id);

        model.addAttribute("residents", residents);

        return "residents";
    }
}

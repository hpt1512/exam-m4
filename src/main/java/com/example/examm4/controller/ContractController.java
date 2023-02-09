package com.example.examm4.controller;

import com.example.examm4.ContractValidate;
import com.example.examm4.model.Contract;
import com.example.examm4.service.impl.ContractServiceImpl;
import com.example.examm4.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class ContractController {
    @Autowired
    private ContractServiceImpl contractService;
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    private ContractValidate contractValidate;
    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("contractList", contractService.findAll());
        model.addAttribute("customerList", customerService.findAll());
        return "contract/list";
    }
    @GetMapping("contract/create")
    public String formCreate(Model model) {
        model.addAttribute("contract", new Contract());
        model.addAttribute("customerList", customerService.findAll());
        return "contract/create";
    }
    @PostMapping("contract/doCreate")
    public String doCreate(@Valid @ModelAttribute Contract contract, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        contractValidate.validate(contract, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("customerList", customerService.findAll());
            return "contract/create";
        }
        contractService.insert(contract);
        redirectAttributes.addFlashAttribute("mess", "Create contract successfully");
        return "redirect:/";
    }
    @GetMapping("contract/detail/{id}")
    public String detail(@PathVariable String id, Model model) {
        model.addAttribute("contract", contractService.findContractById(id));
        return "contract/detail";
    }
    @GetMapping("/contract/delete")
    public String delete(Model model, @RequestParam("id") String id, RedirectAttributes redirectAttributes) {
        Contract contract = contractService.findContractById(id);
        contractService.delete(contract);
        redirectAttributes.addFlashAttribute("mess", "Delete successfully");
        return "redirect:/";
    }
    @GetMapping("/contract/find")
    public String find(@RequestParam String customerFind, @RequestParam String serviceFind, Model model) {
        List<Contract> contractList = contractService.findByCustomerAndService(customerFind, serviceFind);
        if ("".equals(customerFind) && "".equals(serviceFind)) {
            return "redirect:/";
        }
        model.addAttribute("contractList", contractList);
        model.addAttribute("customerList", customerService.findAll());
        return "contract/list";
    }
}

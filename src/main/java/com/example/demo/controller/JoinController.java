package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.MemberDto;
import com.example.demo.service.JoinService;

@Controller
public class JoinController {
	
	@Autowired
	JoinService joinService;
	
	@GetMapping("/join")
	public String joinP() {
		return "join";
	}
	
	@PostMapping("/joinProc")
	public String joinProcess (MemberDto dto) {
		System.out.println(dto.getId());
		joinService.joinUp(dto);
		
		return "redirect:/login";
	}
}

package com.lentux.calciatore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lentux.calciatore.entity.Calciatore;
import com.lentux.calciatore.entity.Squadra;
import com.lentux.calciatore.service.CalciatoreService;
import com.lentux.calciatore.service.SquadraService;
import com.lentux.calciatore.util.Utils;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@SessionAttributes("listaSquadre")
public class CalciatoreController {

	private CalciatoreService calciatoreService;
	private SquadraService squadraService;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "nome", "asc", model);		
	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		return "login";	
	}
	
	@GetMapping("/nuovoCalciatore")
	public String nuovoCalciatore(Calciatore calciatore,Model model, HttpSession session) {
		
		if(squadraService.listaSquadre().isEmpty()) {
			model.addAttribute("message", "Impossibile inserire calciatore. Nessuna squadra presente");
			return "index";
		}
		
		List<Squadra> listaSquadre = squadraService.listaSquadre();
		model.addAttribute("listaSquadre", listaSquadre);
		session.setAttribute("listaNazioni", Utils.getNazioni());
		model.addAttribute("calciatore", calciatore);
		
		return "nuovoCalciatore";
	}

	@PostMapping("/salvaCalciatore")
	public String saveCalciatore(@Valid @ModelAttribute("calciatore") Calciatore calciatore,
			BindingResult bindingResult, RedirectAttributes ra) {

		if(bindingResult.hasErrors()) {
			return "nuovoCalciatore";
		}
		calciatoreService.salvaCalciatore(calciatore);
		ra.addFlashAttribute("message", "Calciatore inserito con successo");
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model, HttpSession session) {
		
		Calciatore calciatore = calciatoreService.getCalciatoreById(id);
		session.setAttribute("listaSquadre", squadraService.listaSquadre());
		session.setAttribute("listaNazioni", Utils.getNazioni());
		model.addAttribute("calciatore", calciatore);
		return "updateCalciatore";
	}
	
	@PostMapping("/updateCalciatore")
	public String updateCalciatore(@Valid @ModelAttribute("calciatore") Calciatore calciatore,
			BindingResult bindingResult, RedirectAttributes ra) {
		
		if(bindingResult.hasErrors()) {
			return "updateCalciatore";
		}
		calciatoreService.salvaCalciatore(calciatore);
		ra.addFlashAttribute("message", "Calciatore modificato con successo");
		return "redirect:/";
	}

	@GetMapping("/deleteCalciatore/{id}")
	public String deleteCalciatore(@PathVariable (value = "id") long id, RedirectAttributes ra) {
		
		this.calciatoreService.deleteCalciatoreById(id);
		ra.addFlashAttribute("message", "Calciatore eliminato con successo");
		return "redirect:/";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Calciatore> page = calciatoreService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Calciatore> listaCalciatori = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listaCalciatori", listaCalciatori);
		return "index";
	}
}

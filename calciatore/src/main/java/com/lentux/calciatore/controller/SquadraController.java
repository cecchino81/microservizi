package com.lentux.calciatore.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lentux.calciatore.entity.Calciatore;
import com.lentux.calciatore.entity.Squadra;
import com.lentux.calciatore.service.FilesService;
import com.lentux.calciatore.service.SquadraService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class SquadraController {

	private SquadraService squadraService;
	private FilesService filesService;
	
	@GetMapping("/updateSquadra/{id}")
	public String updateSquadra(@PathVariable ( value = "id") long id, Model model) {
		
		Squadra squadra = squadraService.getSquadraById(id);
		
		model.addAttribute("squadra", squadra);
		return "updateSquadra";
	}
	
	@GetMapping("/nuovaSquadra")
	public String nuovaSquadra(Squadra squadra, Model model, HttpSession session) {
		model.addAttribute("squadra", squadra);
		
		return "nuovaSquadra";
	}
	
	@GetMapping("/listaSquadre")
	public String listaSquadre(Model model) {
		return findPaginatedSquadre(1, "nome", "asc", model);
	}
	
	@PostMapping("/salvaSquadra")
	public String saveSquadra(@Valid @ModelAttribute("squadra") Squadra squadra, BindingResult bindingResult, 
			@RequestParam("file") MultipartFile file, RedirectAttributes ra) {

		if(bindingResult.hasErrors()) {
			return "nuovaSquadra";
		}
		Squadra salvaSquadra = squadraService.salvaSquadra(squadra);
		
		try {
			filesService.store(file,salvaSquadra);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ra.addFlashAttribute("message", "Squadra inserita con successo");
		return "redirect:/listaSquadre";
	}
	
	@PostMapping("/updateSquadra")
	public String updateSquadra(@Valid @ModelAttribute("squadra") Squadra squadra,
			@RequestParam("file") MultipartFile file,
			BindingResult bindingResult, RedirectAttributes ra) {
		
		if(bindingResult.hasErrors()) {
			return "updateSquadra";
		}
		Squadra salvaSquadra = squadraService.salvaSquadra(squadra);
		try {
			filesService.store(file,salvaSquadra);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ra.addFlashAttribute("message", "Squadra modificata con successo");
		return "redirect:/listaSquadre";
	}
	
	@GetMapping("/listaCalciatoriBySquadra/{id}")
	public String listaCalciatoriBySquadra(@PathVariable ( value = "id") long id, Model model) {
		
		List<Calciatore> listaCalciatoriBySquadra = squadraService.getCalciatoriByIdSquadra(id);
		
		if(!listaCalciatoriBySquadra.isEmpty()) {
			model.addAttribute("nomeSquadra", listaCalciatoriBySquadra.get(0).getSquadra().getNome());
		}
		model.addAttribute("listaCalciatoriBySquadra", listaCalciatoriBySquadra);
		return "listaCalciatoriSquadra";
	}
		
	@GetMapping("/deleteSquadra/{id}")
	public String deleteSquadra(@PathVariable (value = "id") long id, RedirectAttributes ra) {
		
		try {
			this.squadraService.deleteSquadraById(id);
		}catch (Exception e) {
			ra.addFlashAttribute("error", "Impossibile eliminare la squadra ");
		}
		return "redirect:/listaSquadre";
	}


	@GetMapping("/pageSquadre/{pageNo}")
	public String findPaginatedSquadre(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Squadra> page = squadraService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Squadra> listaSquadre = page.getContent();
		
		Map<String, String> base64Images = new HashMap<>();
		for (Squadra squadra : listaSquadre) {
			base64Images.put(squadra.getFiles().getId(), Base64.getEncoder().encodeToString(squadra.getFiles().getData()));
		}
		model.addAttribute("images",base64Images);
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listaSquadre", listaSquadre);
		return "listaSquadre";
	}
}

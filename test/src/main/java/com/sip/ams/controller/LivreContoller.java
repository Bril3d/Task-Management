package com.sip.ams.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.sip.ams.model.Livre;
@Controller
public class LivreContoller {
	@Autowired
	private LivreService livreService;
	@GetMapping("/")
	public String accueil(Model model) {
	List<Livre> livres = livreService.listAllLivres();
	model.addAttribute("livres", livres);
	return "accueil";
	}
	@GetMapping("/ajouterLivre")
	public String ajouterLivre(Model model) {
	Livre livre = new Livre();
	model.addAttribute("livre", livre);
	return "ajouter_livre";
	}
	@PostMapping("/ajouterLivre")
	public String enregistrerLivre(@ModelAttribute("livre") Livre livre,
	BindingResult result) {
	if (result.hasErrors()) {
	return "ajouter_livre";
	}
	livreService.enregistrerLivre(livre);
	return "redirect:/";
	}
	@GetMapping("/editerLivre/{id}")
	public String editerLivre(@PathVariable("id") Long id, Model model) {
	Livre livre = livreService.getLivreById(id);
	model.addAttribute("livre", livre);
	return "editer_livre";
	}
	@PostMapping("/update")
	public String miseajour(@ModelAttribute("livre") Livre livre, BindingResult
	result) {
	livreService.enregistrerLivre(livre);
	return "redirect:/";
	}
	@GetMapping("/supprimerLivre/{id}")
	public String supprimerLivre(@PathVariable("id") Long id) {
	livreService.supprimerLivre(id);
	return "redirect:/";
	}
}
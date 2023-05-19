package com.sip.ams.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sip.ams.model.Livre;
import com.sip.ams.repository.LivreRepository;
@Service
public class LivreService {
	@Autowired
	private LivreRepository livreRepository;
	public List<Livre> listAllLivres() {
	return livreRepository.findAll();
	}
	public void enregistrerLivre(Livre livre) {
	livreRepository.save(livre);
	}
	public Livre getLivreById(Long id) {
	return livreRepository.findById(id).get();
	}
	public void supprimerLivre(Long id) {
	livreRepository.deleteById(id);
	}
}

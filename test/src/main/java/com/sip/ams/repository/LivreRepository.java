package com.sip.ams.repository;
import com.sip.ams.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
public interface LivreRepository extends JpaRepository<Livre, Long> {}
package com.gym.socios.repository;
import com.gym.socios.model.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface SocioRepository extends JpaRepository <Socio,Long> {
    
    // findBy: Es el prefijo obligatorio que le dice a Spring: "Quiero hacer un SELECT".
    // Run: Es el nombre exacto del atributo que tienes en tu clase Socio. 
    // Spring busca la columna run en tu tabla de Laragon.
    // In: Es una palabra clave de JPA que significa "dentro de una lista".
    //  En SQL, esto se traduce al operador IN (...).
    List<Socio> findByRunIn(List<String> runs);

    Optional<Socio> findByRun(String run);
}

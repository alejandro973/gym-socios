package com.gym.socios.config;

import com.gym.socios.model.Socio;
import com.gym.socios.repository.SocioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
//@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final SocioRepository repository;

    @Override
    public void run(String... args) {
        // 1. Verificamos si la base de datos de Laragon ya tiene socios
        if (repository.count() > 0) {
            log.info(">>> Base de datos de Socios ya tiene información. Saltando inicialización.");
            return;
        }


        log.info(">>> Cargando socios de prueba iniciales...");

       // 1. Juan Perez
repository.save(new Socio(null, "12345678-9", "Juan", "Perez", 
                "juan@gmail.com", new Date(90, 4, 15)));

// 2. Maria Soto
repository.save(new Socio(null, "11222333-4", "Maria", "Soto", 
                "maria@gmail.com", new Date(95, 7, 20)));

// 3. Carlos Rojas
repository.save(new Socio(null, "15555666-k", "Carlos", "Rojas", 
                "carlos@gmail.com", new Date(88, 11, 10)));

// 4. Ana López
repository.save(new Socio(null, "17888999-2", "Ana", "López", 
                "ana.lopez@outlook.com", new Date(92, 2, 25)));

// 5. Diego Muñoz
repository.save(new Socio(null, "19222333-k", "Diego", "Muñoz", 
                "d.munoz@yahoo.cl", new Date(100, 0, 12)));

// 6. Elena Valdés
repository.save(new Socio(null, "14777888-5", "Elena", "Valdés", 
                "evaldes@empresa.cl", new Date(85, 10, 30)));

// 7. Bastián Tapia
repository.save(new Socio(null, "20111222-7", "Bastián", "Tapia", 
                "bastian.t@gmail.com", new Date(102, 5, 5)));

// 8. Francisca Guerra
repository.save(new Socio(null, "16444555-0", "Francisca", "Guerra", 
                "fran.guerra@gmail.com", new Date(94, 8, 18)));



        log.info(">>> Socios iniciales cargados correctamente en Laragon.");




    }
}
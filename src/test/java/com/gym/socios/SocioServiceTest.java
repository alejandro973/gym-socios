package com.gym.socios;
import com.gym.socios.dto.SocioResponseDTO;
import com.gym.socios.model.Socio;
import com.gym.socios.repository.SocioRepository;
import com.gym.socios.service.SocioService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@SpringBootTest
public class SocioServiceTest {

    @Autowired
    private SocioService socioService;

    @MockBean
    private SocioRepository socioRepository;

    @Test
    public void testObtenerTodos() {
       
        Date fechaFake = new Date();
        Socio socioFake = new Socio(1L, "12345678-9", "Juan", "Perez", "juan@gmail.com", fechaFake);
        
        when(socioRepository.findAll()).thenReturn(List.of(socioFake));

       
        List<SocioResponseDTO> socios = socioService.obtenerTodos();

        assertNotNull(socios);
        assertEquals(1, socios.size());
    }

   
    @Test
    public void testObtenerPorId() {
        Long id = 1L;
        Date fechaFake = new Date();
        Socio socioFake = new Socio(id, "12345678-9", "Juan", "Perez", "juan@gmail.com", fechaFake);

        when(socioRepository.findById(id)).thenReturn(Optional.of(socioFake));

      
        Optional<SocioResponseDTO> found = socioService.obtenerPorId(id);

        assertTrue(found.isPresent());
        assertEquals("12345678-9", found.get().getRun());
    }

    @Test
    public void testEliminarSocio() {
        Long id = 1L;

        doNothing().when(socioRepository).deleteById(id);

       
        socioService.eliminarSocio(id);

        
        verify(socioRepository, times(1)).deleteById(id);
    }
}

package com.certidevs.controller;

import com.certidevs.model.Usuario;
import com.certidevs.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private Model model;



    @Test
    void encontrarTodos() {

        Usuario usu1 = new Usuario().builder().id(1L).build();
        Usuario usu2 = new Usuario().builder().id(2L).build();
        List<Usuario> usuarios = List.of(usu1, usu2);
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        String view = usuarioController.encontrarTodos(model);

        assertEquals("usuario-list", view);
        verify(usuarioRepository).findAll();
        verify(model).addAttribute("usuarios", usuarios);

    }

    @Test
    void encontrarPorIdCuandoExisteUsuario() {

        Usuario juan = Usuario.builder().id(1L).nombre("Juan").build();
        Optional<Usuario> juanOpt = Optional.of(juan);
        when(usuarioRepository.findById(1L)).thenReturn(juanOpt);

        String view = usuarioController.encontrarPorId((1L), model);

        assertEquals("usuario-detail", view);
        verify(usuarioRepository).findById(1L);
        verify(model).addAttribute("usuario", juan);
    }

    @Test
    void encontrarPorIdCuandoNoExisteUsuario() {

        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        String view = usuarioController.encontrarPorId((1L), model);

        assertEquals("usuario-detail", view);
        verify(usuarioRepository).findById(1L);
        verify(model, never()).addAttribute(anyString(), any());
    }
    @Test
    void formularioParaCrear() {

        String view = usuarioController.formularioParaCrear(model);

        assertEquals("usuario-form", view);
        verify(model).addAttribute(eq("usuario"), any(Usuario.class));



    }

    @Test
    void formularioParaActualizarSiExiste() {

        Usuario juan = new Usuario().builder().id(1L).nombre("Juan").build();
        Optional<Usuario> juanOpt = Optional.of(juan);
        when(usuarioRepository.findById(1L)).thenReturn(juanOpt);

        String view = usuarioController.formularioParaActualizar((1L), model);

        assertEquals("usuario-form", view);
        verify(usuarioRepository).findById(1L);
        verify(model).addAttribute("usuario", juan);

    }

    @Test
    void formularioParaActualizarSiNoExiste() {

        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        String view = usuarioController.formularioParaActualizar(1L, model);

        assertEquals("usuario-form", view);
        verify(usuarioRepository).findById(1L);
        verify(model, never()).addAttribute(anyString(), any());
    }

    @Test
    void guardar() {
    }

    @Test
    void borrarPorId() {

        String view = usuarioController.borrarPorId(1L);

        assertEquals("redirect:/usuarios", view);
        verify(usuarioRepository).deleteById(1L);
    }

    @Test
    void borrarTodo() {

        String view = usuarioController.borrarTodo();

        assertEquals("redirect:/usuarios", view);
        verify(usuarioRepository).deleteAll();
    }
}
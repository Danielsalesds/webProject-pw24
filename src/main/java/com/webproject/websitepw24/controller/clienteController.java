package com.webproject.websitepw24.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webproject.websitepw24.Classes.Cliente;
import com.webproject.websitepw24.DAO.ClienteDAO;

import java.io.IOException;


@Controller
public class clienteController {

    @RequestMapping(value = "/cadastroCliente", method = RequestMethod.POST)
    public void CadastrarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setSenha(senha);

        ClienteDAO clienteDao = new ClienteDAO();
        clienteDao.cadastrarCliente(cliente);

        response.sendRedirect("login.html");
    }


}

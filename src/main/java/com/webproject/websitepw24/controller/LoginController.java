package com.webproject.websitepw24.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.webproject.websitepw24.DAO.ClienteDAO;
import com.webproject.websitepw24.DAO.LojistaDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {
    @RequestMapping(value = "/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {        
        var login = request.getParameter("email");
        var password = request.getParameter("senha");

        ClienteDAO clienteDAO = new ClienteDAO();
        LojistaDAO lojistaDAO = new LojistaDAO();

        if(clienteDAO.verificarLogin(login, password)){
            HttpSession session = request.getSession();
            session.setAttribute("logado", true);
            session.setAttribute("lojista", true);
            response.sendRedirect("/paginaLojista");

        }else if(lojistaDAO.verificarLogin(login, password)){
            HttpSession session = request.getSession();
            session.setAttribute("logado", true);
            session.setAttribute("cliente",true);
            response.sendRedirect("/paginaLojista");
        }else{
            response.sendRedirect("login.html?msg=Login falhou");
        }
        
    }
    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("login.html?msg=Usuario deslogado");
    }
    
    
}

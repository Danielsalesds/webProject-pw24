package com.webproject.websitepw24.controller;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter({"/paginaCliente", "/paginaLojista"})
public class FiltroAutenticacao implements Filter{
    @Override
    public void init(FilterConfig filterconfig) throws ServletException{
        Filter.super.init(filterconfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException{
        
        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        HttpServletResponse response = ((HttpServletResponse) servletResponse);
        HttpSession session = request.getSession(false);

        if(session==null){
            response.sendRedirect("login.html?msg=necessario fazer login antes!");
            System.out.println("session=null");
        }else{
            Boolean logado = (Boolean) session.getAttribute("logado");
            if (!logado || logado == null) {
                response.sendRedirect("login.html?msg=necessario fazer login antes!");  
                System.out.println(logado);     
            }
        }
        System.out.println("entrou no dofilter!");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
    
}

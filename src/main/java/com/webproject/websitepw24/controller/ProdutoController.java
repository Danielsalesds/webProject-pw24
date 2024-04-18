package com.webproject.websitepw24.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webproject.websitepw24.Classes.Produto;
import com.webproject.websitepw24.DAO.ProdutoDAO;

import java.io.IOException;

@Controller
public class ProdutoController {
    @RequestMapping(value = "/cadastrarProdutos",method = RequestMethod.POST)
    public void cadastrarProdutos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int preco = Integer.parseInt(request.getParameter("precoProduto"));
        String nome = request.getParameter("nomeProduto");
        String descricao = request.getParameter("descricaoProduto");
        System.out.println(descricao);
        int estoque = Integer.parseInt(request.getParameter("estoqueProduto"));

        Produto produto = new Produto();
        produto.setPreco(preco);
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setEstoque(estoque);

        ProdutoDAO produtoDao = new ProdutoDAO();
        produtoDao.cadastrarProduto(produto);
        response.sendRedirect("/paginaLojista");
    }
}
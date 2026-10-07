package com.produtoapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.produtoapi.model.Produto;
import com.produtoapi.repository.ProdutoRepository;

public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> listarTodos(){
		return produtoRepository.findAll();
	}
	
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public void deletar(Long id) {
		produtoRepository.deleteById(id);
	}
	
	public Produto atualizar(Long id, Produto produto) {
		if(produtoRepository.existsById(id)) {
			produto.setId(id);
			return produtoRepository.save(produto);
		}else {
			throw new RuntimeException("produto não encontrado");
		}
	}

}

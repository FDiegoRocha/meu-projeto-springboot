package com.produtoapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.produtoapi.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	// Busca por nome
	List<Produto> findByNome(String nome);

	List<Produto> findByNomeContaining(String nome);

	List<Produto> findByNomeAndStatus(String nome, String status);

	List<Produto> findByNomeStartingWith(String prefix);

	List<Produto> findByNomeEndingWith(String suffix);

}

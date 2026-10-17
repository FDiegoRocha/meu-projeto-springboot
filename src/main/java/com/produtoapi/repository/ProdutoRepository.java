package com.produtoapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.produtoapi.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	// Busca por nome
	List<Produto> findByNome(String nome);

	List<Produto> findByNomeContaining(String nome);

	List<Produto> findByNomeAndStatus(String nome, String status);

	List<Produto> findByNomeStartingWith(String prefix);

	List<Produto> findByNomeEndingWith(String suffix);

	// Busca por preço
	List<Produto> findByPreco(Double preco);

	List<Produto> findByPrecoGreaterThan(Double preco);

	List<Produto> findByPrecoLessThan(Double preco);

	// Usando anottation @Query para obter total de preços
	@Query("SELECT SUM(p.preco) FROM Produto p")
	Double findTotalPreco();

}

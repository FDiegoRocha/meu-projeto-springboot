package com.produtoapi.client;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.produtoapi.model.Produto;


public class CRUDJavaClient {
	
	private static final String BASE_URL = "http://localhost:8080/produtos";
	private RestTemplate restTemplate;
	
	public CRUDJavaClient() {
		this.restTemplate = new RestTemplate();
	}
	
	public void listarTodos() {
		ResponseEntity<Produto[]> response = restTemplate.getForEntity(BASE_URL, Produto[].class);
		List<Produto> produtos = Arrays.asList(response.getBody());
		produtos.forEach(produto -> {
			System.out.println("ID: " + produto.getId());
			System.out.println("Nome: " + produto.getNome());
			System.out.println("Preço: " + produto.getPreco());
			System.out.println("Quantidade: " + produto.getQuantidade());
			System.out.println("Status: " + produto.getStatus());
			
		});
	}
	public Produto salvar(Produto produto) {
		HttpEntity<Produto> request = new HttpEntity<>(produto);
		return restTemplate.postForObject(BASE_URL, request, Produto.class);
	}
	public void deletar(long id) {
		restTemplate.delete(BASE_URL + "/" + id);
	}
	public Produto atualizar(Long id, Produto produto) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Produto> request = new HttpEntity<>(produto, headers);
		ResponseEntity<Produto> response = restTemplate.exchange(BASE_URL + "/" + id, HttpMethod.PUT, request, Produto.class);
		return response.getBody();	
	}
	public Produto findById(long id) {
		ResponseEntity<Produto> response = restTemplate.getForEntity(BASE_URL + "/" + id, Produto.class);
		return response.getBody();
	}
	
	public static void main(String[] args) {
		CRUDJavaClient client = new CRUDJavaClient();
		
		Produto novoProduto = new Produto();
		novoProduto.setNome("Skate Profissional");
		novoProduto.setPreco(300.00);
		novoProduto.setQuantidade(200);
		novoProduto.setStatus("Disponivel");
		System.out.println("Criar um novo produto");
		client.salvar(novoProduto);
		client.listarTodos();
		
		Produto atualizarProduto = client.findById(152);
		atualizarProduto.setNome("Skate Infantil");
		atualizarProduto.setPreco(100.69);
		System.out.println("Atualizar o Produto");
		client.atualizar(atualizarProduto.getId(), atualizarProduto);
		client.listarTodos();
//		
//		
		System.out.println("Deletar o produto");
		client.deletar(152);
		
		client.listarTodos();
		
		
	}
}

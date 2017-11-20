package space.indietech.produto;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoDao {

	private EntityManager em;
	
	@Autowired
	public ProdutoDao(EntityManager em) {
		super();
		this.em = em;
	}

	@Transactional
	public void adicionaProduto(ProdutoEntity produto) {
		em.merge(produto);
	}

	public List<ProdutoEntity> getListaProduto() {
		List<ProdutoEntity> produtos = em.createQuery("from ProdutoEntity").getResultList();
		return produtos;
	}

	@Transactional
	public void delete(long codigo) {
		ProdutoEntity produto = new ProdutoEntity();
		produto = em.find(ProdutoEntity.class, codigo);
		if (produto != null) {
			em.remove(produto);
		}
		
	}
	
	public ProdutoEntity getProdutoPeloCodigo(Long codigo) {
		return em.find(ProdutoEntity.class, codigo);
	}
}

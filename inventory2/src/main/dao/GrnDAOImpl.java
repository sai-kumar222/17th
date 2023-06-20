package main.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import main.models.entityModels.ImGrn;
import main.models.entityModels.ImGrnProducts;

@Component
public class GrnDAOImpl implements GrnDAO {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public boolean saveGrn(ImGrn imGrn) {
		em.persist(imGrn);

		List<ImGrnProducts> l = imGrn.getProductsList();
		System.out.println(l);
		for (ImGrnProducts i : l) {
			System.out.println(i);
			System.out.println(imGrn.getGrnId());
			i.setGrn_id(imGrn.getGrnId());

			em.persist(i);
		}
		return true;
	}

}
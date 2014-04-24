package br.com.ajovepcps.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.ajovepcps.entity.Member;
import br.com.ajovepcps.repository.AbstractRepository;
import br.com.ajovepcps.utils.ReflectionUtil;

@SuppressWarnings("unchecked")
public class MemberRepository extends AbstractRepository<Member> {

	@Override
	public List<Member> getAll() {

		List<Member> listMember = em.createNamedQuery("findAllMembers")
				.getResultList();

		return listMember;
	}
	
	public List<Member> findByAtts(Member member) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Member> cq = builder.createQuery(Member.class);
		Root<Member> patientRoot = cq.from(Member.class);
		List<Predicate> predicate = new ArrayList<Predicate>();

		// Obtém por reflection os atributos que foram preenchidos em tela
		ReflectionUtil ru = new ReflectionUtil(member);
		Map<Object, Object> filledAttrs = ru.getFilledAttributes();

		// Preenchendo dinamicamente as condições da consulta
		for (Object key : filledAttrs.keySet()) {
			if (filledAttrs.get(key) instanceof String)
				predicate
						.add(builder.like(builder.upper(patientRoot.<String> get(key.toString())), "%"
								+ filledAttrs.get(key).toString().toUpperCase()
								+ "%"));
			else
				predicate.add(builder.equal(patientRoot.get(key.toString()),
						filledAttrs.get(key)));
		}
		cq.where(predicate.toArray(new Predicate[predicate.size()]));
		List<Member> listMembers = em.createQuery(cq).getResultList();

		return listMembers;
	}

}

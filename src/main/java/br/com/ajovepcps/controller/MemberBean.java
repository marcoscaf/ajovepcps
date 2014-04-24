package br.com.ajovepcps.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.event.SelectEvent;

import br.com.ajovepcps.entity.Member;
import br.com.ajovepcps.exceptions.MemberConstraintViolationException;
import br.com.ajovepcps.factory.EntityManagerFactory;
import br.com.ajovepcps.model.MemberDataModel;
import br.com.ajovepcps.repository.impl.MemberRepository;

@ManagedBean(name = "memberMB")
@ViewScoped
public class MemberBean {

	private MemberDataModel memberDataModel;
	private List<Member> listMember;

	private Member member;

	private MemberRepository repository;

	// MemberServices memberServices;

	@PostConstruct
	public void init() {

		repository = new MemberRepository();

		listMember = new ArrayList<Member>();
		// listMember = repository.getAll();

		memberDataModel = new MemberDataModel(listMember);

		this.member = new Member();
	}

	public MemberDataModel getMemberDataModel() {
		return memberDataModel;
	}

	public void setMemberDataModel(MemberDataModel memberDataModel) {
		this.memberDataModel = memberDataModel;
	}

	public List<Member> getListMember() {
		return listMember;
	}

	public void setListMember(List<Member> listMember) {
		this.listMember = listMember;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public void onRowSelect(SelectEvent event) {

		this.member = (Member) event.getObject();
	}

	/**
	 * Método acionado via Ajax para salvar um membro
	 **/
	public void saveMember() {

		try {

			// Desatachando o objeto do Hibernate -
			// org.hibernate.PersistentObjectException: detached entity passed
			// to persist:
			if (member.getId() != null) {
				member = member.clone();
				member.setId(null);
			}

			listMember.add(member);

			repository.save(member);

			setFacesMessage(FacesMessage.SEVERITY_INFO, "msgsForm", "Membro "
					+ member.getName() + " salvo com sucesso");

		} catch (MemberConstraintViolationException e) {
			setFacesMessage(FacesMessage.SEVERITY_ERROR, "msgsForm", e
					.getCauseException().toString());
		} catch (Exception e) {
			if (e.getCause() instanceof ConstraintViolationException) {

				setFacesMessage(FacesMessage.SEVERITY_ERROR, "msgsForm",
						"Membro " + member.getName() + " já foi inserido");
			}
		}

	}

	/**
	 * Método acionado via Ajax para filtrar membros do banco de dados de acordo
	 * com os atributos preenchidos no form
	 **/
	public void findMembers() {

		listMember.addAll(repository.findByAtts(member));

	}

	public void cleanMemberInstance() {
		if (this.member != null) {
			this.member.clear();
		}

	}

	public void setFacesMessage(Severity severity, String clientId,
			String message) {
		FacesMessage mess = new FacesMessage(severity, message, null);
		FacesContext.getCurrentInstance().addMessage(clientId, mess);
	}
}

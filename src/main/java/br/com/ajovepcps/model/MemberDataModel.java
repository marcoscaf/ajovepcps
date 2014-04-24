package br.com.ajovepcps.model;



import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.com.ajovepcps.entity.Member;



public class MemberDataModel extends ListDataModel<Member> implements
		SelectableDataModel<Member> {

	public MemberDataModel(List<Member> memberlist) {
		super(memberlist);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Member getRowData(String rowKey) {

		List<Member> listMember = (List<Member>) getWrappedData();

		for (Member member : listMember) {
			if (member.toString().equals(rowKey))
				return member.clone();
		}
		return null;
	}

	@Override
	public Object getRowKey(Member patient) {
		return patient;
	}

}
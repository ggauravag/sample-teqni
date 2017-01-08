package org.pkb.springlogin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.pkb.springlogin.dto.MemberDTO;
import org.pkb.springlogin.dto.TeamMemberDTO;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "MEMBER")
public class Member {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	@OneToOne(mappedBy = "member")
	private TeamMember teamMember;

	public MemberDTO toDTO() {
		MemberDTO memberDTO = new MemberDTO();
		BeanUtils.copyProperties(this, memberDTO);

		if (this.getTeamMember() != null) {
			TeamMemberDTO teamMemberDTO = new TeamMemberDTO();
			BeanUtils.copyProperties(this.getTeamMember(), teamMemberDTO);
			memberDTO.setTeamMemberDTO(teamMemberDTO);
		}

		return memberDTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TeamMember getTeamMember() {
		return teamMember;
	}

	public void setTeamMember(TeamMember teamMember) {
		this.teamMember = teamMember;
	}

}

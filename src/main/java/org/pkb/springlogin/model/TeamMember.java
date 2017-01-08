package org.pkb.springlogin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.pkb.springlogin.dto.MemberDTO;
import org.pkb.springlogin.dto.TeamDTO;
import org.pkb.springlogin.dto.TeamMemberDTO;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "TEAM_MEMBER")
public class TeamMember {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JoinColumn(name = "X_TEAM_ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private Team team;

	@JoinColumn(name = "X_MEMBER_ID", unique = true)
	@OneToOne(fetch = FetchType.EAGER)
	private Member member;

	public TeamMemberDTO toDTO() {
		TeamMemberDTO teamMemberDTO = new TeamMemberDTO();
		BeanUtils.copyProperties(this, teamMemberDTO);

		MemberDTO memberDTO = new MemberDTO();
		BeanUtils.copyProperties(this.getMember(), memberDTO);
		teamMemberDTO.setMemberDTO(memberDTO);

		TeamDTO teamDTO = new TeamDTO();
		BeanUtils.copyProperties(this.getTeam(), teamDTO);
		teamMemberDTO.setTeamDTO(teamDTO);

		return teamMemberDTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}

package org.pkb.springlogin.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.pkb.springlogin.dto.TeamDTO;
import org.pkb.springlogin.dto.TeamMemberDTO;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "TEAM")
public class Team {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true, nullable = false)
	private String name;

	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy = "team", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<TeamMember> teamMembers;

	public TeamDTO toDTO() {
		TeamDTO teamDTO = new TeamDTO();
		BeanUtils.copyProperties(this, teamDTO);

		if (this.getTeamMembers() != null) {
			List<TeamMemberDTO> dtos = new ArrayList<>();
			for (TeamMember teamMember : this.getTeamMembers()) {
				dtos.add(teamMember.toDTO());
			}
			teamDTO.setTeamMembers(dtos);
		}

		return teamDTO;
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

	public List<TeamMember> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(List<TeamMember> teamMembers) {
		this.teamMembers = teamMembers;
	}

}

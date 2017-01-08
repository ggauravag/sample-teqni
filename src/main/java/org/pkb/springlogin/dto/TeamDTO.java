package org.pkb.springlogin.dto;

import java.util.List;

public class TeamDTO {

	private Long id;

	private String name;

	private List<TeamMemberDTO> teamMembers;

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

	public List<TeamMemberDTO> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(List<TeamMemberDTO> teamMembers) {
		this.teamMembers = teamMembers;
	}

}

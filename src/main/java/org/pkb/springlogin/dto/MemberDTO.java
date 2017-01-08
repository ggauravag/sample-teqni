package org.pkb.springlogin.dto;

public class MemberDTO {

	private Long id;

	private String name;

	private TeamMemberDTO teamMemberDTO;

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

	public TeamMemberDTO getTeamMemberDTO() {
		return teamMemberDTO;
	}

	public void setTeamMemberDTO(TeamMemberDTO teamMemberDTO) {
		this.teamMemberDTO = teamMemberDTO;
	}

}

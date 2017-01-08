package org.pkb.springlogin.service;

import java.util.List;

import org.pkb.springlogin.dto.MemberDTO;
import org.pkb.springlogin.dto.TeamDTO;

public interface ITeamService {

	List<MemberDTO> getMembers();

	List<TeamDTO> getTeams();

	void updateTeam(TeamDTO team);

}

package org.pkb.springlogin.dao;

import java.util.List;

import org.pkb.springlogin.model.Member;
import org.pkb.springlogin.model.Team;
import org.pkb.springlogin.model.TeamMember;

public interface ITeamDAO {

	void updateTeam(Team team);

	List<Team> getTeams();

	List<Member> getMembers();

	Team getTeam(String name);

	Team getTeam(Long id);

	Member getMember(Long id);

	TeamMember getTeamMember(Long id);

	void updateTeamMember(TeamMember teamMember);

	TeamMember getTeamMemberById(Long memberId);

	void deleteTeamMember(TeamMember teamMember);

}

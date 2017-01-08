package org.pkb.springlogin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.pkb.springlogin.dao.ITeamDAO;
import org.pkb.springlogin.dto.MemberDTO;
import org.pkb.springlogin.dto.TeamDTO;
import org.pkb.springlogin.dto.TeamMemberDTO;
import org.pkb.springlogin.model.Member;
import org.pkb.springlogin.model.Team;
import org.pkb.springlogin.model.TeamMember;
import org.pkb.springlogin.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService implements ITeamService {

	@Autowired
	private ITeamDAO teamDAO;

	@Override
	public List<MemberDTO> getMembers() {

		List<MemberDTO> memberDTOs = new ArrayList<>();
		for (Member member : teamDAO.getMembers()) {
			memberDTOs.add(member.toDTO());
		}

		return memberDTOs;
	}

	@Override
	public void updateTeam(TeamDTO team) {
		Team teamDb = this.teamDAO.getTeam(team.getId());
		List<TeamMember> originalMember = teamDb.getTeamMembers();
		List<TeamMember> members = new ArrayList<>();
		for (TeamMemberDTO teamMemberDTO : team.getTeamMembers()) {
			Long id = teamMemberDTO.getMemberDTO().getId();
			Member member = this.teamDAO.getMember(id);
			TeamMember teamMember = this.teamDAO.getTeamMemberById(member.getId());
			if (teamMember == null)
				teamMember = new TeamMember();

			teamMember.setMember(member);
			teamMember.setTeam(teamDb);
			members.add(teamMember);
		}

		for (TeamMember member : originalMember) {
			boolean isThere = false;
			for (TeamMember newMember : members) {
				if (newMember.getMember().getId().equals(member.getMember().getId())) {
					isThere = true;
					break;
				}
			}

			if (!isThere) {
				System.out.println("Delete member id: " + member.getId());
				this.teamDAO.deleteTeamMember(member);
			}
		}

		teamDb.setTeamMembers(members);
		this.teamDAO.updateTeam(teamDb);
	}

	@Override
	public List<TeamDTO> getTeams() {
		List<TeamDTO> teamDTOs = new ArrayList<>();
		for (Team team : teamDAO.getTeams()) {
			teamDTOs.add(team.toDTO());
		}

		return teamDTOs;
	}

}

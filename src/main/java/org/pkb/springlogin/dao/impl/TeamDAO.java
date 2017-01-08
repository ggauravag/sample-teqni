package org.pkb.springlogin.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.pkb.springlogin.dao.ITeamDAO;
import org.pkb.springlogin.model.Member;
import org.pkb.springlogin.model.Team;
import org.pkb.springlogin.model.TeamMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TeamDAO implements ITeamDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void updateTeam(Team team) {
		Session session = sessionFactory.getCurrentSession();
		session.update(team);
	}

	@Override
	public Team getTeam(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (Team) session.get(Team.class, id);
	}

	@Override
	public TeamMember getTeamMember(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (TeamMember) session.get(TeamMember.class, id);
	}

	@Override
	public void deleteTeamMember(TeamMember teamMember) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(teamMember);
	}

	@Override
	public TeamMember getTeamMemberById(Long memberId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(TeamMember.class);
		criteria.add(Restrictions.eq("member.id", memberId));
		return (TeamMember) criteria.uniqueResult();
	}

	@Override
	public void updateTeamMember(TeamMember teamMember) {
		Session session = sessionFactory.getCurrentSession();
		session.update(teamMember);
	}

	@Override
	public Member getMember(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (Member) session.get(Member.class, id);
	}

	@Override
	public Team getTeam(String name) {
		Session session = sessionFactory.getCurrentSession();
		Criteria createCriteria = session.createCriteria(Team.class);
		createCriteria.add(Restrictions.eq("name", name));
		return (Team) createCriteria.uniqueResult();
	}

	@Override
	public List<Team> getTeams() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Team.class);
		System.out.println(criteria.list().size());
		return criteria.list();
	}

	@Override
	public List<Member> getMembers() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Member.class);
		return criteria.list();
	}

}

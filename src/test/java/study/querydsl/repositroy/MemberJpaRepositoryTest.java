package study.querydsl.repositroy;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.dto.MemberSearchCondition;
import study.querydsl.dto.MemberTeamDto;
import study.querydsl.entity.Member;
import study.querydsl.entity.Team;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void basicTest() throws Exception {
        //given
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        //when
        Member findMember = memberJpaRepository.findById(member.getId()).get();

        //then
        assertEquals(findMember, member);

        List<Member> result1 = memberJpaRepository.findAll();
        assertEquals(result1.size(), 1);

        List<Member> result2 = memberJpaRepository.findByUserName("member1");
        assertEquals(result2.size(), 1);
        assertEquals(result2.get(0), member);
    }

    @Test
    public void basicQuerydslTest() throws Exception {
        //given
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        //when
        Member findMember = memberJpaRepository.findById(member.getId()).get();

        //then
        assertEquals(findMember, member);

        List<Member> result1 = memberJpaRepository.findAll_Querydsl();
        assertEquals(result1.size(), 1);

        List<Member> result2 = memberJpaRepository.findByUserName_Querydsl("member1");
        assertEquals(result2.size(), 1);
        assertEquals(result2.get(0), member);
    }

    @Test
    public void searchBuilderTest() throws Exception {
        //given
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA); //영속성 컨텍스트에 저장
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamB);
        Member member3 = new Member("member3", 30, teamA);
        Member member4 = new Member("member4", 40, teamB);
        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);
        memberJpaRepository.save(member3);
        memberJpaRepository.save(member4);

        //when
        List<MemberTeamDto> result1 = memberJpaRepository.searchByBuilder(new MemberSearchCondition());

        List<MemberTeamDto> result2 = memberJpaRepository.searchByBuilder(new MemberSearchCondition("member1"));

        List<MemberTeamDto> result3 = memberJpaRepository.searchByBuilder(new MemberSearchCondition("", "teamB", 10, 40));
        //then
        assertEquals(result1.size(), 4);
        assertEquals(result2.size(), 1);
        assertEquals(result3.size(), 2);

    }

    @Test
    public void searchTest() throws Exception {
        //given
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA); //영속성 컨텍스트에 저장
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamB);
        Member member3 = new Member("member3", 30, teamA);
        Member member4 = new Member("member4", 40, teamB);
        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);
        memberJpaRepository.save(member3);
        memberJpaRepository.save(member4);

        //when
        List<MemberTeamDto> result1 = memberJpaRepository.search(new MemberSearchCondition());

        List<MemberTeamDto> result2 = memberJpaRepository.search(new MemberSearchCondition("member1"));

        List<MemberTeamDto> result3 = memberJpaRepository.search(new MemberSearchCondition("", "teamB", 10, 40));
        //then
        assertEquals(result1.size(), 4);
        assertEquals(result2.size(), 1);
        assertEquals(result3.size(), 2);

    }

}
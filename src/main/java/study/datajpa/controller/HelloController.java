package study.datajpa.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Transactional
public class HelloController {

    private final EntityManager em;

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @GetMapping("/test")
    public List<TeamResponseDto> team(){
        List<Team> teams = em.createQuery("select t from Team t join t.members m", Team.class)
                .setFirstResult(0)
                .setMaxResults(100)
                .getResultList();
        List<TeamResponseDto> teamResponseDtos = teams.stream().map(t -> new TeamResponseDto(t)).collect(Collectors.toList());
        return teamResponseDtos;
    }
    @Data
    static class TeamResponseDto{
        private Long id;
        private String name;
        private List<MemberResponseDto> members;

        public TeamResponseDto(Team team){
            this.id = team.getId();
            this.name = team.getName();
            this.members = team.getMembers()
                    .stream()
                    .map(m -> new MemberResponseDto(m))
                    .collect(Collectors.toList());
        }
    }
    @Data
    static class MemberResponseDto{
        private String username;
        private int age;

        public MemberResponseDto(Member member){
            this.username = member.getUsername();
            this.age = member.getAge();
        }
    }
}

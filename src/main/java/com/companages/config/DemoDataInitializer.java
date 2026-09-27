package com.companages.config;

import com.companages.entity.*;
import com.companages.repository.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Component
@ConditionalOnProperty(name="app.demo.enabled",havingValue="true")
public class DemoDataInitializer implements ApplicationRunner {
 private final UserRepository users; private final OrganizationRepository organizations; private final MemberRepository members; private final TeamRepository teams; private final PositionRepository positions; private final PasswordEncoder encoder;
 public DemoDataInitializer(UserRepository u,OrganizationRepository o,MemberRepository m,TeamRepository t,PositionRepository p,PasswordEncoder e){users=u;organizations=o;members=m;teams=t;positions=p;encoder=e;}
 @Override @Transactional public void run(ApplicationArguments args){if(users.existsByEmailIgnoreCase("demo@companages.local"))return;User user=new User();user.setName("Demo Owner");user.setEmail("demo@companages.local");user.setPassword(encoder.encode("demo12345"));users.save(user);Organization org=new Organization();org.setName("Acme Demo");org.setDescription("A ready-to-explore Companages workspace");org.setEmail("hello@acme.demo");org.setOwner(user);organizations.save(org);Position director=position(org,"Director",1);Position engineer=position(org,"Software Engineer",4);Position designer=position(org,"Product Designer",4);Team product=team(org,"Product","Builds the customer experience");Team platform=team(org,"Platform","Owns reliable foundations");Member owner=member(org,user,"Demo Owner","demo@companages.local",AccessRole.OWNER,director,product,null);Member lead=member(org,null,"Marina Costa","marina@acme.demo",AccessRole.MANAGER,engineer,platform,owner);Member dev=member(org,null,"Caio Lima","caio@acme.demo",AccessRole.MEMBER,engineer,platform,lead);member(org,null,"Bia Santos","bia@acme.demo",AccessRole.MEMBER,designer,product,owner);product.setLead(owner);platform.setLead(lead);}
 private Position position(Organization o,String name,int level){Position p=new Position();p.setOrganization(o);p.setName(name);p.setLevel(level);return positions.save(p);} private Team team(Organization o,String name,String description){Team t=new Team();t.setOrganization(o);t.setName(name);t.setDescription(description);return teams.save(t);} private Member member(Organization o,User u,String name,String email,AccessRole role,Position p,Team t,Member manager){Member m=new Member();m.setOrganization(o);m.setUser(u);m.setName(name);m.setEmail(email);m.setAccessRole(role);m.setPosition(p);m.setTeam(t);m.setManager(manager);m.setJoinedAt(LocalDate.now().minusMonths(2));return members.save(m);}
}

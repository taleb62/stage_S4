package org.sid;

import org.sid.entites.AppRole;
import org.sid.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistration;

@SpringBootApplication
public class SecuritySerApplication {

    private RepositoryRestConfiguration repositoryRestConfiguration;
    public static void main(String[] args) {
        SpringApplication.run(SecuritySerApplication.class, args);
    }

    @Bean
    CommandLineRunner start (AccountService accountService){
        return args -> {
         //  accountService.save(new AppRole(null,"SAISIE","saisie des mouvements"));
//           accountService.save(new AppRole(null,"CONSULTATION","Consultation des Consomation"));
         //  accountService.save(new AppRole(null,"ADMINISTRATEUR", "Gestion des utilisateurs"));
          // accountService.save(new AppRole(null,"VALIDATION","Valide la saisie des utilisateurs"));
            //accountService.save(new AppRole(null,"SUPER","Droits absolues"));



//            Stream.of("user1","user2","DGB","admin").forEach(un->{
//                accountService.saveUser(un,"1234","1234");
//            });
//            accountService.saveUser("user1","user2","user3","Utilisateur","1234","1234");
//            accountService.saveUser("DGB","DGBD1","DGBD2","Utilisateur1","1234","1234");
            //accountService.saveUser("admin","admin1","admine2","Administrateur","1234","1234", Arrays.asList("ADMINISTRATEUR","SAISIE","VALIDATION"));
////////
//            accountService.addRoleToUser("admin","SAISIE");
//            accountService.addRoleToUser("admin","CONSULTATION");
    //        accountService.addRoleToUser("admin","ADMINISTRATEUR");
//            accountService.addRoleToUser("admin","VALIDATION");
//            //accountService.addRoleToUser("admin","SUPER");
           //accountService.addRoleToUser("DGB","CONSULTATION");
           //accountService.addRoleToUser("user1","CONSULTATION");
           //accountService.addRoleToUser("user1","SAISIE");
//
//            //attribuer des ministere a des utilisateurs
//
//           accountService.addMinistereToUser("admin","67");

        };
    }
   @Bean
    BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
    }
}

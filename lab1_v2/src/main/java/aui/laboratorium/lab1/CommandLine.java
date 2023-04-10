package aui.laboratorium.lab1;

import aui.laboratorium.lab1.entity.Club;
import aui.laboratorium.lab1.entity.Footballer;
import aui.laboratorium.lab1.service.ClubService;
import aui.laboratorium.lab1.service.FootballerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {

    ClubService clubService;
    FootballerService footballerService;
    @Autowired
    public CommandLine(ClubService clubService, FootballerService footballerService) {
        this.clubService = clubService;
        this.footballerService = footballerService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("WRITE \"help\" - to show the available commands");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command;
            command = sc.nextLine();
            if (command.equals("quit")) {
                break;
            }
            else if (command.equals("help")) {
                System.out.println("AVAILABLE COMMANDS:");
                System.out.println("\"help\" - to show the commands");
                System.out.println("\"quit\" - to exit the aplication");
                System.out.println("\"listAllF\" - to list all the footballers");
                System.out.println("\"listAllC\" - to list all the clubs");
                System.out.println("\"findFootballer\" - to find a footballer by id");
                System.out.println("\"findClub\" - to find a club by Name");
                System.out.println("\"addFootballer\" - to add new footballer");
                System.out.println("\"deleteFootballer\" - to remove the footballer");
                System.out.println("\"deleteClub\" - to remove the club");
            }
            else if (command.equals("listAllF")) {
                System.out.println("LIST OF ALL FOOTBALLERS:");
                footballerService.findAll().forEach(System.out::println);
            }
            else if (command.equals("listAllC")) {
                System.out.println("LIST OF ALL CLUBS:");
                clubService.findAll().forEach(System.out::println);
            }
            else if (command.equals("findFootballer")) {
                System.out.println("WRITE ID:");
                long id = sc.nextLong();
                sc.nextLine();
                if(footballerService.findById(id).isPresent()) {
                    System.out.println(footballerService.findById(id).get());
                }
                else {
                    System.out.println("FOOTBALLER NOT FOUND");
                }

            }
            else if (command.equals("deleteFootballer")) {
                System.out.println("WRITE ID:");
                long id = sc.nextLong();
                sc.nextLine();
                footballerService.deleteById(id);
            }
            else if (command.equals("deleteClub")) {
                System.out.println("WRITE THE CLUB NAME:");
                String name = sc.nextLine();
                clubService.deleteByName(name);
            }
            else if (command.equals("findClub")) {
                System.out.println("WRITE NAME:");
                String name = sc.nextLine();
                if(clubService.findByName(name).isPresent()) {
                    System.out.println(clubService.findByName(name).get());
                }
                else {
                    System.out.println("CLUB NOT FOUND");

                }

            }
            else if (command.equals("deleteClub")) {
                System.out.println("WRITE NAME:");
                String name = sc.nextLine();
                clubService.deleteByName(name);
            }
            else if (command.equals("addFootballer")) {
                long id;
                String fullName;
                double rating;
                int age;

                System.out.println("WRITE ID:");
                id = sc.nextLong();
                System.out.println("WRITE AGE:");
                age = sc.nextInt();
                sc.nextLine();
                System.out.println("WRITE NAME:");
                fullName = sc.nextLine();
                System.out.println("WRITE RATING:");
                rating = sc.nextDouble();
                System.out.println("CHOOSE NUMBER OF CLUB FROM:");
                List<Club> clubs = clubService.findAll();
                int i = 1;
                for (Club club : clubs) {
                    System.out.println(Integer.toString(i) + ". " + club);
                    i++;
                }
                int clubNumber = sc.nextInt();
                while (clubNumber < 0 || clubNumber > clubs.size()) {
                    System.out.println("CHOOSE CORRECT NUMBER OF CLUB:");
                    clubNumber = sc.nextInt();
                }
                Footballer addedFootballer = Footballer.builder()
                        .fullName(fullName)
                        .age(age)
                        .averageRating(rating)
                        .club(clubs.get(clubNumber - 1))
                        .id(id)
                        .build();

                footballerService.saveNew(addedFootballer);
            }
        }
    }
}
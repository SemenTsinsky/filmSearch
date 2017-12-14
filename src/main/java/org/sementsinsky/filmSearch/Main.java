package org.sementsinsky.filmSearch;

import org.sementsinsky.filmSearch.dao.Repository;
import org.sementsinsky.filmSearch.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Repository<Producer> producerRepository = new Repository<>(Producer.class);
        Repository<User> userRepository = new Repository<>(User.class);
        Repository<Film> filmRepository = new Repository<>(Film.class);
        Repository<Mark> markRepository = new Repository<>(Mark.class);
        try {
            producerRepository.openSessionWithTransaction();
            List<Producer> producers = producerRepository.getAll();
            producerRepository.closeSessionWithTransaction();
            System.out.println("get producers");
            filmRepository.openSessionWithTransaction();
            Random random = new Random();
            List<Film> films = new ArrayList<>();
            for(Producer producer:producers){
                for(int i = 0; i < 5; i++){
                    films.add(new Film("film"+producer.getId()+i,random.nextDouble()*10,producer,1917+random.nextInt()*100));
                }
            }
            for(Film film : films){
                filmRepository.persist(film);
            }
            filmRepository.closeSessionWithTransaction();
            System.out.println("films added");
            userRepository.openSessionWithTransaction();
            List<User> users = new ArrayList<>();
            for(int i = 0; i < 1000; i++){
                users.add(new User("user"+i,"password"+i));
            }
            for(User user: users){
                userRepository.persist(user);
            }
            userRepository.closeSessionWithTransaction();
            System.out.println("users added");
            List<Mark> marks = new ArrayList<>();
            markRepository.openSessionWithTransaction();
            for(User user : users){
                for(Film film : films){
                    marks.add(new Mark(user,film,random.nextInt()*10));
                    user.addHoldOver(film);
                }
            }
            for(Mark mark : marks){
                markRepository.persist(mark);
            }
            markRepository.closeSessionWithTransaction();
            System.out.println("marks added");
            userRepository.openSessionWithTransaction();
            for(User user : users){
                userRepository.update(user);
            }
            userRepository.closeSessionWithTransaction();
            System.out.println("users updated");
            System.out.println("all done");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}

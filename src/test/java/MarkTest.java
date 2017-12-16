import com.sun.org.apache.regexp.internal.RE;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sementsinsky.filmSearch.dao.MarkRepository;
import org.sementsinsky.filmSearch.dao.Repository;
import org.sementsinsky.filmSearch.entities.Film;
import org.sementsinsky.filmSearch.entities.Mark;
import org.sementsinsky.filmSearch.entities.Producer;
import org.sementsinsky.filmSearch.entities.User;

import java.util.Date;
import java.util.List;

public class MarkTest {

    private static Mark mark;
    private static Mark test;
    private static Mark mark1;
    private static Mark mark2;
    private static Mark mark3;
    private static Film film;
    private static User user;
    private static User user1;
    private static User user2;
    private static User user3;
    private static Producer producer;
    private static MarkRepository repository;
    private static Repository<Film> filmRepository;
    private static Repository<User> userRepository;
    private static Repository<Producer> producerRepository;

    @BeforeClass
    public static void setUp(){
        producerRepository = new Repository<>(Producer.class);
        producer = new Producer("producerproducer",new Date(1995,12,12));
        producerRepository.persist(producer);
        film = new Film("filmfilm",8,producer,1990);
        user = new User("useruser","parol");
        user1 = new User("useruser1","parol");
        user2 = new User("usersuer2","parol");
        user3 = new User("userser3","parol");
        mark = new Mark(user,film,10);
        repository = new MarkRepository(Mark.class);
        filmRepository = new Repository<>(Film.class);
        userRepository = new Repository<>(User.class);
        filmRepository.persist(film);
        userRepository.persist(user);
        userRepository.persist(user1);
        userRepository.persist(user2);
        userRepository.persist(user3);
        repository.persist(mark);
        mark1 = new Mark(user1,film,10);
        mark2 = new Mark(user2,film,6);
        mark3 = new Mark(user3,film,8);
        repository.persist(mark1);
        repository.persist(mark2);
        repository.persist(mark3);
    }

    @Test
    public void persistTest(){
        test = repository.getById(mark.getId());
        Assert.assertTrue(test.equals(mark));
    }

    @Test
    public void updateTest(){
        mark.setMark(8);
        repository.update(mark);
        test = repository.getById(mark.getId());
        Assert.assertTrue(test.equals(mark));
    }

    @Test
    public void getAllTest(){
        List<Mark> marks = repository.getAll();
        Assert.assertTrue(marks.size() == 4);
    }

    @AfterClass
    public static void tearDown(){
        repository.delete(mark);
        repository.delete(mark1);
        repository.delete(mark2);
        repository.delete(mark3);
        filmRepository.delete(film);
        userRepository.delete(user1);
        userRepository.delete(user2);
        userRepository.delete(user3);
        userRepository.delete(user);
        producerRepository.delete(producer);
    }
}

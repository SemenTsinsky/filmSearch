import org.junit.*;
import org.sementsinsky.filmSearch.dao.Repository;
import org.sementsinsky.filmSearch.dao.UserRepository;
import org.sementsinsky.filmSearch.entities.Film;
import org.sementsinsky.filmSearch.entities.Mark;
import org.sementsinsky.filmSearch.entities.Producer;
import org.sementsinsky.filmSearch.entities.User;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class UserTest {

    private static User user;
    private static User test;
    private static UserRepository repository;
    private static Film film;
    private static Producer producer;
    private static Repository<Producer> producerRepository;
    private static Repository<Film> filmRepository;
    private static Mark mark;

    @BeforeClass
    public static void setUp(){
        repository = new UserRepository(User.class);
        user = new User("userName10000001100000","password1");
        producer = new Producer("producer1111111121",new Date(1980,01,01));
        film = new Film("film11111111121",9.5,producer,2010);
        producerRepository = new Repository<>(Producer.class);
        producerRepository.persist(producer);
        filmRepository = new Repository<>(Film.class);
        filmRepository.persist(film);
    }

    @Test
    public void persistTest(){
        repository.persist(user);
        test = repository.getById(user.getId());
        Assert.assertTrue(user.equals(test));
    }

    @Test
    public void updateTest(){
        user.setUsername("userName5000");
        repository.update(user);
        test = repository.getById(user.getId());
        Assert.assertTrue(user.equals(test));
    }

    @Test
    public void getByValueTest(){
        test = repository.getByValue("username",user.getUsername());
        Assert.assertTrue(user.equals(test));
    }

//    @Test
//    public void getSetMarkTest(){
//        mark = new Mark(user,film,10);
//        repository.markFilm(mark);
//        List<Mark> marks = repository.getMarks(user);
//        Assert.assertTrue(mark.equals(marks.get(0)));
//    }

    @Test
    public void getSetHoldOverTest(){
        repository.holdOver(user,film);
        Set<Film> films = repository.getHoldOverSet(user);
        Assert.assertTrue(films.contains(film));
    }

    @Test
    public void delFromHoldOverTest(){
        repository.delFromHoldOver(user,film);
        Set<Film> films = repository.getHoldOverSet(user);
        Assert.assertTrue(!films.contains(film));
    }

    @Test
    public void deleteTest(){
        repository.delete(user);
        Assert.assertTrue(repository.getById(user.getId()) == null);
    }

    @AfterClass
    public static void tearDown(){
        repository.deleteMark(mark);
        repository.delete(user);
        filmRepository.delete(film);
        producerRepository.delete(producer);
    }

}

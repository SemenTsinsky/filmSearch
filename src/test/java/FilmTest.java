import com.sun.org.apache.regexp.internal.RE;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sementsinsky.filmSearch.dao.FilmRepository;
import org.sementsinsky.filmSearch.dao.Repository;
import org.sementsinsky.filmSearch.entities.Film;
import org.sementsinsky.filmSearch.entities.Mark;
import org.sementsinsky.filmSearch.entities.Producer;

import java.util.Date;
import java.util.List;

public class FilmTest {

    private static Film film;
    private static Film test;
    private static Film film1;
    private static Film film2;
    private static Film film3;
    private static FilmRepository repository;
    private static Producer producer;
    private static Repository<Producer> producerRepository;
    private static Repository<Mark> markRepository;

    @BeforeClass
    public static void setUp(){
        repository = new FilmRepository(Film.class);
        producer = new Producer("produ1010",new Date(1980,2,10));
        producerRepository = new Repository<>(Producer.class);
        producerRepository.persist(producer);
        film = new Film("filmName1010",10.0,producer,2005);
        markRepository = new Repository<>(Mark.class);
        repository.persist(film);
    }

    @Test
    public void persistTest(){
        test = repository.getById(film.getId());
        Assert.assertTrue(film.equals(test));
    }

    @Test
    public void getByValueTest(){
        test = repository.getByValue("name",film.getName());
        Assert.assertTrue(film.equals(test));
    }

    @Test
    public void getByIdTest(){
        test = repository.getById(film.getId());
        Assert.assertTrue(film.equals(test));
    }

    @Test
    public void updateTest(){
        film.setRating(9);
        repository.update(film);
        test = repository.getById(film.getId());
        Assert.assertTrue(film.equals(test));
    }

//    @Test
//    public void updateRatingTest() {
//        repository.updateRating(film);
//        List<Mark> marks = markRepository.getASetByValue("film", film.getId().toString());
//        double rating = 0;
//        int i = 0;
//        for(Mark mark : marks){
//            rating += mark.getMark();
//            i++;
//        }
//        rating /= i;
//        Assert.assertTrue(film.getRating() == rating);
//    }

    @Test
    public void getAllTest(){
        film1 = new Film();
        film2 = new Film();
        film3 = new Film();
        repository.persist(film1);
        repository.persist(film2);
        repository.persist(film3);
        List<Film> films = repository.getAll();
        Assert.assertTrue(films.size() == 4);
    }

    @AfterClass
    public static void tearDown(){
        repository.delete(film1);
        repository.delete(film2);
        repository.delete(film3);
        repository.delete(film);
        producerRepository.delete(producer);
    }
}

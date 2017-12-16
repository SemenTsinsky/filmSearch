import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sementsinsky.filmSearch.dao.ProducerRepository;
import org.sementsinsky.filmSearch.dao.Repository;
import org.sementsinsky.filmSearch.entities.Film;
import org.sementsinsky.filmSearch.entities.Producer;

import java.util.Date;
import java.util.List;

public class ProducerTest {

    private static Producer producer;
    private static Producer test;
    private static Producer producer1;
    private static Producer producer2;
    private static Producer producer3;
    private static ProducerRepository repository;
    private static Film film1;
    private static Film film2;
    private static Film film3;
    private static Repository<Film> filmRepository;

    @BeforeClass
    public static void setUp(){
        producer = new Producer("producerName10101010", new Date(1980,1,1));
        repository = new ProducerRepository(Producer.class);
        repository.persist(producer);
        film1 = new Film("film1Name",9,producer,2000);
        film2 = new Film("film2Name",9,producer,2001);
        film3 = new Film("film3Name",9,producer,2002);
        filmRepository = new Repository<>(Film.class);
        filmRepository.persist(film1);
        filmRepository.persist(film2);
        filmRepository.persist(film3);
        producer1 = new Producer();
        producer2 = new Producer();
        producer3 = new Producer();
        repository.persist(producer1);
        repository.persist(producer2);
        repository.persist(producer3);
    }

    @Test
    public void persistTest(){
        test = repository.getById(producer.getId());
        Assert.assertTrue(producer.equals(test));
    }

    @Test
    public void updateTest(){
        producer.setName("producer2Name");
        repository.update(producer);
        test = repository.getById(producer.getId());
        Assert.assertTrue(test.equals(producer));
    }

    @Test
    public void getAllTest(){
        List<Producer> producers = repository.getAll();
        Assert.assertTrue(producers.size() == 4);
    }

//    @Test
//    public void getFilmsTest(){
//        List<Film> films = repository.getFilms(producer);
//        if(!films.contains(film1)) Assert.assertTrue(false);
//        if(!films.contains(film2)) Assert.assertTrue(false);
//        if(!films.contains(film3)) Assert.assertTrue(false);
//        Assert.assertTrue(true);
//    }

    @AfterClass
    public static void tearDown(){
        filmRepository.delete(film1);
        filmRepository.delete(film2);
        filmRepository.delete(film3);
        repository.delete(test);
        if(repository.getById(producer.getId()) != null){
            repository.delete(producer);
        }
        repository.delete(producer1);
        repository.delete(producer2);
        repository.delete(producer3);

    }
}

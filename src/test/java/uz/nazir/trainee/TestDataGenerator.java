package uz.nazir.trainee;

import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.CrudRepository;
import uz.nazir.trainee.mappers.GenericMapper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Generates data in DataBase
 */
public class TestDataGenerator {
    /**
     * initialized random - ready for use
     */
    public static final Random RANDOM = new Random();

    /**
     * Instance for singleton pattern
     */
    private static TestDataGenerator instance = null;

    /**
     * Make Constructor private as we use Singleton pattern
     */
    private TestDataGenerator() {
    }

    /**
     * SINGLETON
     */
    public static TestDataGenerator getInstance() {
        if (instance == null) {
            synchronized (TestDataGenerator.class) {
                if (instance == null) {
                    instance = new TestDataGenerator();
                }
            }
        }
        return instance;
    }

    /**
     * <div>Puts data to DB on specific table</div>
     * To put data use lambda expression on @param createEntity
     *
     * @param context      SpringBoot's context
     * @param count        How many times entity should be created (Use RANDOM.nextInt() to generate random names)
     * @param repoClass    Your repository interface which extends from {@link CrudRepository}
     * @param mapperClass  Your mapper interface which extends from {@link  GenericMapper}
     * @param createEntity Use lambda expression to implement creation of entity
     * @param <E>          DTO Response
     * @param <T>          Entity
     * @param <R>          DTo Request
     * @return Created entities in List
     */
    public <E, T, R> List<E> createAndGetElements(ApplicationContext context,
                                                  int count,
                                                  Class<? extends CrudRepository<T, Long>> repoClass,
                                                  Class<? extends GenericMapper<T, R, E>> mapperClass,
                                                  Command<T> createEntity) {
        List<E> result = new ArrayList<>();
        var repository = context.getBean(repoClass);
        var mapper = context.getBean(mapperClass);

        for (int i = 0; i < count; i++) {
            var created = createEntity.invoke();
            var saved = repository.save(created);
            result.add(mapper.entityToDto(saved));
        }
        return result;
    }
}

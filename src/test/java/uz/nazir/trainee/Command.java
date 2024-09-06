package uz.nazir.trainee;

/**
 * Pattern COMMAND Visit for explanation
 * <a href="https://refactoring.guru/design-patterns/command">Refactoring.guru</a>
 *
 * @param <T> DataType
 */
@FunctionalInterface
public interface Command<T> {
    T invoke();
}

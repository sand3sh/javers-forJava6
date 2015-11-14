package org.javers.common.collections;

import org.javers.common.validation.Validate;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_SET;

/**
 * @author Maciej Zasada
 */
public class Sets {

    private Sets() {
    }

    /**
     * null args are allowed
     */
    public static <E> Set<E> intersection(Set<E> first, Set<E> second) {
        if (first == null || second == null) {
            return EMPTY_SET;
        }

        Set<E> intersection = new HashSet<E>();

        for (E e : first) {
            if (second.contains(e)) {
                intersection.add(e);
            }
        }
        return intersection;
    }

    public static <E> Set<E> xor(Set<E> first, Set<E> second) {
        Set<E> xor = difference(first, second);
        xor.addAll(difference(second, first));

        return xor;
    }

    /**
     * null args are allowed
     */
    public static <E> Set<E> difference(Set<E> first, Set<E> second) {
        if (first == null) {
            return EMPTY_SET;
        }

        if (second == null) {
            return first;
        }

        Set<E> difference = new HashSet<E>(first);
        difference.removeAll(second);
        return difference;
    }

    public static <E> Set<E> asSet(E... elements) {
        return asSet(asList(elements));
    }

    public static <E> Set<E> asSet(Collection<E> elements) {
        return new HashSet<E>(elements);
    }

    public static <F, T> Set<T> transform(Set<F> input, Function<F, T> transformation) {
        Validate.argumentIsNotNull(input);
        Validate.argumentIsNotNull(transformation);

        Set<T> result = new HashSet<T>();
        for (F element : input) {
            result.add(transformation.apply(element));
        }
        return result;
    }

    private static <E> Set<E> nullSafe(Set<E> set) {
        if (set == null) {
            return EMPTY_SET;
        }
        return set;
    }

    /**
     * @return index -> value
     */
    public static <T> Map<Integer, T> asMap(Set<T> input) {
        if (input == null){
            return null;
        }

        Map<Integer, T> result = new HashMap<Integer, T>();
        int i = 0;

        for (T element : input) {
            result.put(i, element);
            i++;
        }

        return result;
    }
}

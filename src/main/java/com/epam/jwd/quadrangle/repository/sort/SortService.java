package com.epam.jwd.quadrangle.repository.sort;

import java.util.Comparator;
import java.util.List;

public interface SortService<T> {

    List<T> sort(List<T> list, Comparator<T> comparator);
}

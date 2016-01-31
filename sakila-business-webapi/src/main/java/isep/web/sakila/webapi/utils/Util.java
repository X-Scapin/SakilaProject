package isep.web.sakila.webapi.utils;

import java.util.LinkedList;
import java.util.List;

public class Util {
	public static <E> List<E> toList(Iterable<E> iterable) {
		if (iterable instanceof List) {
			return (List<E>) iterable;
		}
		List<E> list = new LinkedList<E>();
		if (iterable != null) {
			for (E e : iterable) {
				list.add(e);
			}
		}
		return list;
	}
}

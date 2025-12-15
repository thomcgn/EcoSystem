package org.thomcgn.data;

import org.thomcgn.utils.DaysOfWeek;
import org.thomcgn.utils.Gender;

public record Person(int id, String name, DaysOfWeek favoriteDay, Gender gender) {

}

package org.api;

import org.api.exceptions.FilterException;
import org.api.interfaces.Operation;
import org.api.models.Filter;
import org.api.models.filters.HasBooleanValue;
import org.api.models.filters.IsEqualTo;
import org.api.models.filters.IsGreaterThan;
import org.api.models.filters.IsLowerThan;
import org.api.models.operators.And;
import org.api.models.operators.Not;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class ExampleUsage {
    static Map<String, ?> beatrice = Map.of(
            "name", "Beatrice",
            "surname", "Beacco",
            "age", 25,
            "alive", true,
            "hobbies", List.of("coding", "reading", "working out", "playing chess (badly)"),
            "address", Map.of(
                "city", "Udine",
                "country", "Italy"
            )
    );

    static Map<String, ?> napoleone = Map.of(
            "name", "Napoleone",
            "surname", "Bonaparte",
            "age", 50,
            "alive", false,
            "hobbies", List.of("warfare", "stealing cultural artifacts", "getting banished to an isle", "losing at Waterloo", "being short"),
            "address", Map.of(
                    "city", "Ajaccio",
                    "country", "France"
            )
    );
    public static void main( String[] args ) throws FilterException {
        //Combining filter predicates
        Filter isYoungAndAlive = new Filter(new IsLowerThan("age", 50), new HasBooleanValue("alive", true)); //Any number of Operations can be passed to a Filter
        System.out.println("Is Beatrice young and alive? " + isYoungAndAlive.matches(beatrice)); //true
        System.out.println("Is Napoleone young and alive? " + isYoungAndAlive.matches(napoleone)); //false

        //Combining filters with logical operators
        Operation isNotYoungAndAlive = new Not(isYoungAndAlive); //Use previous filter as operation and invert its result
        Operation hasALongerNameOrSurnameThanCM = new And(new IsGreaterThan("name", "Carlo"), new IsGreaterThan("surname", "Magno")); //Combine Operations with AND logic
        Filter isNotYoungAndAliveAndHasALongerNameThanCM = new Filter(isNotYoungAndAlive, hasALongerNameOrSurnameThanCM); //Combine logic operators with a filter
        System.out.println("Is Beatrice not young and alive and has a longer name than Carlo Magno? " + isNotYoungAndAliveAndHasALongerNameThanCM.matches(beatrice)); //false
        System.out.println("Is Napoleone not young and alive and has a longer name than Carlo Magno? " + isNotYoungAndAliveAndHasALongerNameThanCM.matches(napoleone)); //true

        //Advanced filter features
        Operation isFromUdine = new IsEqualTo(new String[] {"address", "city"}, "Udine"); //Filters can be applied to nested properties by passing the correct path
        Operation hasMoreThanTwoHobbies = new IsGreaterThan("hobbies", 2); //Greater and lower also support Collections and Maps size checks
        Set<String> aLongListOfHobbies = Set.of("coding", "reading", "working out", "playing chess (badly)", "warfare", "stealing cultural artifacts", "getting banished to an isle", "losing at Waterloo", "being short");
        Operation doesntHaveTooManyHobbies = new IsLowerThan("hobbies", aLongListOfHobbies); //Two Collections can be compared by size
        Filter complexFilter = new Filter(isFromUdine, hasMoreThanTwoHobbies, doesntHaveTooManyHobbies);
        System.out.println("Is Beatrice from Udine, has more than two hobbies and doesn't have too many hobbies? " + complexFilter.matches(beatrice)); //true
        System.out.println("Is Napoleone from Udine, has more than two hobbies and doesn't have too many hobbies? " + complexFilter.matches(napoleone)); //false
    }
}

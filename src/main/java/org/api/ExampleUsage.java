package org.api;

import org.api.exceptions.FilterException;
import org.api.interfaces.Operation;
import org.api.models.Filter;
import org.api.models.filters.HasBooleanValue;
import org.api.models.filters.IsGreaterThan;
import org.api.models.filters.IsLowerThan;
import org.api.models.operators.And;
import org.api.models.operators.Not;

import java.util.List;
import java.util.Map;

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
        Filter isYoungAndAlive = new Filter(new IsLowerThan("age", 51), new HasBooleanValue("alive", true));
        System.out.println("Is Beatrice young and alive? " + isYoungAndAlive.matches(beatrice)); //true
        System.out.println("Is Napoleone young and alive? " + isYoungAndAlive.matches(napoleone)); //false

        //Combining filters with logical operators
        Operation isNotYoungAndAlive = new Not(isYoungAndAlive); //Use previous filter as operation
        Operation hasALongerNameOrSurname = new And(new IsGreaterThan("name", "Carlo"), new IsGreaterThan("surname", "Magno"));
        Filter isNotYoungAndAliveAndHasALongerNameThanCarloMagno = new Filter(isNotYoungAndAlive, hasALongerNameOrSurname);
        System.out.println("Is Beatrice not young and alive and has a longer name than Carlo Magno? " + isNotYoungAndAliveAndHasALongerNameThanCarloMagno.matches(beatrice)); //false
        System.out.println("Is Napoleone not young and alive and has a longer name than Carlo Magno? " + isNotYoungAndAliveAndHasALongerNameThanCarloMagno.matches(napoleone)); //true
    }
}

package edu.ntnu.idatt1002.k01g08.fta;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Goal tests")
public class GoalTest {

    @Nested
    @DisplayName("Positive tests for the subclass ´Goal´")
    public class PositiveTests {

        @Nested
        @DisplayName("Tests for valid input in the constructor")
        public class constructorTests {

            @Test
            @DisplayName("")
            public void initializeASubstitutionWithAllCorrectParameters() {
            }
        }
    }

    @Nested
    @DisplayName("Negative tests for the subclass ´Goal´")
    public class NegativeTests {
    }

}

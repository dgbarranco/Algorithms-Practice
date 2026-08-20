package heap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit 5 tests for ER (Homework 4).
 *
 */
public class Homework4Test {

    @Test
    void treatBeforeAnyTriage_returnsNONE() {
        ER er = new ER(20, 20);
        addSixArrivals(er);
        assertEquals("NONE", er.treatOne());
    }

    @Test
    void triageTwo_thenTreat_picksMostUrgent() {
        ER er = new ER(20, 20);
        addSixArrivals(er);

        er.triageOne(); // Bob -> category 4
        er.triageOne(); // Ana -> category 1

        assertEquals("Ana", er.treatOne()); // Ana is more urgent
    }

    @Test
    void categoryOrder_cat3BeatsCat4() {
        ER er = new ER(20, 20);
        addSixArrivals(er);

        er.triageOne(); // Bob category 4
        er.triageOne(); // Ana category 1
        assertEquals("Ana", er.treatOne());

        er.triageOne(); // Cal category 3
        er.triageOne(); // Dee category 4

        assertEquals("Cal", er.treatOne()); // Cal is now most urgent
    }

    @Test
    void triagedLaterHigherPriorityStillWins() {
        ER er = new ER(20, 50);
        addSixArrivals(er);

        // Triage 4: Bob(cat4), Ana(cat1), Cal(cat3), Dee(cat4)
        er.triageOne();
        er.triageOne();
        er.triageOne();
        er.triageOne();

        // Treat cat1 first
        assertEquals("Ana", er.treatOne());

        // Now triage Eli (cat2) after some others already eligible
        er.triageOne();
        assertEquals("Eli", er.treatOne(), "Cat2 should beat remaining cat3/cat4 even if triaged later");
    }

    @Test
    void tieBreak_sameCategoryByArrivalTime() {
        ER er = new ER(20, 50);
        addSixArrivals(er);

        // Triage Bob(cat4), Ana(cat1), Cal(cat3), Dee(cat4), Eli(cat2)
        for (int i = 0; i < 5; i++) er.triageOne();

        // Treat in expected order: Ana(1), Eli(2), Cal(3), Bob(4, arr=5), Dee(4, arr=8)
        assertEquals("Ana", er.treatOne());
        assertEquals("Eli", er.treatOne());
        assertEquals("Cal", er.treatOne());
        assertEquals("Bob", er.treatOne(), "Among cat4, earlier arrival first");
        assertEquals("Dee", er.treatOne());
    }

    @Test
    void notTriagedYet_notEligible() {
        ER er = new ER(20, 50);
        addSixArrivals(er);

        // Triage only 5 patients; Fay remains in queue
        for (int i = 0; i < 5; i++) er.triageOne();

        // Treat 5 eligible patients; Fay must not appear
        assertEquals("Ana", er.treatOne());
        assertEquals("Eli", er.treatOne());
        assertEquals("Cal", er.treatOne());
        assertEquals("Bob", er.treatOne());
        assertEquals("Dee", er.treatOne());

        // Heap empty now, but Fay still waiting for triage
        assertEquals("NONE", er.treatOne());

        // After triaging Fay, she becomes eligible
        er.triageOne();
        assertEquals("Fay", er.treatOne());
    }

    @Test
    void triageOnEmptyQueue_doesNothing() {
        ER er = new ER(10, 10);

        assertDoesNotThrow(er::triageOne);
        assertEquals("NONE", er.treatOne());
    }

    @Test
    void computeCategory_moreUrgentOfTwoSymptoms() {
        assertEquals(1, ER.computeCategory("CHEST_PAIN", "FEVER"));
        assertEquals(2, ER.computeCategory("MILD_PAIN", "BROKEN_BONE"));
        assertEquals(5, ER.computeCategory("NONE", "NONE"));
    }

    private static void addSixArrivals(ER er) {
        er.arrive("Bob", 5, "SORE_THROAT", "NONE");              // cat4
        er.arrive("Ana", 6, "CHEST_PAIN", "NAUSEA");            // cat1 (NAUSEA defaults to cat5)
        er.arrive("Cal", 7, "FEVER", "COUGH");                  // cat3
        er.arrive("Dee", 8, "MILD_PAIN", "SPRAIN");             // cat4
        er.arrive("Eli", 9, "BROKEN_BONE", "SEVERE_BLEEDING");  // cat2
        er.arrive("Fay", 10, "COUGH", "NONE"); // category 4
    }
}

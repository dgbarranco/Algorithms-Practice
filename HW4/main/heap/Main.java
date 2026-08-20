package heap;

/**
 * Simple driver (main) for the ER triage assignment.
 * Use it to do simple testing on your own before you run Homework4Test.
 */
public class Main {

    public static void main(String[] args) {
        ER er = new ER(20, 20); // capacity > number of triaged patients you'll insert

        // 6 patients arrive
        er.arrive("Bob", 5, "SORE_THROAT", "NONE");             // category 4
        er.arrive("Ana", 6, "CHEST_PAIN", "NAUSEA");            // category 1
        er.arrive("Cal", 7, "FEVER", "COUGH");                  // category 3
        er.arrive("Dee", 8, "MILD_PAIN", "SPRAIN");             // category 4
        er.arrive("Eli", 9, "BROKEN_BONE", "SEVERE_BLEEDING");  // category 2
        er.arrive("Fay", 10, "COUGH", "NONE"); // category 4
        System.out.println(er.treatOne()); // NONE (no one triaged yet)
        er.triageOne(); // Bob
        er.triageOne(); // Ana
        System.out.println(er.treatOne()); // Ana
        er.triageOne(); // Cal
        er.triageOne(); // Dee
        System.out.println(er.treatOne()); // Cal
        er.triageOne(); // Eli
        System.out.println(er.treatOne()); // Eli
        System.out.println(er.treatOne()); // Bob
        System.out.println(er.treatOne()); // Dee
        System.out.println(er.treatOne()); // NONE (Fay not triaged)
        er.triageOne(); // Fay
        System.out.println(er.treatOne()); // Fay
    }
}

package heap;

/**
 * Patient class
 * TODO: compareTo method
 */
public class Patient implements Comparable<Patient> {
    private final String name;
    private final int arrivalTime;
    private final String symptom1;
    private final String symptom2;
    private int category = 5;  // "urgency"  = assigned at triage time

    public Patient(String name, int arrivalTime, String symptom1, String symptom2) {
        if (name == null) throw new IllegalArgumentException("name cannot be null");
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.symptom1 = (symptom1 == null) ? "NONE" : symptom1;
        this.symptom2 = (symptom2 == null) ? "NONE" : symptom2;
    }

    public String getName() {
        return name;
    }

    public String getSymptom1() {
        return symptom1;
    }

    public String getSymptom2() {
        return symptom2;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    /** Sentinel used at heap[0] (smaller than any real patient). */
    public static Patient sentinelMin() {
        Patient p = new Patient("", Integer.MIN_VALUE, "NONE", "NONE");
        p.category = Integer.MIN_VALUE;
        return p;
    }

    /**
     * Compares "this" patient with "other" patient by category (ascending).
     * and if categories are equal, by arrival time (ascending).
     * If categories and arrival times are equal, compares patients by name (lexicographic order).
     * @param other the other patient to be compared.
     * @return
     */
    @Override
    public int compareTo(Patient other) {
        if(other == null){
            return -1;
        }
        //comparing the current patient to another by the urgency category at first
        //they cant be equal in value as a heap is assumed not to repeat a value
        //if - num is returned then this patient is of higher priority
        if(this.category != other.category){
            return this.category - other.category;
        }

        //comparing the arrivalTime patient to another by the  at
        //they cant be equal in value as a heap is assumed not to repeat a value
        //if - num is returned then this arrivalTime is of higher priority
        if(this.arrivalTime != other.arrivalTime){
            return this.arrivalTime - other.arrivalTime;
        }

        // FILL IN CODE:
        //calls recursively for comparison based on the name of both objects which if a - num is returned then this is at higher priority than other
        return this.name.compareTo(other.name); // change
    }

    @Override
    public String toString() {
        return name + "(category =" + category + ", arrivalTime =" + arrivalTime + ")";
    }
}

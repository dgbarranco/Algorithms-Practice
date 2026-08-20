package heap;

import queue.ArrayQueue;

import java.util.NoSuchElementException;

/**
 * ER simulates a simplified emergency-room flow with two stages:
 * (1) Patients ARRIVE and wait in triage queue (waitingForTriage), which is a regular ArrayQueue, first-come, first-triaged.
 * (2) TRIAGED patients are assigned an "urgency" category (1 = most urgent, ..., 5 = least urgent)
 *     and inserted into a min-heap (priority queue called "eligible") for treatment.
 *
 * (3) TREATing a patient always removes the smallest category eligible patient from the heap,
 * using this order: urgency category (ascending), then arrivalTime (ascending), then name (this order is defined in the compareTo method in class Patient).
 *
 * This is a simplified ER model for the homework.
 * TODO: fill in code in arrive, triageOne, treatOne
 */
public class ER {
    private final ArrayQueue waitingForTriage; // stores Patient as Object
    private final MinHeap eligible;

    public ER(int triageQueueCapacity, int heapCapacity) {
        this.waitingForTriage = new ArrayQueue(triageQueueCapacity);
        this.eligible = new MinHeap(heapCapacity);
    }

    /** When the patient arrives, add them to the waitingForTriage queue
     *
     * @param name name
     * @param arrivalTime arrival time
     * @param symptom1 primary symptom
     * @param symptom2 secondary symptom
     */
    public void arrive(String name, int arrivalTime, String symptom1, String symptom2) {

        //creates a new patient object by adding the values of the patient which has arrived
        Patient newPatient = new Patient(name, arrivalTime, symptom1, symptom2);
        //the new patient object is then added to queue
        waitingForTriage.enqueue(newPatient);

        // FILL IN CODE:
        // Create a new Patient with the given parameters and add them to the regular (FIFO) queue called  waitingForTriage

    }

    /** Triage one patient (FIFO). If none waiting, do nothing.
     *  Dequeue the first patient from the waitingForTriage queue, compute and set the urgency category,
     *  and then insert the patient into the "eligible" min-heap where patients wait to be treated.
     */
    public void triageOne() {
        if (waitingForTriage.empty()) return;

        //assgining the patient which has been dequed onto this value
        Patient curr = (Patient) waitingForTriage.dequeue();

        //the category is assigned and calculated in value
        int category = computeCategory(curr.getSymptom1(), curr.getSymptom2());

        //assigns the category
        curr.setCategory(category);

        //assign the obj dequed into the heap
        eligible.insert(curr);


        // FILL IN CODE: dequeue the first element from the regular queue (called waitingForTriage)
        // Compute and set the category for this patient based on the symptoms (see helper methods below)
        // and insert the patient into the min heap called "eligible".

    }

    /** Treat exactly one eligible patient:
     *  Get the most "urgent" patient from the min-heap, and
     *  Return name of the patient or "NONE", if no patients. */
    public String treatOne() {
        // FILL IN CODE:
        //if there is no elements in the heap then there are no patients at all
        //therefore the value of no elements will be returned
        if(eligible.isEmpty()){
            return "NONE";
        }

        //removes the smallest element in the heap
        Patient curr = eligible.removeMin();

        // Remove the patient with the smallest category (most urgent one) from the min heap
        // and return their name

        //the new smallest element in the heap will be returned by name
        return curr.getName(); // change
    }

    // Provided: category computation - do not modify computeCategory, categoryOfSymptom
    public static int computeCategory(String symptom1, String symptom2) {
        return Math.min(categoryOfSymptom(symptom1), categoryOfSymptom(symptom2));
    }

    /** Category/urgency is assigned based on the symptom(s)
     *
     * @param symptom symptom
     * @return category
     */
    private static int categoryOfSymptom(String symptom) {
        if (symptom == null) return 5;
        switch (symptom) {
            case "CHEST_PAIN":
            case "NOT_BREATHING":
            case "UNCONSCIOUS":
            case "STROKE":
            case "SEIZURE":
                return 1;

            case "SHORTNESS_OF_BREATH":
            case "BROKEN_BONE":
            case "SEVERE_BLEEDING":
            case "ALLERGIC_REACTION":
                return 2;

            case "FEVER":
            case "VOMITING":
            case "MIGRAINE":
            case "MODERATE_PAIN":
                return 3;

            case "COUGH":
            case "SORE_THROAT":
            case "SPRAIN":
            case "MILD_PAIN":
                return 4;

            default:
                return 5;
        }
    }
}

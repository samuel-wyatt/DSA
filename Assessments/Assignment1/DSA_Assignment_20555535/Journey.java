public class Journey {
    //Class Variables
    private String from;
    private String to;
    private String time;
    private DSAQueue barrier;
    private int security;

    //Constructor
    public Journey(String inFrom, String inTo, String inTime, DSAQueue inBarrier, int inSecurity) {
        this.from = inFrom;
        this.to = inTo;
        this.time = inTime;
        this.barrier = inBarrier;
        this.security = inSecurity;
    }

    //Copy Constructor
    public Journey(Journey inJourney) {
        this.from = inJourney.getFrom();
        this.to = inJourney.getTo();
        this.time = inJourney.getTime();
        this.barrier = inJourney.getBarrier();
        this.security = inJourney.getSecurity();
    }

    //Accessors
    public String getFrom() {return this.from; }
    public String getTo() { return this.to; }
    public String getTime() { return this.time; }
    public DSAQueue getBarrier() { return this.barrier; }
    public int getSecurity() { return this.security; }

    //Mutators
    public void setFrom(String inFrom) { this.from = inFrom; }
    public void setTo(String inTo) { this.to = inTo; }
    public void setTime(String inTime) { this.time = inTime; }
    public void setBarrier(DSAQueue inBarrier) { this.barrier = inBarrier; }
    public void setSecurity(int inSecurity) { this.security = inSecurity; }

    //toString method
    public String toString() {
        String cat = "From: " + from + ", To: " + to + ", Time: " + time + ", Barrier(s): " + barrier.toString() + ", Security: " + security;
        return cat;
    }
}

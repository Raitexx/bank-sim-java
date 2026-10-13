public interface Printable {

    // printable contract where any class implements printable is making promise
    // saying "I guarentee I have printDetails().", Compiler enforces this promise.
    
    // print full summary of this object to standard output
    void printDetails();

    // return single line summary suitable for use in report or log
    String getSummary();

}

public interface Transferable {
    
    void transfer(Account target, double amount) throws AccountException;
}

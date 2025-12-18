package Task1;
import java.text.DecimalFormat;
import java.util.concurrent.locks.ReentrantLock;

public class Bankaccount {

    private double balance;
    private final ReentrantLock lock = new ReentrantLock();
    private static final DecimalFormat df = new DecimalFormat("#.##");

    public Bankaccount(double startBalance) {
        this.balance = startBalance;
    }

    public void deposit(double amount) {
        if (lock.tryLock()) {   // non-blocking attempt
            try {
                balance += amount;
                System.out.println(Thread.currentThread().getName()
                        + " deposited: " + df.format(amount)
                        + ", New Balance: " + df.format(balance));
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName()
                    + " could not acquire lock for deposit.");
        }
    }

    public boolean withdraw(double amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                System.out.println(Thread.currentThread().getName()
                        + " withdrew: " + df.format(amount)
                        + ", New Balance: " + df.format(balance));
                return true;
            } else {
                System.out.println(Thread.currentThread().getName()
                        + " failed to withdraw: (Insufficient balance)");
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    public double getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return "BankAccount{balance=" + df.format(balance) + "}";
    }
}

package Task1;
public class BankDemo {
    public static void main(String[] args) throws InterruptedException {
        Bankaccount account = new Bankaccount(1000);
        System.out.println("Initial Balance: $" + account.getBalance());

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                account.deposit(300);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Deposit-Thread");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                account.withdraw(300);
                try {
                    Thread.sleep(60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Withdraw-Thread");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final Balance: $" + account.getBalance());
    }
}
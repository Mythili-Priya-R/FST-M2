package activities;

public class BankAccount {

    private Integer balance;

    public BankAccount(Integer initialBalance)
    {
            balance=initialBalance;
    }

    public Integer withDraw(Integer amount) throws Exception {
        if(balance < amount)
        {
            throw new Exception();

        }
        balance -=amount;
        return balance;
    }
}

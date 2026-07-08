package linx7a.model;

public class Account {
    private final int id;
    private final int userId;
    private double moneyAmount;

    public Account(int id, int userId, double moneyAmount) {
        this.id = id;
        this.userId = userId;
        this.moneyAmount = moneyAmount;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public double getMoneyAmount() {
        return moneyAmount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userId=" + userId +
                ", moneyAmount=" + moneyAmount +
                '}';
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должна быть положительной");
        }
        moneyAmount += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должна быть положительной");
        }
        if (amount > moneyAmount) {
            throw new IllegalArgumentException("Недостаточно средств на счету");
        }
        moneyAmount -= amount;
    }
}

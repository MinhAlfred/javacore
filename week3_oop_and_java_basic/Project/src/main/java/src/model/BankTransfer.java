package src.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankTransfer extends PaymentMethod{
    private static final double FEE_THRESHOLD = 2000;
    private static final double FEE_PERCENTAGE = 0.01;

    public BankTransfer(String id, double balance, User user) {
        super(id, balance, user);
    }

    @Override
    public boolean processPaymentMethod(double amount) {
        double finalAmount = (amount > FEE_THRESHOLD) ? amount * (1 + FEE_PERCENTAGE) : amount;
        if(finalAmount > getBalance()) return false;
        setBalance(getBalance() - finalAmount);
        return true;
    }
}

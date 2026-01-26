package strategy;

import interfaces.PaymentMethodStrategy;

public class CashPaymentStrategy implements PaymentMethodStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("Received Cash  Rs" + amount);
        return true;
    }
}

package strategy;

import interfaces.PaymentMethodStrategy;

public class UPIPaymentStrategy implements PaymentMethodStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("Received UPI  Rs" + amount);
        return true;
    }
}

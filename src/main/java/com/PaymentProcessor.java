package com.mozido.connectors;

import java.util.*;

public class PaymentProcessor {

    static List<Payment> payments = new ArrayList<>();

    public void addPayment(Payment payment){

        if (payment == null){
            throw new RuntimeException("You cannot add an empty payment");
        }

        payments.add(payment);
    }

    public List<Payment> getPayments(){
        payments.sort(Comparator.comparing(Payment::getAmount).reversed());
        return payments;
    }

    public List<Payment> getPaymentsByStatus(PaymentStatus paymentStatus){

        return payments.stream()
                .filter(p -> p.getPaymentStatus().equals(paymentStatus))
                .toList();
    }

    public int getPaymentsCount(){
        return payments.size();
    }

    public double getSuccessfulPaymentsCount(){

        if(payments.isEmpty()){
            throw new RuntimeException("You haven't added any payment yet ");
        }

        return payments.parallelStream()
                .filter(p -> p.getPaymentStatus().equals(PaymentStatus.SUCCESS))
                .mapToDouble(Payment::getAmount)
                .sum();
    }

    public double calculateAverageAmountOfSuccessfulPayments(){

        if(payments.isEmpty()){
            throw new RuntimeException("You cannot calculate the Average amount of successful payments because you haven't added any payment yet ");
        }

        OptionalDouble amountSum = payments.parallelStream()
                .filter(p -> p.getPaymentStatus().equals(PaymentStatus.SUCCESS))
                .mapToDouble(Payment::getAmount)
                .average();

        if (amountSum.isPresent()) {
            return amountSum.getAsDouble();
        } else {
            throw new RuntimeException("You cannot calculate the Average amount of successful payments because you haven't added any payment yet ");
        }
    }
}

package com.mozido.connectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        PaymentProcessor paymentProcessor = new PaymentProcessor();

        paymentProcessor.addPayment(new Payment(
                "1", 345,"USD", PaymentStatus.PENDING
        ));
        paymentProcessor.addPayment(new Payment(
                "2", 678,"BRL", PaymentStatus.FAILED
        ));
        paymentProcessor.addPayment(new Payment(
                "3", 134,"EUR", PaymentStatus.FAILED
        ));
        paymentProcessor.addPayment(new Payment(
                "4", 134,"BRL", PaymentStatus.SUCCESS
        ));
        paymentProcessor.addPayment(new Payment(
                "5", 132,"USD", PaymentStatus.SUCCESS
        ));

        //- Retrieve all payments.
        System.out.println("Number of Payments: "+ paymentProcessor.getPaymentsCount() );
        System.out.println(paymentProcessor.getPayments());

        // - Retrieve payments filtered by status.
        System.out.println("Number of Failed Payments: "+ paymentProcessor.getPaymentsByStatus(PaymentStatus.FAILED).size() );
        System.out.println(paymentProcessor.getPaymentsByStatus(PaymentStatus.FAILED));

        // Total amount of successful payments.
        System.out.println("Number of Successful Payments: "+ paymentProcessor.getSuccessfulPaymentsCount());

        // - Average amount of successful payments.
        System.out.println("Average amount of successful payments: "+ paymentProcessor.calculateAverageAmountOfSuccessfulPayments());

    }
}
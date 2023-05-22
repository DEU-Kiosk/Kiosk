package com.mycompany.kiosktest;

import javax.swing.*;

public class CashPayment implements PaymentStrategy {
    private String changePay;

    public CashPayment(double changePay) {
        this.changePay = String.valueOf(changePay);
    }

    @Override
    public void pay(double amount) {
        // 계좌 이체 처리 로직 구현
        String message = amount + "가 결제완료되었습니다.\n" + "거스름돈" + changePay + "가져가세요";
        JOptionPane.showMessageDialog(null, message, "결제 완료", JOptionPane.INFORMATION_MESSAGE);

    }
}

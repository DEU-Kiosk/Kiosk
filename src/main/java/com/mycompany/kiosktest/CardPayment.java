package com.mycompany.kiosktest;
import javax.swing.*;

public class CardPayment implements PaymentStrategy {
    private String UserName;
    private String CardCompany;
    private String CardNumber;
    private String cvv;

    public CardPayment(String UserName, String CardCompany, String CardNumber, String cvv) {
        this.UserName = UserName;
        this.CardCompany = CardCompany;
        this.CardNumber = CardNumber;
        this.cvv = cvv;
    }

    @Override
    public void pay(double amount) {
        // 카드 결제 처리 로직 구현
        String message = CardNumber + "으로 " + amount + "가 자동 결제되었습니다.";
        JOptionPane.showMessageDialog(null, message, "결제 완료", JOptionPane.INFORMATION_MESSAGE);

    }
}


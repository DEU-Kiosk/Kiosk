package com.mycompany.kiosktest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardPayFrame extends JTable {
    JPanel AmountPay, UserName, CardCompany, CardNumber, Cvv, panel;
    static JTextField UserNameField, CardCompanyField, CardNumberField, CvvField;
    JButton ApprovalButt;
    JFrame frame;
    double amount;

    public CardPayFrame() {
        Dimension dim = new Dimension(250, 300);

        frame = new JFrame("카드 결재창");
        frame.setPreferredSize(dim);

        AmountPay = new JPanel();
        AmountPay.setLayout(new BoxLayout(AmountPay, BoxLayout.X_AXIS));
        AmountPay.add(new JLabel("AmountPay: "));
        JTextField amountField = new JTextField(String.valueOf(TextFieldSum.sum));
        amountField.setEditable(false);
        AmountPay.add(amountField);

        CardCompany = new JPanel();
        CardCompany.setLayout(new BoxLayout(CardCompany, BoxLayout.X_AXIS));
        CardCompany.add(new JLabel("Cardcompany: "));
        CardCompanyField = new JTextField();
        CardCompany.add(CardCompanyField);

        UserName = new JPanel();
        UserName.setLayout(new BoxLayout(UserName, BoxLayout.X_AXIS));
        UserName.add(new JLabel("UserName: "));
        UserNameField = new JTextField();
        UserName.add(UserNameField);

        CardNumber = new JPanel();
        CardNumber.setLayout(new BoxLayout(CardNumber, BoxLayout.X_AXIS));
        CardNumber.add(new JLabel("CardNumber: "));
        CardNumberField = new JTextField();
        CardNumber.add(CardNumberField);

        Cvv = new JPanel();
        Cvv.setLayout(new BoxLayout(Cvv, BoxLayout.X_AXIS));
        Cvv.add(new JLabel("cvc: "));
        CvvField = new JTextField();
        Cvv.add(CvvField);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(AmountPay);
        panel.add(UserName);
        panel.add(CardCompany);
        panel.add(CardNumber);
        panel.add(Cvv);

        frame.add(panel, BorderLayout.CENTER);
        ApprovalButt = new JButton("결재하기");
        frame.add(ApprovalButt, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);

        ApprovalButt.addActionListener(new Approval());
    }

    class Approval implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String userName = UserNameField.getText();
            String cardCompany = CardCompanyField.getText();
            String cardNumber = CardNumberField.getText();
            String cvv = CvvField.getText();

            if (userName.isEmpty() || cardCompany.isEmpty() || cardNumber.isEmpty() || cvv.isEmpty()) {
                JOptionPane.showMessageDialog(null, "입력되지 않은 정보가 있습니다. 모든 필드를 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }

            frame.dispose();
            PaymentStrategy paymentStrategy = new CardPayment(userName, cardCompany, cardNumber, cvv);
            paymentStrategy.pay(amount);


        }
    }
}

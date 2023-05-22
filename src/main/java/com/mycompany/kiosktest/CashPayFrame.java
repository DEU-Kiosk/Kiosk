package com.mycompany.kiosktest;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CashPayFrame {
    private JPanel AmountPay, ChangePay, panel;
    private JTextField AmountPayField, ChangePayField;
    private JButton ApprovalButt;

    public CashPayFrame() {
        Dimension dim = new Dimension(250, 200);

        JFrame frame = new JFrame("현금 결재창");
        frame.setPreferredSize(dim);

        AmountPay = new JPanel();
        AmountPay.setLayout(new BoxLayout(AmountPay, BoxLayout.X_AXIS));
        AmountPay.add(new JLabel("AmountPay: "));
        AmountPayField = new JTextField();
        AmountPay.add(AmountPayField);

        ChangePay = new JPanel();
        ChangePay.setLayout(new BoxLayout(ChangePay, BoxLayout.X_AXIS));
        ChangePay.add(new JLabel("ChangePay: "));
        ChangePayField = new JTextField();
        ChangePay.add(ChangePayField);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(AmountPay);
        panel.add(ChangePay);

        frame.add(panel, BorderLayout.CENTER);
        ApprovalButt = new JButton("결재하기");
        frame.add(ApprovalButt, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);

        ApprovalButt.addActionListener(new Approval());

        AmountPayField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateChangePay();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateChangePay();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateChangePay();
            }
        });
    }

    class Approval implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String amountText = AmountPayField.getText();

            if (amountText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "입력되지 않은 정보가 있습니다. 모든 필드를 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }


            double amount = Double.parseDouble(amountText);
            double changePay = amount - TextFieldSum.sum;

            ChangePayField.setText(String.valueOf(changePay));

            if(changePay<0){
                JOptionPane.showMessageDialog(null, "금액을 올바르게 설정해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }

            PaymentStrategy paymentStrategy = new CashPayment(changePay);
            paymentStrategy.pay(amount);
        }
    }

    private void updateChangePay() {
        String amountText = AmountPayField.getText();

        if (!amountText.isEmpty()) {
            double amount = Double.parseDouble(amountText);
            double changePay = amount - TextFieldSum.sum;
            ChangePayField.setText(String.valueOf(changePay));
        } else {
            ChangePayField.setText("");
        }
    }
}

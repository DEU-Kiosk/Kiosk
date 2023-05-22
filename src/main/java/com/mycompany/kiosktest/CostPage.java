package com.mycompany.kiosktest;

import javax.swing.*;
import java.awt.event.*;

public class CostPage extends JFrame {
    private JButton CardPayButt, MoneyPayButt;
    private JLabel TotalAmount;
    PaymentStrategy paymentStrategy;
    private JFrame frame;

    public CostPage() {
        this.setLayout(null);
        frame = this; // 현재 CostPage의 JFrame 인스턴스를 저장

        CardPayButt = new JButton("카드 결재");
        MoneyPayButt = new JButton("현금 결재");
        TotalAmount = new JLabel("총 금액: " + TextFieldSum.sum);

        CardPayButt.addActionListener(new CardPay());
        MoneyPayButt.addActionListener(new MoneyPay());

        MoneyPayButt.setBounds(100, 130, 150, 150);
        CardPayButt.setBounds(350, 130, 150, 150);
        TotalAmount.setBounds(50, 50, 500, 50);

        add(TotalAmount);
        add(MoneyPayButt);
        add(CardPayButt);

        setSize(600, 400);
        setVisible(true);
    }

    class CardPay implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new CardPayFrame();
            frame.dispose();
        }
    }

    class MoneyPay implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new CashPayFrame();
            frame.dispose();
        }
    }
}

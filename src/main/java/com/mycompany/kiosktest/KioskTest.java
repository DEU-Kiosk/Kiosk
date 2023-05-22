/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kiosktest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 *
 * @author lsk85
 */
public class KioskTest extends JFrame implements ActionListener {

    private JPanel categoryPanel, menuPanel, cartPanel;
    private JButton coffeeBtn, foodBtn, smoothieBtn, addToCartBtn, removeBtn, Cost;
    private JLabel selectedMenuLabel;
    private DefaultTableModel cartModel, menuModel;
    private JTextField amountTextField;

    public KioskTest() {
        // JFrame 설정
        setTitle("Kiosk");
        setSize(1600, 1200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 카테고리 패널 설정
        categoryPanel = new JPanel(new GridLayout(1, 3));
        coffeeBtn = new JButton("커피");
        coffeeBtn.setPreferredSize(new Dimension(200, 100));
        foodBtn = new JButton("디저트");
        foodBtn.setPreferredSize(new Dimension(200, 100));
        smoothieBtn = new JButton("스무디");
        smoothieBtn.setPreferredSize(new Dimension(200, 100));
        coffeeBtn.addActionListener(this);
        foodBtn.addActionListener(this);
        smoothieBtn.addActionListener(this);
        categoryPanel.add(coffeeBtn);
        categoryPanel.add(foodBtn);
        categoryPanel.add(smoothieBtn);
        
// 메뉴 패널 설정
        menuPanel = new JPanel(new BorderLayout());
        menuModel = new DefaultTableModel(new Object[][]{}, new String[]{"메뉴", "가격"});
        loadMenuFromTxtFile("menu.txt", menuModel); // txt 파일에서 메뉴 데이터를 읽어옴
        JTable table = new JTable(menuModel);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    String menu = (String) menuModel.getValueAt(row, 0);
                    String price = (String) menuModel.getValueAt(row, 1);
                    selectedMenuLabel.setText(menu + " " + price);
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(table);
        menuPanel.add(scrollPane, BorderLayout.CENTER);
        selectedMenuLabel = new JLabel("메뉴를 선택하세요.");
        
 
          // 장바구니 패널 설정
        amountTextField = new JTextField();
    cartPanel = new JPanel(new BorderLayout());
    cartModel = new DefaultTableModel(new Object[][]{}, new String[]{"메뉴", "가격", "수량"});
    JTable cartTable = new JTable(cartModel);
    JScrollPane cartScrollPane = new JScrollPane(cartTable);

    Cost = new JButton("결재");
    Cost.setPreferredSize(new Dimension(100, 50));
    Cost.addActionListener((ActionEvent e)->{
        new CostPage();
            });

    addToCartBtn = new JButton("장바구니에 추가");
    addToCartBtn.setPreferredSize(new Dimension(200, 100));
    addToCartBtn.addActionListener((ActionEvent e) -> {
        String[] cartData = {selectedMenuLabel.getText(), getPrice(selectedMenuLabel.getText())};
        int rowCount = cartModel.getRowCount();
        boolean alreadyExist = false;
        for (int i = 0; i < rowCount; i++) {
            String menu = (String) cartModel.getValueAt(i, 0);
            if (menu.equals(cartData[0])) {
                int quantity = Integer.parseInt((String) cartModel.getValueAt(i, 2));
                cartModel.setValueAt(String.valueOf(quantity + 1), i, 2);
                alreadyExist = true;
                break;
            }
        }
        if (!alreadyExist) {
            cartModel.addRow(new String[]{cartData[0], cartData[1], "1"});
        }
        TextFieldSum.sumToTextField(cartData[1], amountTextField);
    });
    cartPanel.add(cartScrollPane, BorderLayout.CENTER);
    cartPanel.add(addToCartBtn, BorderLayout.SOUTH);
    cartPanel.add(Cost, BorderLayout.LINE_END);
    cartPanel.add(amountTextField, BorderLayout.NORTH);
    

    // JFrame에 패널 추가
    add(categoryPanel, BorderLayout.NORTH);
    add(menuPanel, BorderLayout.CENTER);
    add(selectedMenuLabel, BorderLayout.SOUTH);
    add(cartPanel, BorderLayout.EAST);

    setVisible(true);
}
    @Override
public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();
    if (source == coffeeBtn) {
        loadMenuFromTxtFile("coffee.txt", menuModel); // "coffee.txt" 파일에서 메뉴 데이터를 읽어옴
    } else if (source == foodBtn) {
        loadMenuFromTxtFile("food.txt", menuModel); // "food.txt" 파일에서 메뉴 데이터를 읽어옴
    } else if (source == smoothieBtn) {
        loadMenuFromTxtFile("smoothie.txt", menuModel); // "smoothie.txt" 파일에서 메뉴 데이터를 읽어옴
    }
}

private String getPrice(String menu) {
    String[] tokens = menu.split(" ");
    return tokens[tokens.length - 1];
}

private void loadMenuFromTxtFile(String filename, DefaultTableModel model) {
    model.setRowCount(0); // 테이블 모델 초기화
    try {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(",");
            if (tokens.length == 2) {
                String name = tokens[0].trim();
                String price = tokens[1].trim();
                model.addRow(new String[]{name, price});
            }
        }
        reader.close();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
}

public static void main(String[] args) {
        new KioskTest();
}
}






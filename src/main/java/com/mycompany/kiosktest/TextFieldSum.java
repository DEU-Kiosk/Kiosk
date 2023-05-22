package com.mycompany.kiosktest;

import javax.swing.JTextField;

public class TextFieldSum {
    static int sum = 0;
    public static void sumToTextField(String array, JTextField textField) {

        int value = Integer.parseInt(array);
        sum += value;

        textField.setText(Integer.toString(sum));
    }
}
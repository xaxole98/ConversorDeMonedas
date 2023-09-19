package com.xaxocode.practices.plataforma;

import org.json.JSONArray;
import org.json.JSONObject;

import com.xaxocode.practices.converteroperations.ConvertorOperations;
import com.xaxocode.practices.file.FileRead;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Plataforma {
    private static JTextField amountField = new JTextField(10);
    private static JButton convertButton = new JButton("Convertir");
    private static JTextField resultField = new JTextField(15);
    private static JComboBox<String> fromComboBox = new JComboBox<>(listCurrency());
    private static JComboBox<String> toComboBox = new JComboBox<>(listCurrency());

    public Plataforma() {

    }

    public static void initComponents() {
        startJFrame();
    }

    private static JPanel startMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(startCurrencyPanel(), BorderLayout.NORTH);
        mainPanel.add(startPanelResultado(), BorderLayout.CENTER);
        return mainPanel;

    }

    private static JPanel startAmountConvertorPanel() {
        JPanel amountConvertorPanel = new JPanel(new FlowLayout());
        JLabel amountLabel = new JLabel("Amount:");

        amountConvertorPanel.add(amountLabel);
        amountConvertorPanel.add(amountField);
        amountConvertorPanel.add(convertButton);
        return amountConvertorPanel;
    }

    private static JPanel startCurrencyPanel() {
        JPanel currencyPanel = new JPanel(new FlowLayout());
        JLabel fromLabel = new JLabel("De:");
        JLabel toLabel = new JLabel("A:");
        currencyPanel.add(fromLabel);
        currencyPanel.add(fromComboBox);
        currencyPanel.add(toLabel);
        currencyPanel.add(toComboBox);
        currencyPanel.add(startAmountConvertorPanel());
        return currencyPanel;

    }

    private static String[] listCurrency() {
        String json = FileRead.getFileRead();
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONObject("rates").names();

        String[] opciones = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            opciones[i] = jsonArray.getString(i);
        }
        return opciones;
    }

    private static JPanel startPanelResultado() {
        JPanel resultPanel = new JPanel(new FlowLayout());
        JLabel resultLabel = new JLabel("Resultado:");
        resultField.setEditable(false);
        resultPanel.add(resultLabel);
        resultPanel.add(resultField);
        return resultPanel;
    }

    private static ActionListener startConvertor() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConvertorOperations objeto = new ConvertorOperations();
                String fromCurrency = fromComboBox.getSelectedItem().toString();
                String toCurrency = toComboBox.getSelectedItem().toString();
                String amountText = amountField.getText();
                double amountValue = Double.parseDouble(amountText);
                double valueShow = objeto.convertor(amountValue, fromCurrency, toCurrency);
                String stringShow = String.valueOf(valueShow);
                resultField.setText(stringShow);
            }
        };
    }

    private static void startJFrame() {

        JFrame frame = new JFrame("Conversor de Monedas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 150);
        frame.add(startMainPanel());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        convertButton.addActionListener(startConvertor());

    }
}
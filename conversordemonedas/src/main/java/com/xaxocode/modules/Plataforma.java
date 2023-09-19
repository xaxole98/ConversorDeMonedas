package com.xaxocode.modules;

import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Plataforma {

    /**
     *
     * All methods are static because the value to be converted would never change
     * even if it is instantiated by being a currency converter app.
     *
     * We are saying that the amountField object is an instance of the class
     * JTextField and column size is 10 spaces
     *
     * We are saying that the convertButton object is an instance of the class
     * JButton and this has a text called "Convert"
     *
     * We are saying that the resultField object is an instance of the class
     * JTextField and this has a column size of 15
     *
     * We are saying that the fromComboBox object is an instance of the class
     * JComboBox and it obtains its values through the listCurrency() method
     * 
     */

    private static JTextField amountField = new JTextField(10);
    private static JButton convertButton = new JButton("Convertir");
    private static JTextField resultField = new JTextField(15);
    private static JComboBox<String> fromComboBox = new JComboBox<>(listCurrency());
    private static JComboBox<String> toComboBox = new JComboBox<>(listCurrency());

    /** Default Constructor about class Plataforma */

    public Plataforma() {

    }

    /**
     * This is a basic form that is always placed when trying to initialize a
     * GUI interface, you can change the name if you want but it is best to leave it
     * So, your purpose is to start everything necessary for the GUI interface in
     * this case is starting the startJFrame() method;
     */

    public static void initComponents() {
        startJFrame();
    }

    /**
     * We create a private method which returns a JPanel therefore
     * we place in the class and this method is called startMainPanel(), we create
     * a new object that will be an instance of the JPanel class and through this
     * we clarify the locations of the panels by calling the BorderLayout() method
     * in which using the mainPanel object and the add() method, we add what we want
     * in our Panel and we give it the desired location using BorderLayout.LOCATION
     * and finally we ask it to return the JPanel
     */

    private static JPanel startMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(startCurrencyPanel(), BorderLayout.NORTH);
        mainPanel.add(startPanelResultado(), BorderLayout.CENTER);
        return mainPanel;

    }

    /**
     * We create a new method that returns an object of type JPanel and its name is
     * startCurrencyPanel(), within the method we create an object that instantiates
     * the JPanel class and within it FlowLayout() is used, which organizes the
     * components of a container not from a Panel, by the way, two objects of the
     * JLabel class are created and within this we place the necessary text that
     * would be "From" or "To" that we will use to tell the user FROM"Divisa"
     * TO"Divisa" and the labels created They have the name fromLabel to toLabel and
     * we will do it in necessary order where the list of currencies (ComboBox) will
     * be to select and we will do it through the JPanel and at the end of this we
     * will place the Amount to convert and finally return the JPanel object
     */

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

    /**
     * We create a new private method which will return an object of the JPanel
     * class, within this we will create an object that instantiates the JPanel
     * class, in addition to doing the same for a Label that will be placed that the
     * text will be "Amount:" then using the Panel we add the amount Label to the
     * Panel, also the amountField (JField which is the object we created at the
     * beginning of the code) which is a space where the user will enter the numbers
     * to convert and finally the convertButton (JButton which is the object we
     * created at the beginning x2)
     */

    private static JPanel startAmountConvertorPanel() {
        JPanel amountConvertorPanel = new JPanel(new FlowLayout());
        JLabel amountLabel = new JLabel("Amount:");
        amountConvertorPanel.add(amountLabel);
        amountConvertorPanel.add(amountField);
        amountConvertorPanel.add(convertButton);
        return amountConvertorPanel;
    }

    /**
     * We create a method that will be a list of data type String which the method
     * will be called listCurrency(), within this we create a data of type String
     * called json which calls the getFileRead method which is static through the
     * FileRead class, then we create a object of the JSONObject class which reads
     * the information of the String data type and through another object called
     * jsonArray that instantiates the JSONArray class we try to read the jsonObject
     * object in which we say to search within the "rates" object and in this it
     * should give me all the names using the .names method, finally we create a
     * String List that has the name options in which this call to the constructor
     * of the String class and using square brackets we say that it obtains the
     * length of the jsonArray object using the length() method and then using a for
     * loop we go through each part of the list saying that the Options[i} list will
     * be assigned again to the value of the jsonArray object list in which the
     * String of each one should be returned like this until we finish going through
     * the entire list and finally return the value
     */

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

    /**
     * A method that will return a JPanel object in which startPanelResult will be
     * called and this is responsible for instantiating a JPanel object
     * (resultPanel) and also a Label (resultLabel) which will be a text in the GUI
     * interface, then we clarify that the object that we instantiate when beginning
     * called JTextField(resultField) will not be editable using the setEditable
     * method, finally we use the resultPanel object and add what is necessary and
     * return it
     */

    private static JPanel startPanelResultado() {
        JPanel resultPanel = new JPanel(new FlowLayout());
        JLabel resultLabel = new JLabel("Resultado:");
        resultField.setEditable(false);
        resultPanel.add(resultLabel);
        resultPanel.add(resultField);
        return resultPanel;
    }

    /**
     * We create a method that will be of type ActionListener and this has the name
     * startConvertor(), this will return an instance of an anonymous class in which
     * within this anonymous class in its SCOPE it starts a method without return
     * and we will create an event that will be of type CLICK
     */

    private static ActionListener startConvertor() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // We create an object that instantiates the ConvertorOperations class to have
                // access to its methods

                ConvertorOperations objeto = new ConvertorOperations();

                // We create an integer type data called fromCurrency in which it will call the
                // object that instantiates JComboBox(fromComboBox) in which through the
                // getSelectItem()
                // method we will obtain the selected Item in the list that we previously placed
                // and we add a toString() to ensure that the object be yes or yes text.

                String fromCurrency = fromComboBox.getSelectedItem().toString();

                // We create an integer type data called toCurrency in which it will call the
                // object that instantiates JComboBox(toComboBox) in which through the
                // getSelectItem()
                // method we will obtain the selected Item in the list that we previously placed
                // and we add a toString() to ensure that the object be yes or yes text.

                String toCurrency = toComboBox.getSelectedItem().toString();

                // We create a data type amountText that will be text in which we assign that
                // its value will be the same that will be placed in the JTextField(amountField)
                // in which we tell it to return it to us in text using the .getText() method

                String amountText = amountField.getText();

                // We create a double data type in which what we will do is use the Double class
                // that will use the parseDouble method to convert the text to Double.

                double amountValue = Double.parseDouble(amountText);

                // We create a double data type in which it will call the ConvertorOperations
                // class object and we will place the 3 necessary data, which would be the
                // amount to convert (amountValue), then the currency from which we want to
                // convert (fromCurrency) and finally what we will convert to ( toCurrency)

                double valueShow = objeto.convertor(amountValue, fromCurrency, toCurrency);

                // Converts the numeric value stored in the "valueShow" variable to a text
                // string.
                String stringShow = String.valueOf(valueShow);

                // Set the value of the string "stringShow" in the UI component "resultField".
                resultField.setText(stringShow);
            }
        };
    }

    private static void startJFrame() {
        /**
         * With JFrame you can configure the JPanel the JPanel is the way to create a
         * graphical interface visually and here we are calling the JPanel class to
         * tell him look in your constructor we will place the title for the GUI.
         */
        JFrame frame = new JFrame("Currency Converter");
        /**
         * When creating the "frame" object we are using the method
         * setDefaultCloseOperation to tell it to close the object when pressed
         * the .EXIT_ON.CLOSE event which is to close the window
         */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /**
         * With this you are telling the frame object that the gui interface must have a
         * size with respect to X,Y using the setSize(X,Y) method;
         */
        frame.setSize(600, 150);
        /**
         * I don't know if it is necessary.
         */

        frame.add(startMainPanel());

        /**
         * What this is doing is centering the GUI interface on the screen, that's why
         * your name to the setLocationRelativeTo() method
         */
        frame.setLocationRelativeTo(null);
        /**
         * Makes the sale visible using the setVisible(true or false) method, if
         * necessary
         * true will be displayed, if false it will not.
         */
        frame.setVisible(true);
        /** We add to the convertButton object */
        convertButton.addActionListener(startConvertor());

    }

}
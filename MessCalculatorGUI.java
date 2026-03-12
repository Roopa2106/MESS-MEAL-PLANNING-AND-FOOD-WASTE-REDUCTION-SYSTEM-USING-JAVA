import javax.swing.*;
import java.awt.event.*;
public class MessCalculatorGUI extends JFrame implements ActionListener {
    JTextField registeredField, preparedField;
    JTextArea resultArea;
    JComboBox<String> specialDayBox;
    JComboBox<String> popularityBox;
    JComboBox<String> academicBox;
    JComboBox<String> mealTimeBox;
    JButton calculateButton;
    MessCalculatorGUI() {
        setTitle("Smart Mess Meal Planning System");
        setSize(450,450);
        setLayout(null);

        JLabel title = new JLabel("Mess Food Waste Calculator");
        title.setBounds(130,10,200,25);
        add(title);

        JLabel regLabel = new JLabel("Registered Students:");
        regLabel.setBounds(30,50,150,25);
        add(regLabel);

        registeredField = new JTextField();
        registeredField.setBounds(200,50,150,25);
        add(registeredField);

        JLabel specialLabel = new JLabel("Special Meal Day:");
        specialLabel.setBounds(30,90,150,25);
        add(specialLabel);

        String special[] = {"No","Yes"};
        specialDayBox = new JComboBox<>(special);
        specialDayBox.setBounds(200,90,150,25);
        add(specialDayBox);

        JLabel popLabel = new JLabel("Meal Popularity:");
        popLabel.setBounds(30,130,150,25);
        add(popLabel);

        String popularity[] = {"Regular","Popular","Very Popular"};
        popularityBox = new JComboBox<>(popularity);
        popularityBox.setBounds(200,130,150,25);
        add(popularityBox);

        JLabel academicLabel = new JLabel("Academic Period:");
        academicLabel.setBounds(30,170,150,25);
        add(academicLabel);

        String period[] = {"Regular Semester","Exam Period","Holiday/Festival"};
        academicBox = new JComboBox<>(period);
        academicBox.setBounds(200,170,150,25);
        add(academicBox);

        JLabel mealLabel = new JLabel("Meal Time:");
        mealLabel.setBounds(30,210,150,25);
        add(mealLabel);

        String mealTime[] = {"Breakfast","Lunch","Dinner"};
        mealTimeBox = new JComboBox<>(mealTime);
        mealTimeBox.setBounds(200,210,150,25);
        add(mealTimeBox);

        JLabel prepLabel = new JLabel("Meals Prepared:");
        prepLabel.setBounds(30,250,150,25);
        add(prepLabel);

        preparedField = new JTextField();
        preparedField.setBounds(200,250,150,25);
        add(preparedField);

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(150,290,120,30);
        add(calculateButton);

        resultArea = new JTextArea();
        resultArea.setBounds(40,340,350,70);
        add(resultArea);

        calculateButton.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        int registered = Integer.parseInt(registeredField.getText());
        int prepared = Integer.parseInt(preparedField.getText());

        double expected = registered;

        // Special meal factor
        if(specialDayBox.getSelectedItem().equals("Yes"))
            expected *= 1.20;

        // Meal popularity factor
        String popularity = (String) popularityBox.getSelectedItem();
        if(popularity.equals("Popular"))
            expected *= 1.25;
        else if(popularity.equals("Very Popular"))
            expected *= 1.35;

        // Academic period factor
        String period = (String) academicBox.getSelectedItem();
        if(period.equals("Exam Period"))
            expected *= 1.15;
        else if(period.equals("Holiday/Festival"))
            expected *= 0.60;
        // Meal time factor
        String meal = (String) mealTimeBox.getSelectedItem();
        if(meal.equals("Breakfast"))
            expected *= 0.80;
        else if(meal.equals("Dinner"))
            expected *= 1.10;
        int suggestedMeals = (int) expected;
        String result;
        if(prepared > suggestedMeals) {
            int waste = prepared - suggestedMeals;
            double wastePercent = ((double) waste / prepared) * 100;
            result = "Suggested Meals: " + suggestedMeals +
                     "\nMeals Prepared: " + prepared +
                     "\nFood Waste: " + waste +
                     "\nWaste Percentage: " + String.format("%.2f", wastePercent) + "%";
            if(waste > 20)
                result += "\nSuggestion: Donate surplus food.";  }
        else if(prepared < suggestedMeals) {
            int shortage = suggestedMeals - prepared;
            result = "Suggested Meals: " + suggestedMeals +
                     "\nMeals Prepared: " + prepared +
                     "\nShortage: " + shortage;  }
        else {
            result = "Perfect Planning!\nNo Food Waste.";
        }
        resultArea.setText(result);
    }
    public static void main(String[] args) {
        new MessCalculatorGUI();
    }
}
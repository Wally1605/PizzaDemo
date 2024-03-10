import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GUI {

    JPanel panel;
    JPanel panel2;
    JPanel panel3;
    JFrame frame;
    JLabel title;
    JLabel mess1;

    JComboBox quantity;

    String[] listOfNum = {"0","1","2","3","4","5","6","7","8","9","10"};

    JTextField top1;
    JTextField top2;

    JTextField top3;

    JTextField top4;

    JTextField top5;

    JTextField top6;

    JTextField top7;

    JTextField top8;

    JTextField top9;

    JTextField top10;


    JTextField[] textFields = {top1,top2,top3,top4,top5,top6,top7,top8,top9,top10};

    JLabel prompt1;
    JLabel prompt2;
    JLabel prompt3;
    JLabel prompt4;
    JLabel prompt5;
    JLabel prompt6;
    JLabel prompt7;
    JLabel prompt8;
    JLabel prompt9;
    JLabel prompt10;


    JLabel[] messages = {prompt1,prompt2,prompt3,prompt4,prompt5,prompt6,prompt6,prompt7,prompt8,prompt9,prompt10};

    JRadioButton deli;
    JTextField addr;
    JButton submit;
    JLabel finalResult;

    String text;
    float price;
    int selected;
    public GUI(){
        panel = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        frame = new JFrame();
        panel.setBorder(BorderFactory.createEmptyBorder(10,100,10,100));
        JScrollPane scrollFrame = new JScrollPane(panel);
        panel.setAutoscrolls(true);
        panel2.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
        panel3.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));

        Color  b  = new Color	(107,139,169);
        panel2.setBackground(b);
        panel3.setBackground(Color.GRAY);
        panel.setLayout(new GridBagLayout());
        panel2.setLayout(new FlowLayout());
        panel3.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        title = new JLabel("Pizza Planet", SwingConstants.LEFT);
        title.setFont(new Font("Impact", Font.PLAIN, 26));
        title.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel2.add(title,gbc);

        mess1 = new JLabel("How many toppings would you like?");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(mess1,gbc);
        quantity = new JComboBox(listOfNum);
        quantity.addActionListener(this::topAmt);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(quantity,gbc);

        for(int i = 0; i < 10; i++){
            textFields[i] = new JTextField();
        }
        for(int i = 0; i < 10; i++){
            messages[i] = new JLabel("Enter Topping "+(i+1));
        }

        for(int i = 0; i < 10; i++){
            messages[i].setVisible(false);
            gbc.gridx = 0;
            gbc.gridy = i+1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panel.add(messages[i],gbc);

            textFields[i].setVisible(false);
            gbc.gridx = 1;
            gbc.gridy = i+1;
            gbc.ipadx = 35;
            panel.add(textFields[i],gbc);

        }


        deli = new JRadioButton("Delivery?");
        deli.addActionListener(this::ActDeli);
        gbc.gridx = 0;
        gbc.gridy = 11;
        panel.add(deli,gbc);

        addr = new JTextField("Enter Address");
        addr.setVisible(false);
        //addr.setVisible(false);
        gbc.gridx = 0;
        gbc.gridy = 12;
        panel.add(addr, gbc);


        Color y = new Color(251,219,101);
        Color c = new Color(220, 20, 60);
        submit = new JButton("Ready To Launch!");
        submit.addActionListener(this::submitAct);
        submit.setBackground(y);
        submit.setForeground(c);
        gbc.gridx = 0;
        gbc.gridy = 14;
        panel.add(submit, gbc);

        finalResult = new JLabel("");
        gbc.gridx = 0;
        gbc.gridy = 15;
        panel.add(finalResult, gbc);

        frame.setSize(500,500);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(panel2,BorderLayout.PAGE_START);
        frame.add(panel3, BorderLayout.PAGE_END);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("PizzaDemo");
        frame.setVisible(true);
    }



    public void ActDeli(ActionEvent e){
        GridBagConstraints gbc = new GridBagConstraints();
        if(deli.isSelected()) {
            addr.setVisible(true);
        }else{
            addr.setVisible(false);
        }
        panel.revalidate();

    }
    public void topAmt(ActionEvent e){
        selected = Integer.parseInt(String.valueOf(quantity.getSelectedItem()));
        for(int i = 0; i < selected;i++){
            messages[i].setVisible(true);
            textFields[i].setVisible(true);
        }
        for(int i = selected; i < 10;i++){
            messages[i].setVisible(false);
            textFields[i].setVisible(false);
        }
        panel.revalidate();

    }

    public void submitAct(ActionEvent e){
        selected = Integer.parseInt(String.valueOf(quantity.getSelectedItem()));
        if(selected != 0) {
            String[] toppings = new String[selected];
            if (deli.isSelected()) {
                for (int i = 0; i < selected; i++) {
                    toppings[i] = textFields[i].getText();
                }
                DeliveryPizza piz = new DeliveryPizza(toppings, selected, addr.getText());
                text = piz.toString();
                finalResult.setText(text);
            } else {
                for (int i = 0; i < selected; i++) {
                    toppings[i] = textFields[i].getText();
                }
                Pizza piz = new Pizza(toppings, selected);
                text = piz.toString();
                finalResult.setText(text);
            }
        }else{
            String[] nothing = {"Nothing"};
            if (deli.isSelected()) {
                DeliveryPizza piz = new DeliveryPizza(nothing, 1, addr.getText());
                piz.price = 19;
                text = piz.toString();
                finalResult.setText(text);
            }else{
                Pizza piz = new Pizza(nothing, 1);
                piz.price = 14;
                text = piz.toString();
                finalResult.setText(text);
            }
        }
        panel.revalidate();
    }
}

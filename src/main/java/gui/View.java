package gui;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame{
 private JPanel contentPane;
 private JPanel resultPanel;
 private JLabel resultLabel;
 private JLabel resultValueLabel;
 private JPanel polynomialsPanel;
 private JLabel firstPolynomialLabel;
 private JTextField firstPolynomialTextField;
 private JLabel secondPolynomialLabel;
 private JTextField secondPolynomialTextField;
 private JLabel operationsLabel;
 private JComboBox operationsComboBox;
 private JButton addButton;
 private JButton subtractButton;
 private JButton multiplicationButton;
 private JButton divideButton;
 private JButton differentiateButton;
 private JButton integrateButton;
 private JLabel differentiateLabel;
 private JLabel integrateLabel;

 Controller controller = new Controller(this);


 public View(String name) {
  super(name);
  this.prepareGui();
 }


 public void prepareGui(){
  this.setSize(500, 500);
  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
  this.contentPane = new JPanel(new GridLayout(2, 2));
  this.preparePolynomialsPanel();
  this.prepareResultPanel();
  this.setContentPane(this.contentPane);
 }

 private void prepareResultPanel() {
  this.resultPanel = new JPanel();
  this.resultPanel.setLayout(new GridLayout(1,1));
  this.resultLabel = new JLabel("Result", JLabel.CENTER);
  this.resultValueLabel = new JLabel("", JLabel.CENTER);
  this.resultPanel.add(this.resultLabel);
  this.resultPanel.add(this.resultValueLabel);
  this.contentPane.add(this.resultPanel);
 }

 private void preparePolynomialsPanel() {
  this.polynomialsPanel = new JPanel();
  this.polynomialsPanel.setLayout(new GridLayout(5, 2));



  this.firstPolynomialLabel = new JLabel("First polynomial", JLabel.CENTER);
  this.polynomialsPanel.add(this.firstPolynomialLabel);
  this.firstPolynomialTextField = new JTextField();
  this.polynomialsPanel.add(this.firstPolynomialTextField);
  this.secondPolynomialLabel = new JLabel("Second polynomial", JLabel.CENTER);
  this.polynomialsPanel.add(secondPolynomialLabel);
  this.secondPolynomialTextField = new JTextField();
  this.polynomialsPanel.add(secondPolynomialTextField);

  this.polynomialsPanel.setBorder( BorderFactory.createEmptyBorder(10,10,10,10));


  this.operationsLabel = new JLabel("Select operation", JLabel.CENTER);
  this.polynomialsPanel.add(this.operationsLabel);


  Box b = Box.createHorizontalBox();
  b.add(Box.createHorizontalGlue());


  this.polynomialsPanel.add(b);
  this.addButton= new JButton("+");
  this.addButton.setPreferredSize(new Dimension(50,40));
  this.addButton.setFont(new Font("Arial",Font.BOLD,15));
  this.addButton.setActionCommand("ADD");
  this.addButton.addActionListener(this.controller);
  b.add(addButton);
  b.add(Box.createHorizontalStrut(5));

  this.subtractButton = new JButton("-");
  this.addButton.setPreferredSize(new Dimension(50,40));
  this.subtractButton.setFont(new Font("Arial",Font.BOLD,15));
  this.subtractButton.setActionCommand("SUBTRACT");
  this.subtractButton.addActionListener(this.controller);
  b.add(subtractButton);
  b.add(Box.createHorizontalStrut(5));

  this.multiplicationButton = new JButton("x");
  this.addButton.setPreferredSize(new Dimension(50,40));
  this.multiplicationButton.setFont(new Font("Arial",Font.BOLD,15));
  this.multiplicationButton.setActionCommand("MULTIPLY");
  this.multiplicationButton.addActionListener(this.controller);
  b.add(multiplicationButton);
  b.add(Box.createHorizontalStrut(5));

  this.divideButton = new JButton("/");
  this.addButton.setPreferredSize(new Dimension(50,40));
  this.divideButton.setFont(new Font("Arial",Font.BOLD,15));
  this.divideButton.setActionCommand("DIVIDE");
  this.divideButton.addActionListener(this.controller);
  b.add(divideButton);
  b.add(Box.createHorizontalGlue());


  this.differentiateLabel = new JLabel("Differentiate the first polynomial", JLabel.CENTER);
  this.polynomialsPanel.add(differentiateLabel);


  this.differentiateButton = new JButton("Differentiate");
  this.differentiateButton.setPreferredSize(new Dimension(40,30));
  this.differentiateButton.setFont(new Font("Arial",Font.PLAIN,15));
  this.differentiateButton.setActionCommand("DIFFERENTIATE");
  this.differentiateButton.addActionListener(this.controller);
  this.polynomialsPanel.add(differentiateButton);


  this.integrateLabel = new JLabel("Integrate the first polynomial", JLabel.CENTER);
  this.polynomialsPanel.add(integrateLabel);

  this.integrateButton = new JButton("Integrate");
  this.integrateButton.setPreferredSize(new Dimension(40,30));
  this.integrateButton.setFont(new Font("Arial",Font.PLAIN,15));
  this.integrateButton.setActionCommand("INTEGRATE");
  this.integrateButton.addActionListener(this.controller);
  this.polynomialsPanel.add(integrateButton);


  this.contentPane.add(this.polynomialsPanel);
 }

 public JTextField getFirstPolynomialTextField() {
  return firstPolynomialTextField;
 }

 public JTextField getSecondPolynomialTextField() {
  return secondPolynomialTextField;
 }

 public JLabel getResultValueLabel() {
  return resultValueLabel;
 }


}

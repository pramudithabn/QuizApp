/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pramuditha Buddhini
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.ImageIcon;

public class RadioQuestion extends JPanel implements ActionListener {

    int correctAns;
    Quiz quiz;
    int selected;
    boolean used;
    //questions
    JPanel qPanel = new JPanel();

    JPanel header = new JPanel();
    int qnumber = 1;

    //answers
    JPanel aPanel = new JPanel();
    JRadioButton[] responses;
    ButtonGroup group = new ButtonGroup();

    JPanel msgPanel = new JPanel();

    //bottom
    JPanel botPanel = new JPanel();
    JButton next = new JButton("Next");
    JButton finish = new JButton("Finish");

    public RadioQuestion(String q, String[] options, int ans, Quiz quiz) {
        this.quiz = quiz;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

//                setLayout(new BorderLayout(this,BorderLayout.);
//        System.out.print(ans + " ");
        correctAns = ans;
//        System.out.print(correctAns + " ");

        JLabel title = new JLabel("Question ");

        title.setFont(new Font("Tahoma", 1, 18));
        header.add(title);
//                header.setAlignmentX(RIGHT_ALIGNMENT);
        add(header, BorderLayout.LINE_START);

        //question
        ImageIcon icon3 = new ImageIcon("D:\\Images\\q.png");
        JLabel qstn = new JLabel(q);
        qstn.setIcon(icon3);
        qPanel.add(qstn);

        add(qPanel);
        qPanel.setSize(500, 200);
        qPanel.setAlignmentX(RIGHT_ALIGNMENT);
        qPanel.setFont(new Font("Tahoma", Font.BOLD, 12));
        //answer
        responses = new JRadioButton[options.length];
        for (int i = 0; i < options.length; i++) {
            responses[i] = new JRadioButton(options[i]);
            responses[i].addActionListener(this);
            group.add(responses[i]);
            Box box1 = Box.createVerticalBox();
            box1.add(responses[i]);
            aPanel.add(box1, BorderLayout.CENTER);

//			aPanel.add(responses[i], BorderLayout.CENTER);
        }
        add(aPanel);
        //bottom
        next.addActionListener(this);
        next.setPreferredSize(new Dimension(100, 40));
        finish.addActionListener(this);
        finish.setPreferredSize(new Dimension(100, 40));
        botPanel.add(next);
        botPanel.add(finish);
        add(botPanel);

        add(msgPanel);

    }

    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        

//        System.out.println(selected + "=selected");
        //next button
//        if(src.equals(next)){
//			showResult();
//			if(selected==correctAns){
//				used=true;
//				quiz.next();
//			}
//		}
        if (src.equals(next)) {
            showResult();
            
                used = true;
                quiz.next();
            
        }
        
        
        
//        quiz.showSummary();

        //finish button
        if (src.equals(finish)) {
            quiz.showSummary();
        }
        //radio buttons
        for (int i = 0; i < responses.length; i++) {
            if (src == responses[i]) {
                selected = i;
            }
        }
    }

    public void showResult() {
//        String text = responses[selected].getText();
        quiz.total++;

//        JLabel correct = new JLabel("Correct!");
//        msgPanel.add(correct);
//        correct.setForeground(Color.green);
//
//        JLabel wrong = new JLabel("Wrong!");
//        msgPanel.add(wrong);
//        wrong.setForeground(Color.red);
        if (selected == correctAns) {
            quiz.corrects++;
            ImageIcon icon1 = new ImageIcon("D:\\Images\\right.png");
            JOptionPane.showMessageDialog(null, " Correct!", "Result", JOptionPane.INFORMATION_MESSAGE, icon1);
        } else {
            quiz.wrongs++;
            ImageIcon icon2 = new ImageIcon("D:\\Images\\wrong.png");
            JOptionPane.showMessageDialog(null, " Wrong", "Result", JOptionPane.INFORMATION_MESSAGE, icon2);
        }
        
        System.out.println("qtotal " + quiz.total);
        System.out.println("qnum " + quiz.numQs);
        System.out.println("wrong " + quiz.wrongs);
        System.out.println("qcorrect " + quiz.corrects + "\n\n");
        
        System.out.println(((quiz.numQs - quiz.corrects - quiz.wrongs) == 0));
        if ((quiz.numQs - quiz.corrects - quiz.wrongs) == 0) {
            quiz.showSummary();
        }
    }
}

package quiz;
/**
 *
 * @author zNova
 */
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Quiz {
    
    Vector<JRadioButton> answervector = new Vector<>();//Vector containing RadioButton data
    int currentquestion = 1;
    static int index = 0;
    int score = 0;
    int wronganswer = 0;
    String[] QuestionABCD = {"A.) ","B.) ","C.) ","D.) "};
    Vector<Integer> numberchecker = new Vector<>();
    boolean questionasked = false;
    String[] question0 = {"JLabel","JButton","JTextField","JComboBox"};//JLabel
    String[] question1 = {"Java","Package","Library","Import"};//Package
    String[] question2 = {"Loop","Recursion","If Statement","Else If Statement"};//Loop
    String[] question3 = {"5","6","4","7"};//how many times does this loop print i
    String[] question4 = {"notepad","Visual Studio","Netbeans","kali linux"};//The i.d.e we are using
    String[] question5 = {"Built-in package","User-defined package","Primitive Package","JDK Package"};//2 types of package
    String[] question6 = {"JTextField","JLabel","JFormattedText","JLabelText"};//JTextField
    String[] question7 = {"While loop","Range-based loop","do while loop","While loop"};//Do while loop
    String[] question8 = {"JTable","JComboBox","JProgressBar","JButton"};//JButton
    String[] question9 = {"JCheckBox","JRadioButton","JComboBox","JButton"};//JRadioButton
    int[] answers = {0,1,0,3,2,1,0,2,3,1};
    String[][] question2DArray = {question0,question1,question2,question3,question4,question5,question6,question7,question8,question9};
    
    String Question1 = "C:\\Users\\PC1\\Desktop\\LPU\\Comprog\\Finished\\Quiz\\Number1.png";
    String Question2 = "C:\\Users\\PC1\\Desktop\\LPU\\Comprog\\Finished\\Quiz\\Number2.png";
    String Question3 = "C:\\Users\\PC1\\Desktop\\LPU\\Comprog\\Finished\\Quiz\\Number3.png";
    String Question4 = "C:\\Users\\PC1\\Desktop\\LPU\\Comprog\\Finished\\Quiz\\Number4.png";
    String Question5 = "C:\\Users\\PC1\\Desktop\\LPU\\Comprog\\Finished\\Quiz\\Number5.png";
    String Question6 = "C:\\Users\\PC1\\Desktop\\LPU\\Comprog\\Finished\\Quiz\\Number6.png";
    String Question7 = "C:\\Users\\PC1\\Desktop\\LPU\\Comprog\\Finished\\Quiz\\Number7.png";
    String Question8 = "C:\\Users\\PC1\\Desktop\\LPU\\Comprog\\Finished\\Quiz\\Number8.png";
    String Question9 = "C:\\Users\\PC1\\Desktop\\LPU\\Comprog\\Finished\\Quiz\\Number9.png";
    String Question10 = "C:\\Users\\PC1\\Desktop\\LPU\\Comprog\\Finished\\Quiz\\Number10.png";
    String[] QuestionPictureArray = {Question1,Question2,Question3,Question4,Question5,Question6,Question7,Question8,Question9,Question10};

    int answer = -1;
    String[] rating = {"Boi you need to study","Nice try","Close, still goodjob","Congrats 1337Haxor"};
    boolean doonce = false;
    boolean answeronce = false;
    
    private int randomint(int min,int max){
        return ThreadLocalRandom.current().nextInt(min, max);
    }
    
    int CheckScore(){
        if(score < 5)
            return 0;
        else if(score > 5 && score < 7)
            return 1;
        else if(score > 7 && score < 10)
            return 2;
        else if(score == 10)
            return 3;
        return 0;
    }
    
    boolean isQuizOver = false;
    
    void Hide(JLabel question){
        for(int i = 0; i < answervector.size(); i++){
            answervector.elementAt(i).setVisible(false);
        }
        question.setIcon(null);
    }
    
    void QuizStatus(JButton btn,JLabel question){
        if(index == 10){
            btn.setBounds(180,270,200,20);
            btn.setText("Back to Main Menu");
            Hide(question);
            IconEvent(question);
            wronganswer = 10 - score;
            JOptionPane.showMessageDialog(null, rating[CheckScore()]
            + "\nGiving a correct answer chance: \t\t"+(int)(((float)(10-wronganswer)/10)*100)+"%", rating[CheckScore()],JOptionPane.INFORMATION_MESSAGE );
            index=0;
            currentquestion = 0;
            score = 0;
            wronganswer = 0;
            isQuizOver = true;
        }else {
            btn.setBounds(180,270,100,20);
            btn.setText("Submit");
        }
        
    }

    boolean CheckRepeat(int num){
        if(numberchecker.size() == question2DArray.length)numberchecker.clear();//Prevent stackover flow as we are doing recursion
        if(numberchecker.size() != 0){
        for(int i = 0; i < numberchecker.size(); i++){
            if(numberchecker.elementAt(i) == num)return true;
        }}
        return false;
    }
    
    
    
    private int RNGnoRepeat(){
    int tmp;
    int randomInt = randomint(0,question2DArray.length);
    if(CheckRepeat(randomInt)){
        return RNGnoRepeat();
    }else{
        tmp = randomInt;
    }
    numberchecker.add(randomInt);
    return tmp;
    }
    
    int curPseudorandom = 0;
    void QuestionEvent(JLabel question){
        if(currentquestion == 1){//cause the pseudorandom variable has a value at the main method already, repeating the value instantiation will bug out the events
            if(!doonce){
                question.setIcon(new ImageIcon(QuestionPictureArray[curPseudorandom]));
                for(int i = 0; i < answervector.size(); i++){
                JRadioButton items = answervector.elementAt(i);
                items.setText(QuestionABCD[i] + question2DArray[curPseudorandom][i]);
                answeronce = false;
                }
            } 
            doonce=true;
        }else{
            curPseudorandom = RNGnoRepeat();
            if(!doonce){
                question.setIcon(new ImageIcon(QuestionPictureArray[curPseudorandom]));
                for(int i = 0; i < answervector.size(); i++){
                JRadioButton items = answervector.elementAt(i);
                items.setText(QuestionABCD[i] + question2DArray[curPseudorandom][i]);
                answeronce = false;
                }
            } 
            doonce=true;  
        }
    }

    void SelectOneOnly(){//Shit method
       answervector.elementAt(0).addActionListener(new ActionListener() {
    @Override
        public void actionPerformed(ActionEvent e){
         Object src=e.getSource();  
         for(int i = 0 ; i < answervector.size(); i++){
             if(src == answervector.elementAt(i))answer = i;
             if(answervector.elementAt(0) == answervector.elementAt(i))continue;
             answervector.elementAt(i).setSelected(false);
         }   
        }});
       
       answervector.elementAt(1).addActionListener(new ActionListener() {
    @Override
        public void actionPerformed(ActionEvent e){
         Object src=e.getSource();  
         for(int i = 0 ; i < answervector.size(); i++){
             if(src == answervector.elementAt(i))answer = i;
             if(answervector.elementAt(1) == answervector.elementAt(i))continue;
             answervector.elementAt(i).setSelected(false);
         }   
        }});
       
       answervector.elementAt(2).addActionListener(new ActionListener() {
    @Override
        public void actionPerformed(ActionEvent e){
         Object src=e.getSource();  
         for(int i = 0 ; i < answervector.size(); i++){
             if(src == answervector.elementAt(i))answer = i;
             if(answervector.elementAt(2) == answervector.elementAt(i))continue;
             answervector.elementAt(i).setSelected(false);
         }   
        }});
       
       answervector.elementAt(3).addActionListener(new ActionListener() {
    @Override
        public void actionPerformed(ActionEvent e){
         Object src=e.getSource();  
         for(int i = 0 ; i < answervector.size(); i++){
             if(src == answervector.elementAt(i))answer = i;
             if(answervector.elementAt(3) == answervector.elementAt(i))continue;
             answervector.elementAt(i).setSelected(false);
         }   
        }});
    }
    
    void Reset(){
        for(int i = 0 ; i < answervector.size(); i++){
             answervector.elementAt(i).setSelected(false);
        }   
        if(index > 10){
        wronganswer = 0;
        score = 0;
        currentquestion = 0;
        }
    }
    
    boolean CheckSelected(){
        for(int i = 0;i < answervector.size(); i++){
            if(answervector.elementAt(i).isSelected())return true;
        }
        return false;
    }
    boolean CheckAnswer(){
        return (answer == answers[curPseudorandom]);
    }
    
    void AnswerEvent(){
        if(!answeronce){
           if(answer != -1){
            if(CheckAnswer()){
            //if(answervector.elementAt(answer).getText().contains(question2DArray[curPseudorandom][answers[curPseudorandom]])){//Another way to do it but the method above is easier
            score++;
            doonce=false;
            }
            answeronce=true;
            }
            }
    }
    
    void IconEvent(JLabel question){
        if(index == 10){
        question.setBounds(0,0,500,260);
        if(score == 10){
        question.setIcon(new ImageIcon("C:\\Users\\PC1\\Desktop\\LPU\\Comprog\\Finished\\Quiz\\Congrats.gif"));
        }else if(score < 4){
        question.setIcon(new ImageIcon("C:\\Users\\PC1\\Desktop\\LPU\\Comprog\\Finished\\Quiz\\FailScreen.png"));
        }else if(score > 4 && score < 7){
         question.setIcon(new ImageIcon("C:\\Users\\PC1\\Desktop\\LPU\\Comprog\\Finished\\Quiz\\Good.png"));   
        }else if(score > 6 && score < 9){
         question.setIcon(new ImageIcon("C:\\Users\\PC1\\Desktop\\LPU\\Comprog\\Finished\\Quiz\\Notbad.png"));   
        }
       }
    }
    
    static JLabel redbot = null;
    void MainEvent(JFrame frame,JButton btn,JLabel lbl,JLabel xs,JLabel question){
        if(!isQuizOver){
        if(CheckSelected()){
        doonce=false;
        Reset();
        index++;
        AnswerEvent();
        xs.setText("Pts: " + score);
        if(currentquestion < 10){
        currentquestion++;
        lbl.setText("Q: " + currentquestion);
        QuestionEvent(question);
        }else{
        }
        QuizStatus(btn,question);
        }else{
        JOptionPane.showMessageDialog(null, "Select something", "No radio button selected",JOptionPane.INFORMATION_MESSAGE );
        }}else{
            frame.dispose();
            FirstScreen();
        }
    }
    
    void HackIt(JButton btn){
        answervector.elementAt(0).doClick();
        answer = answers[curPseudorandom];//Hacker man
        btn.doClick();
    }
    
    static void MainMenuScreenEvent(JFrame frame){
        frame.dispose();
        new Quiz();
    }
    
    
    
    public static void FirstScreen(){
        JFrame frame = new JFrame("Quiz Main Menu");
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);
        JButton btn = new JButton("Start");
        btn.setBackground(Color.black);
        btn.setForeground(Color.white);
        JLabel question = new JLabel();
        question.setIcon(new ImageIcon("C:\\Users\\PC1\\Desktop\\LPU\\Comprog\\Finished\\Quiz\\Main.png"));
        question.setBounds(0,0,500,260);
        redbot = question;
        index = 0;
        btn.addActionListener((l) -> MainMenuScreenEvent(frame));
        btn.setBounds(200, 270, 100, 20);

        panel.add(btn);
        panel.add(question);
        frame.add(panel);
        frame.setSize(500,330);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);//Set the form to center
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
    
    Quiz(){
        JFrame frame = new JFrame("Quiz");
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);
        JButton btn = new JButton("Start");
        btn.setBackground(Color.black);
        btn.setForeground(Color.white);
        JLabel question = new JLabel();
        JLabel currentitem = new JLabel("Q: " + currentquestion);
        currentitem.setForeground(Color.yellow);
        currentitem.setBounds(5,5,100,11);
        JLabel Score = new JLabel("Pts: " + score);
        Score.setForeground(Color.green);
        Score.setBounds(450,5,50,11);
        
        JButton hackerman = new JButton("Hack");
        hackerman.setBackground(Color.black);
        hackerman.setForeground(Color.white);
        
        hackerman.addActionListener((l) -> HackIt(btn));
        hackerman.setBounds(50, 270, 100, 20);
        
        question.setBounds(10,30,470,140);
        JRadioButton AnswerA = new JRadioButton("A.)");
        JRadioButton AnswerB = new JRadioButton("B.)");
        JRadioButton AnswerC = new JRadioButton("C.)");
        JRadioButton AnswerD = new JRadioButton("D.)");
        
        answervector.add(AnswerA);
        answervector.add(AnswerB);
        answervector.add(AnswerC);
        answervector.add(AnswerD);
        
        SelectOneOnly();//Prevent multiple answer
        curPseudorandom = RNGnoRepeat();
        QuestionEvent(question);//Initialize the first question
        
        btn.addActionListener((l) -> MainEvent(frame,btn,currentitem,Score,question));
        btn.setBounds(180, 270, 100, 20);
        AnswerA.setBounds(10,180,200,20);
        AnswerB.setBounds(10,230,200,20);
        AnswerC.setBounds(270,180,200,20);
        AnswerD.setBounds(270,230,200,20);
        panel.add(hackerman);//for testing purpose xd
        panel.add(btn);
        panel.add(Score);
        panel.add(currentitem);
        for(int i = 0 ; i < answervector.size();i++){
            answervector.elementAt(i).setBackground(Color.black);
            answervector.elementAt(i).setForeground(Color.white);
            panel.add(answervector.elementAt(i));
        }
        panel.add(question);
        frame.add(panel);
        frame.setSize(500,330);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);//Set the form to center
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        FirstScreen();
    }
    
}

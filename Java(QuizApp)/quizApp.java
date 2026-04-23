import java.util.*;

class Questions {
    String question;
    String[] options;
    int key;
    int userOption=0;
    String status="NA";

    Questions(String question,String[] options,int key){
        this.question=question;
        this.options=options;
        this.key=key;
    }
}

class questionBucket{
    public void defaultquestions(ArrayList<Questions>quizList){
        quizList.add(new Questions("Which component is used to compile, debug, and execute Java programs?",
            new String[]{"JRE","JIT","JDK","JVM"},
            4));
        quizList.add(new Questions("Which of the follwing is not a Java Feature?",
            new String[]{"Object-Oriented","Use of Pointers","Portable","Dynamic and Extensible"},
            2));
        quizList.add(new Questions("What is the extension of java code files?",
            new String[]{".js",".txt",".class",".java"},
            4));
    }
    public void addQuestion(ArrayList<Questions> quizList){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the question: ");
        String question=sc.nextLine();
        System.out.println("Enter the number of options: ");
        int numOptions=sc.nextInt();
        sc.nextLine();
        String[] options=new String[numOptions];
        System.out.println("Enter options one by one new line(No need of entering option number)");
        for(int i=0;i<numOptions;i++){
            options[i]=sc.nextLine();
        }
        System.out.println("Enter the correct option number: ");
        int key=sc.nextInt();
        quizList.add(new Questions(question,options,key));
    }
} 

public class quizApp{
    public static void main(String[] args){
        while(true){
            ArrayList<Questions> quizList = new ArrayList<>();
            Scanner sc=new Scanner(System.in);
            int Score=0;
            System.out.println("====Welcome to Java Quiz!====");
            System.out.println("Do you start(s) the quiz or add(a) own question or exit(e)?");
            System.out.println("Enter s to start and a to add question e to exit");
            System.out.println("Note: You will get 40 seconds to answer each question.");
            String choice=sc.nextLine();
            if(choice.equalsIgnoreCase("e")){
                System.out.println("Thank you for attending the quiz.");
                break;
            }
            questionBucket qb=new questionBucket();
            if (choice.equalsIgnoreCase("a")){
                qb.addQuestion(quizList);
                System.out.println("Question added, now can we start quiz? (y/n)");
                String stChoice=sc.nextLine();
                if(stChoice.equalsIgnoreCase("n")){
                    System.out.println("Thank you for attending the quiz.");
                    break;
                }
            }
            qb.defaultquestions(quizList);
            
            for(Questions q: quizList){
                final boolean[] timeUp={false};
                Thread timer=new Thread(()->{
                    try{
                        Thread.sleep(40000);//40 seconds
                        timeUp[0]=true;
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                });
                timer.setDaemon(true); // daemon thread will automatically stop when the main thread finishes
                timer.start();
                System.out.println(q.question);
                for(int i=0;i<q.options.length;i++){
                    System.out.println((i+1)+") "+q.options[i]);
                }
                System.out.println();
                int userOption=sc.nextInt();
                sc.nextLine();
                if (timeUp[0]){
                    System.out.println("Time's up! Moving to the next question.");
                    System.out.println("You answer will not considered for the above question.");
                }
                else{
                    q.userOption=userOption;
                    if(userOption<1 || userOption>q.options.length){
                        System.out.println("Invalid option. Please select a valid option.");
                    }
                    else if (userOption==q.key){
                        Score++;
                        q.status="C";
                    }
                    else{
                        q.status="W";
                    }
                }
            }
            double percentage=(double)(Score*100)/quizList.size();
            System.out.println("Your Score is: "+Score);
            System.out.println("Your percentage is: "+percentage+"%");
            if(percentage>=80){
                System.out.println("Excellent!");
            }
            else if(percentage>=60){
                System.out.println("Good!");
            }
            else{
                System.out.println("Keep Practicing");
            }
            System.out.println("Do you want correct(c) or wrong(w) answers or exit(e)?");
            String feedbackCh=sc.next();
            if(feedbackCh.equalsIgnoreCase("e")){
                System.out.println("Thank you for attending the quiz.");
                break;
            }
            else if(feedbackCh.equalsIgnoreCase("c")){
                System.out.println("Correct Answers:");
                for (Questions q: quizList){
                    if (q.status.equals("C")){
                        System.out.println(q.question);
                        for(int i=0;i<q.options.length;i++){
                            System.out.println((i+1)+") "+q.options[i]);
                        }
                        System.out.println("Correct Answer and Your Answer: "+q.options[q.key-1]);
                    }
                }
            }
            else if(feedbackCh.equalsIgnoreCase("w")){
                System.out.println("Wrong Answers:");
                for (Questions q: quizList){
                    if(q.status.equals("W")){
                        System.out.println(q.question);
                        for(int i=0;i<q.options.length;i++){
                            System.out.println((i+1)+") "+q.options[i]);
                        }
                        System.out.println("Correct Answer: "+q.options[q.key-1]);
                        System.out.println("Your Answer: "+q.options[q.userOption-1]);
                    }
                }
            }
        }
    }
}

import java.util.*;

public class MainInterface
{
    public static Console c = new Console();
    public static boolean helpNeeded = false;
    public static int numSymptoms = 0;
    
    public static void main(String[] args)
    {
        c.print("Welcome!\n\nFeel free to check out these resources:\n\n--Use the hotline to get help\n--Take the NIMH Brief Suicide Safety Assessment (Press ENTER to take assessment)\n--Try our interactive demo about labels");
        c.scanString();
        runTest();
        
    }
    
    public static void runTest()
    {
        if (askQuestion("In the past few weeks, have you been thinking about killing yourself?") == 'y')
        {
            askQuestion("Are you having these thoughts right now?");
            if (askQuestion("Do you have a plan to kill yourself?") == 'y')
                c.openWebpage("https://www.thetrevorproject.org/get-help/");
        }
        if (askQuestion("Have you tried to hurt yourself before?") == 'y')
            askFollowUp("Did you recieve treatment?");
        askSymptom("Depression");
        askSymptom("Anxiety");
        askSymptom("Impulsivity/Recklessness");
        askSymptom("Hopelessness");
        askSymptom("Isolation");
        askSymptom("Irritability");
        askSymptom("Substance or Alcohol Use");
        askSymptom("Abnormal Sleep Patterns");
        askSymptom("Abnormal Appetite");
        askSymptom("Abnormal Thoughts or Feelings");
        if (numSymptoms >= 5)
            helpNeeded = true;
        askQuestionOpp("Is there a trusted person you can talk to?");
        if (helpNeeded)
            c.openWebpage("https://www.thetrevorproject.org/get-help/");
    }
    
    public static char acceptInput()
    {
        char answer = ' ';
        
        do
        {
            String input = c.scanString().toLowerCase();
            if (input.length() > 0)
                answer = input.charAt(0);
        }
        while ((answer != 'y') && (answer != 'n'));
        
        return answer;
    }
    
    public static char askQuestion(String question)
    {
        c.clear();
        c.println(question + "\n\n(Yes or no)\n");
        char answer = acceptInput();
        if (answer == 'y')
            helpNeeded = true;
        return answer;
    }
    
    public static char askQuestionOpp(String question)
    {
        c.clear();
        c.println(question + "\n\n(Yes or no)\n");
        char answer = acceptInput();
        if (answer == 'n')
            helpNeeded = true;
        return answer;
    }
    
    public static void askFollowUp(String question)
    {
        c.clear();
        c.println(question + "\n\n(Yes or no)\n");
        c.scanString();
    }
    
    public static char askSymptom(String question)
    {
        c.clear();
        c.println("Have you experienced the following symptom:\n\n" + question + "\n\n(Yes or no)\n");
        char answer = acceptInput();
        if (answer == 'y')
            numSymptoms++;
        return answer;
    }
}
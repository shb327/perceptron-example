import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("\n\n\"Welcome to the Perceptron Project 3.2.7\"\n");

        HashSet<Vector> trainingSet = Vector.readVectorsFromFile(true);

        Scanner scanner = new Scanner(System.in);
        double learningRate = 0;
        int maxNumberOfErrors = 0;
        boolean checkA = true;
        while (checkA) {
            System.out.println("\nPlease, specify the learning rate(rational number in range{0,1}):");
            if (scanner.hasNextDouble()) {
                learningRate = scanner.nextDouble();
                if(!(learningRate > 0)||!(learningRate < 1)){
                    System.err.print("Learning rate should be rational number in range from 1 to 0.\nTry again..,\n");
                    continue;
                }
                checkA = false;
            } else {
                System.err.print("Learning rate should be positive rational number.\nTry again..,\n");
            }
        }

        boolean checkB = true;
        while (checkB) {
            System.out.println("\nPlease, specify allowed number of errors in data set:");
            if (scanner.hasNextInt()) {
                maxNumberOfErrors = scanner.nextInt();
                if(maxNumberOfErrors < 0){
                    System.err.print("Max number of errors should be positive integer number.\nTry again..,\n");
                    continue;
                }
                checkB = false;
            } else {
                System.err.print("Max number of errors should be positive integer number.\nTry again..,\n");
            }
        }

        Perceptron perceptron = new Perceptron(learningRate);
        perceptron.classifyVectors(trainingSet,maxNumberOfErrors,true);

        System.out.println("Do ou want to enter Vector manually or use example set?");
        while (true) {
            System.out.println("Enter M(Enter vector manually) or E(Provide example set):");
            String s1;
            if (scanner.hasNextLine()) {
                s1=scanner.next();
                switch (s1){
                    case "M":
                        Vector vector = Vector.createVector();
                        perceptron.classifyVector(vector);
                        break;
                    case "E":
                        HashSet<Vector> exampleSet = Vector.readVectorsFromFile(false);
                        perceptron.classifyVectors(exampleSet,maxNumberOfErrors,false);
                        break;
                    default:
                        System.err.print("You only have choice of \"M\" or \"E\", other inputs are not allowed.\nTry again..,\n");
                }
            } else {
                System.err.print("Your input should be a single character from English Alphabet.\nTry again..,\n");
            }
            System.out.println("Are you done?");
            System.out.println("Enter YES or NO:");
            String s2;
            if (scanner.hasNextLine()) {
                s2=scanner.next();
                switch (s2){
                    case "YES":
                        return;
                    case "NO":
                        System.out.println();
                        break;
                    default:
                        System.err.print("You only have choice of \"YES\" or \"NO\", other inputs are not allowed.\nTry again..,\n");
                }
            } else {
                System.err.print("Your input should be \"YES\" or \"NO\", other inputs are not allowed.\nTry again..,\n");
            }
        }
    }
}

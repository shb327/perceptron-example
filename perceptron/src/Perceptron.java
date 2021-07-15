import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class Perceptron {
    private double learningRate;
    private Vector weightVector;
    private double bias;

    Perceptron(double learningRate) {
        this.learningRate = learningRate;
        bias = Math.random();
        weightVector = new Vector(
                Math.random(),
                Math.random(),
                Math.random(),
                Math.random(),
                "weightVector");
    }

    @SuppressWarnings("Duplicates")
    void classifyVectors(HashSet<Vector> hashSet, int allowedErrors, boolean training) {
        if (training)
            System.out.println("\nForming Bias and Weight Vector using training set.\n");
        else
            System.out.println("\nClassifying vectors using pre-formed Weight Vector and Bias.\n");
        boolean done = true;
        int counter = 0;
        do {
            int errorCounter = 0;
            double type;
            int y;
            for (Vector vector : hashSet) {
                double net = weightVector.getX() * vector.getX() +
                        weightVector.getY() * vector.getY() +
                        weightVector.getZ() * vector.getZ() +
                        weightVector.getU() * vector.getU() - bias;
                y = (net >= 0) ? 1 : 0;
                type = (vector.getValue().equals("Iris-setosa")) ? 0 : 1;
                weightVector.setX(weightVector.getX() + learningRate * (type - y) * vector.getX());
                weightVector.setY(weightVector.getY() + learningRate * (type - y) * vector.getY());
                weightVector.setZ(weightVector.getZ() + learningRate * (type - y) * vector.getZ());
                weightVector.setU(weightVector.getU() + learningRate * (type - y) * vector.getU());
                bias = bias - learningRate * (type - y);
                errorCounter += Math.abs(type - y);
            }
            double accuracy = (double) (hashSet.size() - errorCounter) / (double) hashSet.size();
            System.out.println("Iteration " + (++counter) + ")");
            System.out.println("Number of correctly classified vectors: " + (hashSet.size() - errorCounter) + "/" + (hashSet.size()));
            System.out.println("Accuracy: " + accuracy * 100 + "%\n");
            if (errorCounter < allowedErrors) done = false;
        } while (done);
    }

    @SuppressWarnings("Duplicates")
    void classifyVector(Vector vector) {
        System.out.println("\nClassifying vector using pre-formed Weight Vector and Bias.\n");
        double type;
        int y;
        double net = weightVector.getX() * vector.getX() +
                weightVector.getY() * vector.getY() +
                weightVector.getZ() * vector.getZ() +
                weightVector.getU() * vector.getU() - bias;
        y = (net >= 0) ? 1 : 0;
        type = (vector.getValue().equals("Iris-setosa")) ? 0 : 1;
        weightVector.setX(weightVector.getX() + learningRate * (type - y) * vector.getX());
        weightVector.setY(weightVector.getY() + learningRate * (type - y) * vector.getY());
        weightVector.setZ(weightVector.getZ() + learningRate * (type - y) * vector.getZ());
        weightVector.setU(weightVector.getU() + learningRate * (type - y) * vector.getU());
        bias = bias - learningRate * (type - y);
        boolean match = Math.abs(type - y) == 0;
        System.out.println("Was the expected value of the vector correct?");
        if (match) System.out.println("Yes\n");
        else System.out.println("No\n");
    }
}
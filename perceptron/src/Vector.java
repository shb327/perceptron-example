import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;

public class Vector implements Comparable<Vector> {
    private double x, y, z, u;
    private String value;

    Vector(double x, double y, double z, double u, String value) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.u = u;
        this.value = value;
    }

    double getX() {
        return x;
    }

    void setX(double x) {
        this.x = x;
    }

    double getY() {
        return y;
    }

    void setY(double y) {
        this.y = y;
    }

    double getZ() {
        return z;
    }

    void setZ(double z) {
        this.z = z;
    }

    double getU() {
        return u;
    }

    void setU(double u) {
        this.u = u;
    }

    String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Vector with x=" + x + ", y=" + y + ", z=" + z + ", u=" + u + ",and  value=" + value + ";";
    }

    @Override
    public int compareTo(Vector o) {
        return Comparator.comparing(Vector::getValue)
                .thenComparingDouble(Vector::getX)
                .thenComparingDouble(Vector::getY)
                .thenComparingDouble(Vector::getZ)
                .thenComparingDouble(Vector::getU)
                .compare(this, o);
    }

    @SuppressWarnings("ContinueOrBreakFromFinallyBlock")
    static HashSet<Vector> readVectorsFromFile(boolean trainSet) {
        String msg;
        HashSet<Vector> set = new HashSet<>();
        if (trainSet)
            msg = "training set:";
        else
            msg = "example set:";
        while (true) {
            System.out.println("\nPlease, specify path to " + msg);
            Scanner scanner = new Scanner(System.in);
            String path = scanner.nextLine();
            System.out.printf("Path to the file is: %s%n", path);
            BufferedReader bufferedReader = null;
            String line;
            String splitter = ",";
            try {
                bufferedReader = new BufferedReader(new FileReader(path));
                System.out.println("\nContent of the " + msg);
                int counter = 1;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] vector = line.split(splitter);
                    Vector v = new Vector(
                            Double.parseDouble(vector[0].replaceAll("[^\\d.]", "")),
                            Double.parseDouble(vector[1]),
                            Double.parseDouble(vector[2]),
                            Double.parseDouble(vector[3]),
                            vector[4]);
                    set.add(v);
                    System.out.println("\t"+(counter++) + ") " + v);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
        System.out.println();
        return set;
    }

    static Vector createVector(){
        Vector vector;
        double x = 0;
        double y = 0;
        double z = 0;
        double u = 0;
        String value = null;
        boolean check = true;
        while (check) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nPlease, specify \"X\":");
            if (scanner.hasNextDouble()) {
                x = scanner.nextDouble();
            } else {
                System.err.print("\"X\" should be rational number.\nPlease try again..,\n");
                continue;
            }
            System.out.println("\nPlease, specify \"Y\":");
            if (scanner.hasNextDouble()) {
                y = scanner.nextDouble();
            } else {
                System.err.print("\"Y\" should be rational number.\nPlease try again..,\n");
                continue;
            }
            System.out.println("\nPlease, specify \"Z\":");
            if (scanner.hasNextDouble()) {
                z = scanner.nextDouble();
            } else {
                System.err.print("\"Z\" should be rational number.\nPlease try again..,\n");
                continue;
            }
            System.out.println("\nPlease, specify \"U\":");
            if (scanner.hasNextDouble()) {
                u = scanner.nextDouble();
            } else {
                System.err.print("\"U\" should be rational number.\nPlease try again..,\n");
                continue;
            }
            System.out.println("\nPlease, specify expected value:");
            if (scanner.hasNext()) {
                value = scanner.next();
                if(!(value.equals("Iris-setosa")||value.equals("Iris-versicolor"))){
                    System.err.print("This value is not in the scope.\nPlease try again..,\n");
                    continue;
                }
            } else {
                System.err.print("Expected value should be string.\nPlease try again..,\n");
                continue;
            }
            check = false;
        }
        vector = new Vector(x,y,z,u,value);
        return vector;
    }
}
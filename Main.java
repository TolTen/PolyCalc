
import java.io.*;

//Pathler böyle kalmamalı, input.txt.txt ve output.txt.txt şeklinde olmalı
//Outputun sonucu 0 ise hiçbir şey printlemiyor, 0 yazması gerekiyor onu düzelt.

public class Main {
    public static void main(String[] args) {

        //File Reader and Writer
        File input = new File("input.txt");
        File output = new File(" output.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(input));
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            int testCases = Integer.parseInt(reader.readLine());
            for (int t = 0; t < testCases; t++) {
                String line = reader.readLine();
                String[] data = line.split(" ");
                if (data.length < 3) {
                    System.out.println("Invalid input format: " + line);
                    continue;
                }
                char operator = data[0].charAt(0); // +, -, *, /
                Polynomial p1 = parsePolynomial(data[1]); // 3x^2+2x+1
                Polynomial p2 = parsePolynomial(data[2]); // 2x^2+3x+4
                Polynomial result;

                switch (operator) {
                    case '+':
                        result = p1.add(p2); // 5x^2+5x+5
                        String resultString = result.toString();
                        if (!resultString.isEmpty() && resultString.charAt(0) == '+') {
                            resultString = resultString.substring(1);
                        }
                        writer.write(result.toString());
                        writer.newLine();
                        break;
                    case '-':
                        result = p1.subtraction(p2);
                        resultString = result.toString();
                        if (!resultString.isEmpty() && resultString.charAt(0) == '+') {
                            resultString = resultString.substring(1);
                        }
                        writer.write(result.toString());
                        writer.newLine();
                        break;
                    case '*':
                        result = p1.multiplication(p2);
                        resultString = result.toString();
                        if (!resultString.isEmpty() && resultString.charAt(0) == '+') {
                            resultString = resultString.substring(1);
                        }
                        writer.write(result.toString());
                        writer.newLine();
                        break;
                }
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Parse polynomial string and create a polynomial object
    public static Polynomial parsePolynomial(String polynomial) {
        Polynomial result = new Polynomial();
        String[] terms = polynomial.split("\\+|(?=\\-)"); // Terimleri ayır (artı veya eksi işaretine göre)
        for (String term : terms) {
            if (term.isEmpty()) continue;

            // Artı veya eksi işaretlerini ayırma
            boolean isNegative = term.charAt(0) == '-';
            if (isNegative || term.charAt(0) == '+') {
                term = term.substring(1);
            }

            String[] parts = term.split("(?=[xyz])"); // Katsayıyı ve değişkenleri ayır
            int coefficient = isNegative ? -1 : 1; // İşaretin belirlenmesi
            int degreeX = 0, degreeY = 0, degreeZ = 0;

            for (String part : parts) {
                if (part.isEmpty()) continue;

                if (Character.isDigit(part.charAt(0))) {
                    coefficient *= Integer.parseInt(part);
                } else {
                    if (part.contains("x")) {
                        if (part.length() == 1) {
                            degreeX++;
                        } else {
                            degreeX += Integer.parseInt(part.substring(1)); // Dereceyi ayır ve parse et
                        }
                    }
                    if (part.contains("y")) {
                        if (part.length() == 1) {
                            degreeY++;
                        } else {
                            degreeY += Integer.parseInt(part.substring(1));
                        }
                    }
                    if (part.contains("z")) {
                        if (part.length() == 1) {
                            degreeZ++;
                        } else {
                            degreeZ += Integer.parseInt(part.substring(1));
                        }
                    }
                }
            }
            result.insertLast(new TermNode(new Term(coefficient, degreeX, degreeY, degreeZ)));
        }
        return result;
    }
}

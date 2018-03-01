/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bill;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author rajbi
 */
public class Product {

    private static void writeBill(String quantity, String code, String name, String price) throws IOException {
        FileWriter fileWriter = new FileWriter("Bill.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        Random r = new Random();
        int bn = r.nextInt(9999);
        printWriter.println("Bill #"+bn);
        Date d = new Date();
        printWriter.println("Date: "+d);
        printWriter.println(quantity+"  "+code+"    "+name+"    "+price);
        double sumTotal = Double.parseDouble(quantity)*Double.parseDouble(price);
        printWriter.println("Subtotal   "+sumTotal);
        System.out.println("Sumtotal="+sumTotal);
        double taxes = sumTotal*0.13;
        printWriter.println("Taxes @13%     "+taxes);
        printWriter.println("Total      "+(sumTotal+taxes));
        printWriter.close();
    }

    public static boolean compareInFile(String inputWord) {

        String word;
        String[] a;

        File file = new File("Product.txt");
        try {
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                word = input.next();
                a = word.split(",");
                if (inputWord.equals(a[0])) {
                    System.out.println("CODE HAS BEEN FOUND");
                    return true;
                }
            }
        } catch (Exception error) {
            System.err.println(error);
        }
        return false;
    }

    public static String getInfo(String inputCode,String quant) {
        String word;
        String[] a;

        File file = new File("Product.txt");
        try {
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                word = input.next();
                a = word.split(",");
                if (inputCode.equals(a[0])) {
//                    double p =0;
//                    p += Double.parseDouble(a[2]);
                   // System.out.println("Sumtotal p: "+p);
                    writeBill(quant,inputCode,a[1],a[2]);
                }
            }
        } catch (Exception error) {
            System.err.println(error);
        }
        return null;
    }

}

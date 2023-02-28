import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class kirsing_4 {
    public static void main(String[] args){
        System.out.println("Ül 1:");
        ül1("1.txt","2.txt");
        System.out.println("Ül 2:");
        ül2("in3.txt", "out.txt");
    }
    static void ül1(String sisendfail1, String sisendfail2){
        File sisend1 = new File(sisendfail1);
        File sisend2 = new File(sisendfail2);
        int rida = 0;
        try {
            Scanner reader = new Scanner(sisend1);
            while(reader.hasNextLine()){
                String text = reader.nextLine();
                rida ++;
                if(rida == 1 || rida == 5){
                    System.out.println("Rida "+ rida + " failis 1: " + text);
                }
            }
            if(rida < 5){
                System.out.println("Failis 2 pole nii palju ridu!");
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Viga failiga: " +sisendfail1);
            e.printStackTrace();
        }
        try {
            rida = 0;
            Scanner reader = new Scanner(sisend2);
            while(reader.hasNextLine()){
                String text = reader.nextLine();
                rida ++;
                if(rida == 1 || rida == 5){
                    System.out.println("Rida "+ rida + " failis 2: " + text);
                }
            }
            if(rida < 5){
                System.out.println("Failis 2 pole piisavalt ridu!");
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Viga failiga: " +sisendfail2);
            e.printStackTrace();
        }
    }
    static void ül2(String sisendfail, String väljundfail){
        File sisend = new File(sisendfail);
        File väljund = new File(väljundfail);
        String[] uustekst = {};
        try {
            if(!väljund.createNewFile()){
                väljund.delete();
                boolean fail = väljund.createNewFile();
            }
            Scanner reader = new Scanner(sisend);
            while(reader.hasNextLine()){
                String tmp = "";
                String text = reader.nextLine();
                String[] tekst = text.split(" ");
                for(int i=0; i<tekst.length;i++){
                    try {  
                        int number = Integer.parseInt(tekst[i]);
                    } catch(NumberFormatException e){
                        tmp = tmp + tekst[i] + " ";
                    }
                }
                int n = uustekst.length;
                uustekst = Arrays.copyOf(uustekst, n+1);
                uustekst[n] = tmp;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Esines viga!");
            e.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter(väljund);
            for(int i = 0; i < uustekst.length; i++){
                writer.write(uustekst[i] + "\n");
            }
            writer.close();
            System.out.println("Puhastatud tekst kirjutatud faili: " + väljundfail);
        } catch (IOException e) {
            System.out.println("Faili kirjutamisel tekkis viga!");
            e.printStackTrace();
        }
    }
}

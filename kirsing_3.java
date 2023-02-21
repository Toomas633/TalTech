import java.util.Arrays;
import java.util.Scanner;

public class kirsing_3 {
    public static void main(String[] args){
        System.out.println("Ülesanne 1: ");
        //ül1();
        System.out.println("Ülesanne 2:");
        ül2();
    }
    public static void ül1(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Sisesta esimene rida: ");
        String es = sc.nextLine();
        System.out.print("Sisesta teine rida: ");
        String te = sc.nextLine();
        if(es.equals(te)) System.out.println("Read on samad");
        else System.out.println("Read ei ole samad");
        sc.close();
    }
    public static void ül2(){
        Scanner sc=new Scanner(System.in);
        int num, sum = 0;
        while(true){
            System.out.print("Sisesta number (1-5): ");
            num = sc.nextInt();
            if(num >= 1 && num <= 5){
                int[] arr = new int[num];
                System.out.print("Sisesta massiivi numbrid: ");
                for(int i=0; i < arr.length; i++){
                    arr[i] = sc.nextInt();
                }
                System.out.println("Sisestatud massiiv on: " + Arrays.toString(arr));
                for(int i=0; i < arr.length; i++){
                    sum += arr[i];
                }
                System.out.println("Massiivi liikmete summa on: " + sum);
                break;
            }
            else System.out.println("Vale sisend!");
        }

        sc.close();
    }
}

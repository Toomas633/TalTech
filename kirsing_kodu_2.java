import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class kirsing_kodu_2 {
    public static void main(String[] args){
        System.out.println("Ülesanne 1: ");
        System.out.println(ül1());
        System.out.println("Ülesanne 2:");
         //ül2(Fail kust loetakse andmed, väljundfail õiges formaadis andmetele, väljundfail vales formaadis andmetele)
        ül2("kodu2_andmed.txt","kodu2_õiged.txt","kodu2_valed.txt");
    }
    static char leiaKõigeSagedasemNumber(String text){
        Map<Character, Integer> hm = new HashMap<Character, Integer>(); //numbrite loendamiseks hashmap
        int loend = 0;
        char num = ' ';
        char[] temp, numbrid = {} ,otsitavad = {'0','1','2','3','4','5','6','7','8','9'};
        temp = text.toCharArray(); // Konverdin sõne muutuja karakterite massiiviks
        for(int i = 0; i < temp.length; i++){ //Kui leidub nuber tekstis siis see salvestatakse
            for(int j =0; j < otsitavad.length; j++){
                if(temp[i] == otsitavad[j]){
                    int n = numbrid.length;
                    numbrid = Arrays.copyOf(numbrid, n+1);
                    numbrid[n] = temp[i];
                }
            }
        }
        if(numbrid.length == 0) return 'e'; //kui numbreid ei leidu tagastatakse e
        for(char x:numbrid){
            if(!hm.containsKey(x)) hm.put(x, 1); //Kui hashmapis ei leidu liiget siis see lisatakse
            else hm.put(x, hm.get(x)+1); //kui leidub hashmapis otsitakse leidumiste arv ja suurendatakse seda 1e korra
        }
        int max = (Collections.max(hm.values())); //leian suurima leidumiste korra
        for (Entry<Character, Integer> entry : hm.entrySet()) { //otsib kõik need võtmed millel selline väärtus on ja tagastab need
            if (entry.getValue()==max) {
                loend += 1;
                if(loend <= 1) num = entry.getKey();
                else { //Kui mitu numbrit leidus sama palju kordi
                    char temp2 = entry.getKey();
                    if(Character.getNumericValue(num) < Character.getNumericValue(temp2)) num=temp2; //võrdle omavahel ja tagasta suurem number
                }
            }
        }
        return num;
    }
    static String eemaldaKõigeSagedasemNumber(String text, char symbol){
        //Kuna millegipärast text.replace(String.valueOf(symbol),""); ega ka lihtsalt symbol ega mitte ükski teine versioon mis teistel töötab ss tegin nii pikemalt....
        char[] arr = {};
        for(int i = 0; i < text.length();i++){
            if(text.charAt(i) != symbol){
                int n = arr.length;
                arr = Arrays.copyOf(arr, n+1);
                arr[n] = text.charAt(i);
            }
        }
        return String.valueOf(arr);
    }
    static String ül1(){
        String text = "1777,1799,1997,1998 and 2007õöäü!!!!";
        //String text = ", and õöäü!!!!";
        text = text.replaceAll("[^\\p{ASCII}]", "."); //asenda kõik karakterid mis pole inglise tähestikus(ASCII) punktidega
        char symbol = leiaKõigeSagedasemNumber(text);
        if(symbol == 'e') return "Tekstis pole numbreid!"; //Kui numbreid ei leidunud
        return eemaldaKõigeSagedasemNumber(text, symbol); //eemaldatakse number ja tagastatakse muudetud tekst
    }
    static void convert(String sisendfail, String väljund1, String väljund2){
        File sisend = new File(sisendfail), valjund1 = new File(väljund1), valjund2 = new File(väljund2);
        FileWriter filewriter = null;
        String[][] vastused1={};
        String[] vastused2={};
        Boolean õige = true;
        String andmed = "", perenimi="", eesnimi="", isikukood="", palk="";
        try {
            if(!valjund1.createNewFile()){
                valjund1.delete();
                boolean fail1 = valjund1.createNewFile();
            }
            if(!valjund2.createNewFile()){
                valjund2.delete();
                boolean fail2 = valjund2.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Failide muutmisel esines viga!");
            e.printStackTrace();
            System.exit(0);
        }
        try{
            Scanner reader = new Scanner(sisend);
            while(reader.hasNextLine()){
                õige = true;
                andmed = reader.nextLine();
                int count = 0;
                for(int i = 0; i < andmed.length();i++){ //loe eraldajad ega andeid ei puuduks, kui puudub või on liiga palju väljastatakse valede faili
                    if(andmed.charAt(i) == '|') count ++;
                }
                if(count == 3){ //Kui andmed on õiges formaadis
                    String[] sisendid = andmed.split("\\|");
                    System.out.println(Arrays.toString(sisendid));
                    try{ //kui isikukoodi pole võimalik teha numbriformaati ehk pole isikukood, ei hakka isikukoodi süvakuti kontrolli tegema näiteandmete pärast
                        int tmp = Integer.parseInt(sisendid[0]);
                        isikukood = sisendid[0];
                    }
                    catch (NumberFormatException ex){
                        õige = false;
                    }
                    try{ //kui palka pole võimalik teha numbriformaati ehk pole isikukood, ei hakka isikukoodi süvakuti kontrolli tegema näiteandmete pärast
                        int tmp = Integer.parseInt(sisendid[3]);
                        palk = sisendid[3];
                    }
                    catch (NumberFormatException ex){
                        õige = false;
                    }
                    perenimi = sisendid[1];
                    eesnimi = sisendid[2];
                    if(õige){ //kui kontroll läbitud edukalt kirjutatakse faili
                        String[] temp = {"Perekonnanimi: "+perenimi, "Eesnimi: "+eesnimi, "Isikukood: "+isikukood, "Palk: "+palk};
                        int n = vastused1.length;
                        vastused1 = Arrays.copyOf(vastused1, n+1);
                        vastused1[n] = temp;

                    }
                    else{ //kontrolli ei läbitud kirjutatakse valede faili
                        int n = vastused2.length;
                        vastused2 = Arrays.copyOf(vastused2, n+1);
                        vastused1[n] = andmed;
                    }
                }
                else{ //kontrolli ei läbitud kirjutatakse valede faili
                    filewriter = new FileWriter(valjund2);
                    filewriter.write(andmed);
                    filewriter.close();
                }
            }
            reader.close();
        }
        catch(IOException e){ //kui sisendfaili ei leidu tagastatakse veateade ja väljutakse
            System.out.println("Sisendfaili ei leitud!");
            e.printStackTrace();
            System.exit(0);
        }
    }
    static void ül2(String sisend, String väljund1, String väljund2){
        convert(sisend, väljund1, väljund2);
    }
}

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
        //Fail kust loetakse andmed, väljundfail õiges formaadis andmetele, väljundfail vales formaadis andmetele
        String sisendandmed = "kodu2_andmed.txt", väljund1 = "kodu2_õiged.txt", väljund2="kodu2_valed.txt";
        //Fail kust otsitakse rida ning otsitav sõna
        String sisendandmed2="kodu2_andmed2.txt", otsitav = "Jaanus";
        ül2(sisendandmed, väljund1, väljund2, sisendandmed2, otsitav);
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
        FileWriter writer = null;
        String[][] vastused1={};
        String[] vastused2={};
        Boolean õige = true;
        String andmed = "", perenimi="", eesnimi="", isikukood="", palk="";
        try {
            if(!valjund1.createNewFile()){
                valjund1.delete();
                boolean fail = valjund1.createNewFile();
            }
            if(!valjund2.createNewFile()){
                valjund2.delete();
                boolean fail = valjund2.createNewFile();
            }
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
                    isikukood  = sisendid[0];
                    if(isikukood.length() == 11) {//Isikukoodi pikkuse kontroll
                        if(isikukood.charAt(0) >= '1' && isikukood.charAt(0) <= '6'){ //isikukoodis aasta kontroll
                            //isikukoodis kuu kontroll, ei hakka siia kuude päevade täpset kontrolli panema
                            if((isikukood.charAt(1) == '0' && isikukood.charAt(2) >= '1' &&  isikukood.charAt(2) <= '9') || (isikukood.charAt(1) == '1' && isikukood.charAt(2) >= '1' &&  isikukood.charAt(2) <= '2')){
                                if(!(isikukood.charAt(5) == '0' && isikukood.charAt(6) >= '1' && isikukood.charAt(6) <= '9') && !(isikukood.charAt(5) >= '1' && isikukood.charAt(5) <= '2' && isikukood.charAt(6) >= '0' && isikukood.charAt(6) <= '9') && !(isikukood.charAt(5) == '3' && isikukood.charAt(6) >= '0' && isikukood.charAt(6) <= '1')){
                                    õige=false;
                                }
                            }
                            else õige = false;
                        }
                        else õige = false;
                    }
                    else õige = false;
                    try{ //kui palka pole võimalik teha numbriformaati
                        int tmp = Integer.parseInt(sisendid[3]);
                        palk = sisendid[3];
                    }
                    catch (NumberFormatException ex){õige = false;}
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
                        vastused2[n] = andmed;
                    }
                }
                else{ //kontrolli ei läbitud kirjutatakse valede faili
                    int n = vastused2.length;
                    vastused2 = Arrays.copyOf(vastused2, n+1);
                    vastused2[n] = andmed;
                }
            }
            reader.close();
            writer = new FileWriter(valjund1);
            for(int i = 0; i < vastused1.length; i++){
                for(int j = 0; j < vastused1[i].length; j++){
                    writer.write(vastused1[i][j]+"\n");
                }
                writer.write("\n");
            }
            writer.close();
            writer = new FileWriter(valjund2);
            for(int i = 0; i < vastused2.length; i++){
                writer.write(vastused2[i]+"\n");
            }
            writer.close();
            System.out.println("Failid muudetud edukalt!");
        } catch(IOException e){ //kui sisendfaili ei leidu tagastatakse veateade ja väljutakse
            System.out.println("Failide muutmisel esine viga!");
            e.printStackTrace();
        }
    }
    static void eemaldaRida(String sisendfail, String otsitav){
        File sisend = new File(sisendfail);
        String[] tulemus = {};
        try {
            Scanner reader = new Scanner(sisend);
            while(reader.hasNextLine()){
                String rida = reader.nextLine();
                if(!rida.contains(otsitav)){ //kui sisse loetud rida ei sisalda otsitavat sõne salvestatakse see muutujasse ja kirjutatakse hiljem faili
                    int n = tulemus.length;
                    tulemus = Arrays.copyOf(tulemus, n+1);
                    tulemus[n] = rida;
                }
            }
            if(!sisend.createNewFile()){
                sisend.delete();
                boolean fail = sisend.createNewFile();
            }
            reader.close();
            FileWriter writer = new FileWriter(sisendfail);
            for(int i = 0; i < tulemus.length; i++){
                writer.write(tulemus[i]+"\n");
            }
            writer.close();
            System.out.println("Rida eemaldatud edukalt!");
        } catch (IOException e) {
            System.out.println("Rea eemaldamisel esines viga!");
            e.printStackTrace();
        }
    }
    static void ül2(String sisend, String väljund1, String väljund2, String sisend2, String otsitav){
        System.out.println("Andmete sorteerimine...");
        convert(sisend, väljund1, väljund2);
        System.out.println("Eemaldan soovitud rea...");
        eemaldaRida(sisend2, otsitav);
    }
}
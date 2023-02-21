import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class kirsing_kodu_2 {
    public static void main(String[] args){
        System.out.println("Ülesanne 1: ");
        System.out.println(ül1());
        System.out.println("Ülesanne 2:");
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
}

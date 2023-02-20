import java.util.Arrays;

public class kirsing_kodu_1 {
    public static void main(String[] args){
        // vastav ülesanne mida käivitatakse ül1, ...2, ...3, ...4 vastavalt
        ül3(args);
    }
    public static void ül1(String[] args){
        String sisend="paaritu";
        char a, b, c;
        int keskmine=0;
        if(sisend.length() <= 1){ //sõne pikkuse kontroll
            System.out.println("Sõne on: " + sisend);
        }
        else {
            if(sisend.length() % 2 == 0){ //kas sõne pikkus on paaris või paaritu
                keskmine = sisend.length()/2;
                a = sisend.charAt(keskmine-1); //keskmine sümbol arvestades alguseks 0 mitte 1
                b = sisend.charAt(keskmine);
                System.out.println("Kaks keskmist sümbolit on: "+a+" "+b);
            }
            else{
                keskmine = (sisend.length()-1)/2;
                a = sisend.charAt(keskmine-1); //keskmine sümbol arvestades alguseks 0 mitte 1
                b = sisend.charAt(keskmine);
                c = sisend.charAt(keskmine+1);
                System.out.println("Kolm keskmist sümbolit on: "+a+" "+b+" "+c);
            }        
        }
    }
    public static void ül2(String[] args){
        String sisend="abaxaaaxc", alamsõna="aaa";
        boolean leidub=false;
        for(int i=0; i<sisend.length()-2; i++){
            int index = sisend.indexOf(alamsõna, i);
            if(index == 2 || index == 4){
                leidub = true;
            }
        }
        System.out.println(leidub);
    }
    public static void ül3(String[] args){
        int[] sisend={3,1,4,55,1,9,2};
        int otsitav=2, leitud=0;
        for(int i=0; i<sisend.length;i++){ //käi iga massiivi liikme kohta läbi
            if(sisend[i] == otsitav) leitud += 1; //iga kord kui leitakse massiivist otsitav suurendatakse muutujat 1e võrra
        }
        System.out.println(leitud);
    }
    public static void ül4(String[] args){
        String[] sisend ={"a","c","f","j","h"};
        if(sisend.length<3) System.out.println("Sõne pole piisavalt pikk!"); //kontolli sõne pikkust
        else{
            String viimane = sisend[sisend.length-1]; //salvesta viimane ja kolmas koht mällu
            String kolmas = sisend[sisend.length-3];
            sisend[sisend.length-3] = viimane; //tee vastavad vahetused
            sisend[sisend.length-1] = kolmas;
            System.out.println("Vahetatud kohtadega massiiv on: "+ Arrays.toString(sisend));
        }
    }
}
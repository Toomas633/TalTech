import java.util.Arrays;

public class kirsing_kodu_2 {
    public static void main(String[] args){
        System.out.println("Ülesanne 1: ");
        ül1();
    }
    static char leiaKõigeSagedasemNumber(String text){
        char num = 'e';
        char[] temp, numbrid={} ,otsitavad={'0','1','2','3','4','5','6','7','8','9'};
        temp = text.toCharArray(); // Konverdin sõne muutuja karakterite massiiviks
        for(int i = 0; i < temp.length; i++){
            for(int j =0; j < otsitavad.length; j++){
                if(temp[i] == otsitavad[j]){ //Kui leidub nuber tekstis siis see salvestatakse
                    int n = numbrid.length;
                    numbrid = Arrays.copyOf(numbrid, n+1);
                    numbrid[n] = temp[i];
                    System.out.println(numbrid[n]);
                }
            }
        }

        return num;
    }
    static String eemaldaKõigeSagedasemNumber(String text, char symbol){
        String vastus;
        return vastus;
    }
    static void ül1(){
        String text = "1777,1799,1997,1998 and 2007õöäü!!!!";
        char symbol = leiaKõigeSagedasemNumber(text);
        //System.out.println(eemaldaKõigeSagedasemNumber(text, symbol));

    }
}

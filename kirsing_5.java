import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class kirsing_5 {
    public static void main(String[] args){
        System.out.println("Ülesanne 1:");
        int[] andmed_1 = {5,499,202,345,66,3,5,78,3,341,51,2,3444,499};
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i : andmed_1) list.add(i);
        massiivHulkadeks(list);
        System.out.println("Ülesanne 2:");
        String[] andmed_2 ={"üks", "kaks", "kolm", "kaks", "neli", "üks", "viis", "kaks", "kolm", "neli", "viis", "üks", "üks", "kaks", "seitse"};
        ArrayList<String> list_2 = new ArrayList<String>();
        for (String i : andmed_2) list_2.add(i);
        System.out.println(loputäheKordused(list_2));
    }

    static void massiivHulkadeks(ArrayList<Integer> list){
        System.out.println("Esialgne list on: " + list);
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        for (int i : list) if (i > 100) list2.add(i);
        list.removeAll(list2);
        list2.sort(Comparator.naturalOrder());
        System.out.println("Väiksemad: " + list);
        System.out.println("Suuremad: " + list2);
    }

    static Map<Character,Integer> loputäheKordused(ArrayList<String> list_2){
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        for (String i : list_2){
            char l = i.charAt(i.length()-1);
            if(!map.containsKey(l)) map.put(l,1);
            else map.put(l, map.get(l)+1);
        }
        return map;
    }
}

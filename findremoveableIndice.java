import java.util.*;

public class findremoveableIndice {

    public static List<Integer> findRemovableIndices(String str1, String str2) {
        List<Integer> result = new ArrayList<>();
        
        // length check
        if (str1.length() != str2.length() + 1) {
            result.add(-1);
            return result;
        }
        
        for (int i = 0; i < str1.length(); i++) {
            // remove character at index i
            String modified = str1.substring(0, i) + str1.substring(i + 1);
            
            if (modified.equals(str2)) {
                result.add(i);
            }
        }
        
        // if no possible removal
        if (result.isEmpty()) {
            result.add(-1);
        }
        
        return result;
    }

    public static void main(String[] args) {
        String str1 = "abdgggda";
        String str2 = "abdggda";
        
        List<Integer> indices = findRemovableIndices(str1, str2);
        System.out.println(indices);  // Output: [3, 4, 5]
    }
}


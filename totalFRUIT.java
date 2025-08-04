import  java.util.*;

public class totalFRUIT {
    
     public int totalFruit(int[] fruits) {
        int ans = 0;
        Map<Integer, Integer> count = new HashMap<>();

        for(int l=0,r=0;r<fruits.length; ++r){
            count.merge(fruits[r], 1, Integer:: sum);
            while(count.size() >2){
                count.merge(fruits[l],-1, Integer::sum);

                count.remove(fruits[l],0);
                ++l;
            }
            ans=Math.max(ans, r-l+1);
        }
        return ans;
    }
    public static void main(String[] args) {
        int []arr={0,1,2,3,1};
        totalFRUIT fruit=new totalFRUIT();

        System.out.println(":"+fruit.totalFruit(arr));


    }

}

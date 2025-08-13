public class powofthree {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) return false;
        
        while (n % 3 == 0) {
            n /= 3;
        }
        
        return n == 1;
}  
public static void main(String[] args) {
    powofthree sol =new powofthree();

    int a=27;
    System.out.println(sol.isPowerOfThree(a));
} 
}

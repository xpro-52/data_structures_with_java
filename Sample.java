class Sample {
    
    static int f(int n) {
        if (n <= 1) {
            return n;
        }
        System.out.println("aaa");
        return f(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(f(8));
    }
}

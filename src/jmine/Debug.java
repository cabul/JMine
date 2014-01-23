package jmine;


public class Debug {

    public static void log(String tag, String info){
        System.out.println("\033[34m"+tag+": \t"+info+"\033[0m");
    }
    
    public static void log(String info){
        System.out.println("\033[32m"+info+"\033[0m");
    }
    
}

/**
 * Ian Falk
 * https://afteracademy.com/problems/valid-anagram
 */
public class Seven_Anagram {
    public static void main(String args[]) {
        String s1="admirer",s2="married";
        System.out.println("Input s1: "+s1+" , s2= "+s2+" ; Output: "+isEqual(s1,s2));
        s1="mindorks";
        s2="mindrocks";
        System.out.println("Input s1: "+s1+" , s2= "+s2+" ; Output: "+isEqual(s1,s2));
        s1="anagram";
        s2="graaamn";
        System.out.println("Input s1: "+s1+" , s2= "+s2+" ; Output: "+isEqual(s1,s2));
        s1="abcd";
        s2="abc";
        System.out.println("Input s1: "+s1+" , s2= "+s2+" ; Output: "+isEqual(s1,s2));
        s1="abc";
        s2="cba";
        System.out.println("Input s1: "+s1+" , s2= "+s2+" ; Output: "+isEqual(s1,s2));
    }

    public static int isEqual(String s1, String s2) {
        int val1=0;
        int val2=0;
        for(int i=0; i<s1.length(); i++) {
            val1+=(int)s1.charAt(i);
        }

        for(int j=0; j<s2.length(); j++) {
            val2+=(int)s2.charAt(j);
        }

        if(val1==val2) {
            return 1;
        } else {
            return 0;
        }
    }
}

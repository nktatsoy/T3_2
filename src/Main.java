import java.util.*;
import java.util.regex.Pattern;

class RomeNumbers{
    HashMap<Integer,String> ToRomeDigits = new HashMap<>();
    HashMap<String, Integer> ToArabicDigits = new HashMap<>();
    String Num;
    public RomeNumbers(String in){
        this.ToRomeDigits.put(1, "I");
        this.ToRomeDigits.put(2, "II");
        this.ToRomeDigits.put(3, "III");
        this.ToRomeDigits.put(4, "IV");
        this.ToRomeDigits.put(5, "V");
        this.ToRomeDigits.put(6, "VI");
        this.ToRomeDigits.put(7, "VII");
        this.ToRomeDigits.put(8, "VIII");
        this.ToRomeDigits.put(9, "IX");
        this.ToRomeDigits.put(10, "X");
        this.ToRomeDigits.put(20, "XX");
        this.ToRomeDigits.put(30, "XXX");
        this.ToRomeDigits.put(40, "XL");
        this.ToRomeDigits.put(50, "L");
        this.ToRomeDigits.put(60, "LX");
        this.ToRomeDigits.put(70, "LXX");
        this.ToRomeDigits.put(80, "LXXX");
        this.ToRomeDigits.put(90, "XC");
        this.ToRomeDigits.put(100, "C");

        this.ToArabicDigits.put("I",1);
        this.ToArabicDigits.put("II",2);
        this.ToArabicDigits.put("III",3);
        this.ToArabicDigits.put("IV",4);
        this.ToArabicDigits.put("V",5);
        this.ToArabicDigits.put("VI",6);
        this.ToArabicDigits.put("VII",7);
        this.ToArabicDigits.put("VIII",8);
        this.ToArabicDigits.put("IX",9);
        this.ToArabicDigits.put("X",10);

        this.Num=in;
    }

    String GetRome(int a){
        String Ans;
        int b,c;
        b=a/10;
        b=b*10;
        c=a%10;
        if(c==0){
            Ans=this.ToRomeDigits.get(b);
        }else if(b==0){
            Ans=this.ToRomeDigits.get(c);

        }else{
            Ans=this.ToRomeDigits.get(b)+this.ToRomeDigits.get(c);
        }

        return Ans;
    }

    int GetArabic(String a){
        return this.ToArabicDigits.get(a);
    }

    boolean isRome(String a){
        if(this.ToArabicDigits.get(a)==null){
            return false;
        }else{
            return true;
        }
    }
}

class MyException extends Exception{
    String str;
    MyException(String str2) {
        str=str2;
    }
}


public class Main{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String[] spliteds;
        String first=null,second=null;
        String opr=null;
        int answer=0;
        int a,b;
        RomeNumbers c=new RomeNumbers("XXX");


        try{
            if(s.contains("+")){
                opr="+";
                spliteds = s.split(Pattern.quote("+"), 2);
                first=spliteds[0];
                second=spliteds[1];
            }else if(s.contains("-")){
                opr="-";
                spliteds = s.split(Pattern.quote("-"), 2);
                first=spliteds[0];
                second=spliteds[1];
            }else if(s.contains("*")){
                opr="*";
                spliteds = s.split(Pattern.quote("*"), 2);
                first=spliteds[0];
                second=spliteds[1];
            }else if(s.contains("/")){
                opr="/";
                spliteds = s.split(Pattern.quote("/"), 2);
                first=spliteds[0];
                second=spliteds[1];
            }
            else {
                throw new MyException("Not suitable arithmetic expression!");
            }
        } catch (MyException e) {
            System.out.println(e.str);
            System.exit(0);
        }



        try{

            try{
                a=Integer.parseInt(first);
                b=Integer.parseInt(second);

                if(a>0&&a<11&&b>0&&b<11){
                    switch(opr){
                        case "+":
                            System.out.println(a+b);
                            break;
                        case "-":
                            System.out.println(a-b);
                            break;
                        case "*":
                            System.out.println(a*b);
                            break;
                        case "/":
                            System.out.println(a/b);
                            break;
                    }

                } else {
                    System.out.println("Not suitable arithmetic expression!");
                    System.exit(0);
                }


            } catch (NumberFormatException e) {
                throw new MyException("Not suitable arithmetic expression!");
            }

        } catch (MyException e) {

            try{
                if(c.isRome(first)&&c.isRome(second)){
                    a=c.GetArabic(first);
                    b=c.GetArabic(second);
                    switch(opr){
                        case "+":
                            System.out.println(c.GetRome(a+b));
                            break;
                        case "-":
                            if(a<b){
                                System.out.println("-"+c.GetRome(b-a));
                            }
                            else{
                                System.out.println(c.GetRome(a-b));
                            }
                            break;
                        case "*":
                            System.out.println(c.GetRome(a*b));
                            break;
                        case "/":
                            if(a<b){
                                System.out.println(0);
                            }else{
                                System.out.println(c.GetRome(a/b));
                            }
                            break;
                    }
                } else{
                    throw new MyException("Not suitable arithmetic expression!");
                }
            } catch (MyException e1) {
                System.out.println(e1.str);
                System.exit(0);
            }

        }

    }
}
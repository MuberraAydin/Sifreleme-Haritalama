import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList <Integer > keep_equality=new ArrayList<Integer>() ;
    public static ArrayList <String > keep_equality_hex=new ArrayList<String>() ;

    public static void main(String[] args) {
        System.out.println("İndirgenemez polinomu giriniz(Derecesi en yüksek x en başa gelecek şekilde) :  " );
        Scanner sc = new Scanner(System.in) ;
        String polynomial=sc.nextLine() ;
        int e=polynomial.indexOf('x');
        int polynomial_degree=0;
        switch (polynomial.charAt(e+1)) {
            case '1':
                polynomial_degree=1;
                break;
            case '2':
                polynomial_degree=2;
                break;
            case '3':
                polynomial_degree=3;
                break;
            case '4':
                polynomial_degree=4;
                break;
            default:
                System.out.println("Girdiğiniz polinom GF4 de tanımlı olmalıdır !!!   " );
                System.exit(0);
        }
        int polynomial_size = (int) Math.pow(polynomial_degree, 2);

        System.out.println("Sonlu Cismi Oluşturan Elemanlar Listeleniyor :  ");
        //x4+x+1 ------->  x4=x+1
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < polynomial_size; i++) {
            if (i < polynomial_degree) {
                double pow = Math.pow(2, i);
                keep_equality.add((int) pow);

            }else {
                if (keep_equality.get(i - 1) * keep_equality.get(1) > 15) {

                    String str1;
                    str1 = Integer.toBinaryString(keep_equality.get(i - 1)) + "0";
                    String str2 = "10011";
                    int size_str1 = str1.length();

                    for (int j = 0; j < size_str1; j++) {
                        sb.append(str1.charAt(j) ^ str2.charAt(j));
                    }

                    String keep = sb.toString();
                    int i_add = 0;
                    int aggregate = 0;
                    for (int j = keep.length() - 1; j > 0; j--) {
                        double us = Math.pow(2, i_add);
                        int a = Integer.valueOf(keep.charAt(j)) - 48;
                        aggregate = aggregate + (int) (us * a);
                        i_add++;
                    }
                    keep_equality.add((int) aggregate);
                    // System.out.println("sb int : "+aggregate);
                    sb.delete(-0, sb.length() + 1);

                } else {
                    keep_equality.add(keep_equality.get(i - 1) * keep_equality.get(1));
                    sb.delete(0, sb.length() + 1);
                }
            }
            System.out.println("a^" + i + " : a^" + i + "  ( " + Integer.toBinaryString(keep_equality.get(i))+ " - " + Integer.toHexString(keep_equality.get(i))+ ")" );
            
            keep_equality_hex.add(Integer.toHexString(keep_equality.get(i)));
        }

        // burda çıktıları haritalayacağız, değerlere git diyeceğiz
        System.out.println();
        System.out.println("Haritalamayı Giriniz:");
        System.out.print("x->x^(?): ");
        Scanner scc = new Scanner(System.in);
        String temp_data=scc.nextLine();
        int get_data=Integer .parseInt(temp_data) ;
        System.out.println("\nX -> X^("+get_data+") Haritalaması:");
        int n_or_p = temp_data.indexOf('-');
        int temp;
            System.out.println("Giriş:  Çıkış: ");
        System.out.println("0 ----> 0");
        for (int j = 1; j < polynomial_size; j++) {
            if (n_or_p == -1) {
                
                temp = j * get_data;

                if (temp > polynomial_size - 1) {
                    temp = (temp % (polynomial_size - 1));

                }
                System.out.println(Integer.toHexString(keep_equality.get(j))+" ----> " + keep_equality_hex.get(temp));
            }else{
                temp = j * Math .abs(get_data );

                if (temp > polynomial_size - 1) {
                    temp = (temp % (polynomial_size - 1));
                }
                System.out.println(Integer.toHexString(keep_equality.get(j))+" ----> " + keep_equality_hex.get(polynomial_size - 1-temp));
            }
        }
    }
}

package works;

import application.annotation.Argument;

public class Logic {

    @Argument
    public static void logicTest(String...args){
        int num = 123;
        String text = "abc";

        if(num > 60){
            System.out.println("C");
        } else if(num > 80){
            System.out.println("B");
        } else if(num > 90){
            System.out.println("A");
        } else {
            System.out.println("D");
        }

        switch (text) {
            case "A":
                System.out.println("A");
                break;
            default:
                System.out.println("Default");
                break;
        }

        int[] arr = new int[3];
        int[] arr2 = new int[]{1,2,3};
        int arr3[] = {1,2,3};
        int[][] arrs = {{}};
        int[][] arrs2 = new int[1][2];
        int[][] as[][][] = null;

        for(int i = 0 ; i < 3 ; i++){
            System.out.println(i);
        }

        for(int i = 0 ; i < arr.length ; i++){
            arr[i] = i;
        }
        for(int i : arr){
            System.out.println(i);
        }

        int j = 0;
        while(j < 9){
            System.out.println(j);

            if(j < 3){
                j++;
                continue;
            }else if(j > 5){
                j++;
                break;
            } else {
                j++;
            }
        }

        do{
            System.out.println(j);
        }while(j < 0);

        if(false)
            System.out.println("123");

        String test_1 = j > 123 ? "Y" : "N";
    }
}

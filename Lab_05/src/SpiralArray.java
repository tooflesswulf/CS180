import java.util.Scanner;

public class SpiralArray {
    private static boolean getCont(Scanner sc)
    {
        System.out.println("Do you want to generate another Spiral Array? (y/n)");
        String in = sc.nextLine();
        switch (in)
        {
            case "yes": case "y":
                return true;
            case "no": case "n":
                return false;
            default:
                System.out.print("ERROR: input only 'y' or 'n'. ");
                return getCont(sc);
        }
    }

    private static int getDim(Scanner sc)
    {
        System.out.println("What dimensions would you like?");

        int dim = sc.nextInt(); sc.nextLine();
        if (dim<1) {
            System.out.println("Please enter an integer greater than or equal to 1.");
            return getDim(sc);
        }

        return dim;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        boolean cont = true;
        System.out.println("Welcome to the Spiral Array Generator!");

        while(cont) {
            int dim = getDim(sc);
            printSpiral(dim);

            cont = getCont(sc);
        }

        System.out.println("Thank you for using the Spiral Array Generator!");
    }

    //Fills the array from [from] to [to] with values from counter, increasing.
    // Non-inclusive on start, inclusive on end point.
    private static int fillArr(int[][] arr,
                                int fromr, int fromc,
                                int tor, int toc,
                                int counter)
    {
        boolean col = fromr==tor; //whether to iterate across cols or rows
        if(col)
        {
            int sgn = Integer.signum(toc-fromc);
            for(int c=fromc+sgn; c!=toc; c+=sgn)
            {
                arr[tor][c] = counter;
                counter++;
            }
        } else {
            int sgn=Integer.signum(tor-fromr);
            for(int r=fromr+sgn; r!=tor; r+=sgn)
            {
                arr[r][toc] = counter;
                counter++;
            }
        }
        arr[tor][toc] = counter; //include the endpoint.
        counter++;
        return counter;
    }

    // First fills just the first row.
    // Then fills the rest of the rectangle using L's, decreasing in size.
    // Fill order shown below.
    // 1111
    // 4552
    // 4762
    // 3332
    public static void printSpiral(int size)
    {
        int[][] arr = new int[size][size];

        int counter = 1;

        //First we fill the first row.
        counter = fillArr(arr,
                0,-1,
                0,size-1,
                counter
        );

        //We can fill in the rest using L's.
        int inc_by = size-1;

        int curr=0, curc = size-1;
        int tor, toc;

        int sgn=1;

        while(inc_by > 0)
        {
            //Do vertical part of L
            tor=curr + sgn*inc_by;
            toc=curc;

            counter = fillArr(arr,
                    curr,curc,
                    tor, toc,
                    counter);
            curr=tor; curc=toc;

            //Do horizontal part of L
            tor=curr;
            toc=curc - sgn*inc_by;

            counter = fillArr(arr,
                    curr,curc,
                    tor, toc,
                    counter);
            curr=tor; curc=toc;

            //Update looping variables
            --inc_by;
            sgn *= -1;
        }

        print2Darr(arr);
    }

    private static void print2Darr(int[][] arr)
    {
        for(int[] a:arr)
        {
            for(int i:a) {
                System.out.print(i);
                System.out.print("\t");
            }
            System.out.print("\n");
        }
    }
}

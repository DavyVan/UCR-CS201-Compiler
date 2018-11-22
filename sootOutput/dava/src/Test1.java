import java.io.PrintStream;

public class Test1
{
    static long func1BB0ExeNum;
    static long func1BB1ExeNum;
    static long func1BB2ExeNum;
    static long func1BB3ExeNum;
    static long func1BB4ExeNum;
    static long func1BB5ExeNum;
    static long func1BB6ExeNum;
    static long func1BB7ExeNum;
    static long mainBB0ExeNum;

    public static void main(String[]  r0)
    {

        long l0, l1, l2, l3, l4, l5, l6, l7, l8, l9, l10;
        PrintStream r1;
        int i11, i12, i13, i14, i15, i16, i17, i18, i19;
        l0 = mainBB0ExeNum;
        l1 = l0 + 1L;
        mainBB0ExeNum = l1;
        Test1.func1(0);
        Test1.func1(95);
        r1 = System.out;
        r1.print("Method: <Test1: void main(java.lang.String[])>\n");
        r1.print("BB0: ");
        l2 = mainBB0ExeNum;
        i11 = (int) l2;
        r1.println(i11);
        r1.print("Method: <Test1: void func1(int)>\n");
        r1.print("BB0: ");
        l3 = func1BB0ExeNum;
        i12 = (int) l3;
        r1.println(i12);
        r1.print("BB1: ");
        l4 = func1BB1ExeNum;
        i13 = (int) l4;
        r1.println(i13);
        r1.print("BB2: ");
        l5 = func1BB2ExeNum;
        i14 = (int) l5;
        r1.println(i14);
        r1.print("BB3: ");
        l6 = func1BB3ExeNum;
        i15 = (int) l6;
        r1.println(i15);
        r1.print("BB4: ");
        l7 = func1BB4ExeNum;
        i16 = (int) l7;
        r1.println(i16);
        r1.print("BB5: ");
        l8 = func1BB5ExeNum;
        i17 = (int) l8;
        r1.println(i17);
        r1.print("BB6: ");
        l9 = func1BB6ExeNum;
        i18 = (int) l9;
        r1.println(i18);
        r1.print("BB7: ");
        l10 = func1BB7ExeNum;
        i19 = (int) l10;
        r1.println(i19);
    }

    public static void func1(int  i0)
    {

        int i1, i2;
        long l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16, l17, l18;
        l3 = func1BB0ExeNum;
        l4 = l3 + 1L;
        func1BB0ExeNum = l4;

        label_0:
        if (i0 != 0)
        {
            l7 = func1BB2ExeNum;
            l8 = l7 + 1L;
            func1BB2ExeNum = l8;

            while (true)
            {
                l9 = func1BB3ExeNum;
                l10 = l9 + 1L;
                func1BB3ExeNum = l10;
                i1 = i0 % 4;

                if (i1 == 0)
                {
                    l11 = func1BB4ExeNum;
                    l12 = l11 + 1L;
                    func1BB4ExeNum = l12;
                    l15 = func1BB6ExeNum;
                    l16 = l15 + 1L;
                    func1BB6ExeNum = l16;
                    break label_0;
                }

                l13 = func1BB5ExeNum;
                l14 = l13 + 1L;
                func1BB5ExeNum = l14;
                i2 = i0 / 4;
                i0 = i2;
            }
        }
        else
        {
            l5 = func1BB1ExeNum;
            l6 = l5 + 1L;
            func1BB1ExeNum = l6;
        }

        l17 = func1BB7ExeNum;
        l18 = l17 + 1L;
        func1BB7ExeNum = l18;
    }
}

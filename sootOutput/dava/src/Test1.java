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
    static long func1LastBlock;
    static long[] func1EdgeExeNumMatrix;
    static long mainBB0ExeNum;

    public static void main(String[]  r0)
    {

        long l0, l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16, l17, l18, l19;
        long[] r1, r3;
        PrintStream r2;
        int i20, i21, i22, i23, i24, i25, i26, i27, i28, i30, i31, i33, i34, i36, i37, i39, i40, i42, i43, i45, i46, i48, i49, i51, i52, i54, i55;
        r1 = new long[64];
        func1EdgeExeNumMatrix = r1;
        l0 = mainBB0ExeNum;
        l1 = l0 + 1L;
        mainBB0ExeNum = l1;
        Test1.func1(0);
        Test1.func1(95);
        r2 = System.out;
        r2.print("\n");
        r2.print("+----------------------------------\n");
        r2.print("| Basic Block Profiling\n");
        r2.print("+----------------------------------\n");
        r2.print("Method: <Test1: void main(java.lang.String[])>\n");
        r2.print("BB0: ");
        l2 = mainBB0ExeNum;
        i20 = (int) l2;
        r2.println(i20);
        r2.print("Method: <Test1: void func1(int)>\n");
        r2.print("BB0: ");
        l3 = func1BB0ExeNum;
        i21 = (int) l3;
        r2.println(i21);
        r2.print("BB1: ");
        l4 = func1BB1ExeNum;
        i22 = (int) l4;
        r2.println(i22);
        r2.print("BB2: ");
        l5 = func1BB2ExeNum;
        i23 = (int) l5;
        r2.println(i23);
        r2.print("BB3: ");
        l6 = func1BB3ExeNum;
        i24 = (int) l6;
        r2.println(i24);
        r2.print("BB4: ");
        l7 = func1BB4ExeNum;
        i25 = (int) l7;
        r2.println(i25);
        r2.print("BB5: ");
        l8 = func1BB5ExeNum;
        i26 = (int) l8;
        r2.println(i26);
        r2.print("BB6: ");
        l9 = func1BB6ExeNum;
        i27 = (int) l9;
        r2.println(i27);
        r2.print("BB7: ");
        l10 = func1BB7ExeNum;
        i28 = (int) l10;
        r2.println(i28);
        r2.print("\n");
        r2.print("+----------------------------------\n");
        r2.print("| Edge Profiling\n");
        r2.print("+----------------------------------\n");
        r2.print("Method: <Test1: void func1(int)>\n");
        r3 = func1EdgeExeNumMatrix;
        r2.print("BB0 -> BB1:  ");
        i30 = (int) 1L;
        l11 = r3[i30];
        i31 = (int) l11;
        r2.println(i31);
        r2.print("BB0 -> BB2:  ");
        i33 = (int) 2L;
        l12 = r3[i33];
        i34 = (int) l12;
        r2.println(i34);
        r2.print("BB1 -> BB7:  ");
        i36 = (int) 15L;
        l13 = r3[i36];
        i37 = (int) l13;
        r2.println(i37);
        r2.print("BB2 -> BB3:  ");
        i39 = (int) 19L;
        l14 = r3[i39];
        i40 = (int) l14;
        r2.println(i40);
        r2.print("BB3 -> BB4:  ");
        i42 = (int) 28L;
        l15 = r3[i42];
        i43 = (int) l15;
        r2.println(i43);
        r2.print("BB3 -> BB5:  ");
        i45 = (int) 29L;
        l16 = r3[i45];
        i46 = (int) l16;
        r2.println(i46);
        r2.print("BB4 -> BB6:  ");
        i48 = (int) 38L;
        l17 = r3[i48];
        i49 = (int) l17;
        r2.println(i49);
        r2.print("BB5 -> BB3:  ");
        i51 = (int) 43L;
        l18 = r3[i51];
        i52 = (int) l18;
        r2.println(i52);
        r2.print("BB6 -> BB7:  ");
        i54 = (int) 55L;
        l19 = r3[i54];
        i55 = (int) l19;
        r2.println(i55);
    }

    public static void func1(int  i0)
    {

        int i1, i2, i54, i55, i56, i57, i58, i59, i60, i61, i62, i63, i64, i65, i66, i67;
        long l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16, l17, l18, l19, l20, l21, l22, l23, l24, l25, l26, l27, l28, l29, l30, l31, l32, l33, l34, l35, l36, l37, l38, l39, l40, l41, l42, l43, l44, l45, l46, l47, l48, l49, l50, l51, l52, l53;
        long[] r0, r1, r2, r3, r4, r5, r6;
        l3 = func1BB0ExeNum;
        l5 = l3 + 1L;
        func1BB0ExeNum = l5;
        func1LastBlock = 0L;

        label_0:
        if (i0 != 0)
        {
            l12 = func1BB2ExeNum;
            l13 = l12 + 1L;
            func1BB2ExeNum = l13;
            l14 = func1LastBlock;
            l15 = l14 * 8L;
            l16 = l15 + 2L;
            r1 = func1EdgeExeNumMatrix;
            i56 = (int) l16;
            l17 = r1[i56];
            l18 = l17 + 1L;
            i57 = (int) l16;
            r1[i57] = l18;
            func1LastBlock = 2L;

            while (true)
            {
                l19 = func1BB3ExeNum;
                l20 = l19 + 1L;
                func1BB3ExeNum = l20;
                i1 = i0 % 4;
                l21 = func1LastBlock;
                l22 = l21 * 8L;
                l23 = l22 + 3L;
                r2 = func1EdgeExeNumMatrix;
                i58 = (int) l23;
                l24 = r2[i58];
                l25 = l24 + 1L;
                i59 = (int) l23;
                r2[i59] = l25;
                func1LastBlock = 3L;

                if (i1 == 0)
                {
                    l26 = func1BB4ExeNum;
                    l27 = l26 + 1L;
                    func1BB4ExeNum = l27;
                    l28 = func1LastBlock;
                    l29 = l28 * 8L;
                    l30 = l29 + 4L;
                    r3 = func1EdgeExeNumMatrix;
                    i60 = (int) l30;
                    l31 = r3[i60];
                    l32 = l31 + 1L;
                    i61 = (int) l30;
                    r3[i61] = l32;
                    func1LastBlock = 4L;
                    l40 = func1BB6ExeNum;
                    l41 = l40 + 1L;
                    func1BB6ExeNum = l41;
                    l42 = func1LastBlock;
                    l43 = l42 * 8L;
                    l44 = l43 + 6L;
                    r5 = func1EdgeExeNumMatrix;
                    i64 = (int) l44;
                    l45 = r5[i64];
                    l46 = l45 + 1L;
                    i65 = (int) l44;
                    r5[i65] = l46;
                    func1LastBlock = 6L;
                    break label_0;
                }

                l33 = func1BB5ExeNum;
                l34 = l33 + 1L;
                func1BB5ExeNum = l34;
                i2 = i0 / 4;
                i0 = i2;
                l35 = func1LastBlock;
                l36 = l35 * 8L;
                l37 = l36 + 5L;
                r4 = func1EdgeExeNumMatrix;
                i62 = (int) l37;
                l38 = r4[i62];
                l39 = l38 + 1L;
                i63 = (int) l37;
                r4[i63] = l39;
                func1LastBlock = 5L;
            }
        }
        else
        {
            l6 = func1BB1ExeNum;
            l7 = l6 + 1L;
            func1BB1ExeNum = l7;
            l8 = func1LastBlock;
            l9 = l8 * 8L;
            l10 = l9 + 1L;
            r0 = func1EdgeExeNumMatrix;
            i54 = (int) l10;
            l4 = r0[i54];
            l11 = l4 + 1L;
            i55 = (int) l10;
            r0[i55] = l11;
            func1LastBlock = 1L;
        }

        l47 = func1BB7ExeNum;
        l48 = l47 + 1L;
        func1BB7ExeNum = l48;
        l49 = func1LastBlock;
        l50 = l49 * 8L;
        l51 = l50 + 7L;
        r6 = func1EdgeExeNumMatrix;
        i66 = (int) l51;
        l52 = r6[i66];
        l53 = l52 + 1L;
        i67 = (int) l51;
        r6[i67] = l53;
        func1LastBlock = 7L;
    }
}

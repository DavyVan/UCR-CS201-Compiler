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

        long l0, l1, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16, l17, l18, l19, l20, l21, l22, l23, l24, l25, l26, l27, l28, l29, l30, l31, l32, l33, l35, l36, l37, l38, l39, l40, l41, l42, l43, l44, l45, l46, l47, l48, l49, l50, l51, l52, l53, l54, l55, l56, l57, l58, l59, l60, l61, l62, l63, l64, l65, l66, l67, l68, l69, l70, l71, l72;
        long[] r1, r3;
        float f1, f2, f3, f4, f5, f6, f7, f9, f10, f11, f12, f13, f14, f15, f16, f17, f18, f19, f20, f21, f22, f23, f24;
        PrintStream r2;
        int i73, i74, i75, i76, i77, i78, i79, i80, i81, i83, i84, i86, i87, i89, i90, i92, i93, i95, i96, i98, i99, i101, i102, i104, i105, i107, i108;
        r1 = new long[64];
        func1EdgeExeNumMatrix = r1;
        l0 = mainBB0ExeNum;
        l3 = l0 + 1L;
        mainBB0ExeNum = l3;
        Test1.func1(0);
        Test1.func1(95);
        r2 = System.out;
        r2.print("\n");
        r2.print("+----------------------------------\n");
        r2.print("| Basic Block Profiling\n");
        r2.print("+----------------------------------\n");
        r2.print("Method: <Test1: void main(java.lang.String[])>\n");
        r2.print("BB0: ");
        l4 = mainBB0ExeNum;
        i73 = (int) l4;
        r2.println(i73);
        r2.print("Method: <Test1: void func1(int)>\n");
        r2.print("BB0: ");
        l5 = func1BB0ExeNum;
        i74 = (int) l5;
        r2.println(i74);
        r2.print("BB1: ");
        l6 = func1BB1ExeNum;
        i75 = (int) l6;
        r2.println(i75);
        r2.print("BB2: ");
        l7 = func1BB2ExeNum;
        i76 = (int) l7;
        r2.println(i76);
        r2.print("BB3: ");
        l8 = func1BB3ExeNum;
        i77 = (int) l8;
        r2.println(i77);
        r2.print("BB4: ");
        l9 = func1BB4ExeNum;
        i78 = (int) l9;
        r2.println(i78);
        r2.print("BB5: ");
        l10 = func1BB5ExeNum;
        i79 = (int) l10;
        r2.println(i79);
        r2.print("BB6: ");
        l11 = func1BB6ExeNum;
        i80 = (int) l11;
        r2.println(i80);
        r2.print("BB7: ");
        l12 = func1BB7ExeNum;
        i81 = (int) l12;
        r2.println(i81);
        r2.print("\n");
        r2.print("+----------------------------------\n");
        r2.print("| Edge Profiling\n");
        r2.print("+----------------------------------\n");
        r2.print("Method: <Test1: void func1(int)>\n");
        r3 = func1EdgeExeNumMatrix;
        r2.print("BB0 -> BB1:  ");
        i83 = (int) 1L;
        l13 = r3[i83];
        i84 = (int) l13;
        r2.println(i84);
        r2.print("BB0 -> BB2:  ");
        i86 = (int) 2L;
        l14 = r3[i86];
        i87 = (int) l14;
        r2.println(i87);
        r2.print("BB1 -> BB7:  ");
        i89 = (int) 15L;
        l15 = r3[i89];
        i90 = (int) l15;
        r2.println(i90);
        r2.print("BB2 -> BB3:  ");
        i92 = (int) 19L;
        l16 = r3[i92];
        i93 = (int) l16;
        r2.println(i93);
        r2.print("BB3 -> BB4:  ");
        i95 = (int) 28L;
        l17 = r3[i95];
        i96 = (int) l17;
        r2.println(i96);
        r2.print("BB3 -> BB5:  ");
        i98 = (int) 29L;
        l18 = r3[i98];
        i99 = (int) l18;
        r2.println(i99);
        r2.print("BB4 -> BB6:  ");
        i101 = (int) 38L;
        l19 = r3[i101];
        i102 = (int) l19;
        r2.println(i102);
        r2.print("BB5 -> BB3:  ");
        i104 = (int) 43L;
        l20 = r3[i104];
        i105 = (int) l20;
        r2.println(i105);
        r2.print("BB6 -> BB7:  ");
        i107 = (int) 55L;
        l21 = r3[i107];
        i108 = (int) l21;
        r2.println(i108);
        r2.print("\n");
        r2.print("+----------------------------------\n");
        r2.print("| Average Number Of Variables Live\n");
        r2.print("| At An Executed Instruction\n");
        r2.print("+----------------------------------\n");
        r2.print("Method: <Test1: void main(java.lang.String[])>\n");
        l22 = mainBB0ExeNum;
        l1 = l22 * 0L;
        f1 = 0.0F + l1;
        l23 = 0L + l22;
        l24 = l22 * 0L;
        f2 = f1 + l24;
        l25 = l23 + l22;
        l26 = l22 * 1L;
        f3 = f2 + l26;
        l27 = l25 + l22;
        l28 = l22 * 2L;
        f4 = f3 + l28;
        l29 = l27 + l22;
        l30 = l22 * 1L;
        f5 = f4 + l30;
        l31 = l29 + l22;
        l32 = l22 * 0L;
        f6 = f5 + l32;
        l33 = l31 + l22;
        f7 = f6 / l33;
        r2.println(f7);
        r2.print("Method: <Test1: void func1(int)>\n");
        l35 = func1BB0ExeNum;
        l36 = l35 * 0L;
        f9 = 0.0F + l36;
        l37 = 0L + l35;
        l38 = l35 * 1L;
        f10 = f9 + l38;
        l39 = l37 + l35;
        l40 = func1BB1ExeNum;
        l41 = l40 * 0L;
        f11 = f10 + l41;
        l42 = l39 + l40;
        l43 = func1BB2ExeNum;
        l44 = l43 * 1L;
        f12 = f11 + l44;
        l45 = l42 + l43;
        l46 = func1BB3ExeNum;
        l47 = l46 * 1L;
        f13 = f12 + l47;
        l48 = l45 + l46;
        l49 = l46 * 1L;
        f14 = f13 + l49;
        l50 = l48 + l46;
        l51 = l46 * 2L;
        f15 = f14 + l51;
        l52 = l50 + l46;
        l53 = func1BB4ExeNum;
        l54 = l53 * 0L;
        f16 = f15 + l54;
        l55 = l52 + l53;
        l56 = func1BB5ExeNum;
        l57 = l56 * 1L;
        f17 = f16 + l57;
        l58 = l55 + l56;
        l59 = l56 * 1L;
        f18 = f17 + l59;
        l60 = l58 + l56;
        l61 = l56 * 1L;
        f19 = f18 + l61;
        l62 = l60 + l56;
        l63 = l56 * 1L;
        f20 = f19 + l63;
        l64 = l62 + l56;
        l65 = func1BB6ExeNum;
        l66 = l65 * 0L;
        f21 = f20 + l66;
        l67 = l64 + l65;
        l68 = func1BB7ExeNum;
        l69 = l68 * 0L;
        f22 = f21 + l69;
        l70 = l67 + l68;
        l71 = l68 * 0L;
        f23 = f22 + l71;
        l72 = l70 + l68;
        f24 = f23 / l72;
        r2.println(f24);
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

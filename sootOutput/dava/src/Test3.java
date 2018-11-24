import java.io.PrintStream;

public class Test3
{
    static long func1BB0ExeNum;
    static long func1BB1ExeNum;
    static long func1BB2ExeNum;
    static long func1BB3ExeNum;
    static long func1BB4ExeNum;
    static long func1LastBlock;
    static long[] func1EdgeExeNumMatrix;
    static long func2BB0ExeNum;
    static long func2BB1ExeNum;
    static long func2BB2ExeNum;
    static long func2BB3ExeNum;
    static long func2BB4ExeNum;
    static long func2LastBlock;
    static long[] func2EdgeExeNumMatrix;
    static long mainBB0ExeNum;

    public static void main(String[]  r0)
    {

        long l0, l1, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16, l17, l18, l19, l20, l21, l22, l23, l24, l25, l26, l27, l28, l29, l30, l31, l32, l34, l35, l36, l37, l38, l39, l40, l41, l42, l43, l44, l45, l46, l47, l48, l49, l50, l51, l52, l53, l54, l55, l56, l57, l58, l59, l60, l62, l63, l64, l65, l66, l67, l68, l69, l70, l71, l72, l73, l74, l75, l76, l77, l78, l79, l80, l81, l82, l83, l84, l85, l86, l87, l88;
        long[] r1, r3, r4, r5;
        float f1, f2, f3, f4, f5, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16, f17, f18, f20, f21, f22, f23, f24, f25, f26, f27, f28, f29, f30, f31;
        PrintStream r2;
        int i89, i90, i91, i92, i93, i94, i95, i96, i97, i98, i99, i101, i102, i104, i105, i107, i108, i110, i111, i113, i114, i116, i117, i119, i120, i122, i123, i125, i126, i128, i129;
        r1 = new long[25];
        func2EdgeExeNumMatrix = r1;
        r3 = new long[25];
        func1EdgeExeNumMatrix = r3;
        l0 = mainBB0ExeNum;
        l3 = l0 + 1L;
        mainBB0ExeNum = l3;
        Test3.func1(100);
        Test3.func2(10);
        r2 = System.out;
        r2.print("\n");
        r2.print("+----------------------------------\n");
        r2.print("| Basic Block Profiling\n");
        r2.print("+----------------------------------\n");
        r2.print("Method: <Test3: void main(java.lang.String[])>\n");
        r2.print("BB0: ");
        l4 = mainBB0ExeNum;
        i89 = (int) l4;
        r2.println(i89);
        r2.print("Method: <Test3: void func1(int)>\n");
        r2.print("BB0: ");
        l5 = func1BB0ExeNum;
        i90 = (int) l5;
        r2.println(i90);
        r2.print("BB1: ");
        l6 = func1BB1ExeNum;
        i91 = (int) l6;
        r2.println(i91);
        r2.print("BB2: ");
        l7 = func1BB2ExeNum;
        i92 = (int) l7;
        r2.println(i92);
        r2.print("BB3: ");
        l8 = func1BB3ExeNum;
        i93 = (int) l8;
        r2.println(i93);
        r2.print("BB4: ");
        l9 = func1BB4ExeNum;
        i94 = (int) l9;
        r2.println(i94);
        r2.print("Method: <Test3: void func2(int)>\n");
        r2.print("BB0: ");
        l10 = func2BB0ExeNum;
        i95 = (int) l10;
        r2.println(i95);
        r2.print("BB1: ");
        l11 = func2BB1ExeNum;
        i96 = (int) l11;
        r2.println(i96);
        r2.print("BB2: ");
        l12 = func2BB2ExeNum;
        i97 = (int) l12;
        r2.println(i97);
        r2.print("BB3: ");
        l13 = func2BB3ExeNum;
        i98 = (int) l13;
        r2.println(i98);
        r2.print("BB4: ");
        l14 = func2BB4ExeNum;
        i99 = (int) l14;
        r2.println(i99);
        r2.print("\n");
        r2.print("+----------------------------------\n");
        r2.print("| Edge Profiling\n");
        r2.print("+----------------------------------\n");
        r2.print("Method: <Test3: void func1(int)>\n");
        r4 = func1EdgeExeNumMatrix;
        r2.print("BB0 -> BB1:  ");
        i101 = (int) 1L;
        l15 = r4[i101];
        i102 = (int) l15;
        r2.println(i102);
        r2.print("BB1 -> BB2:  ");
        i104 = (int) 7L;
        l16 = r4[i104];
        i105 = (int) l16;
        r2.println(i105);
        r2.print("BB1 -> BB3:  ");
        i107 = (int) 8L;
        l17 = r4[i107];
        i108 = (int) l17;
        r2.println(i108);
        r2.print("BB2 -> BB4:  ");
        i110 = (int) 14L;
        l18 = r4[i110];
        i111 = (int) l18;
        r2.println(i111);
        r2.print("BB3 -> BB1:  ");
        i113 = (int) 16L;
        l19 = r4[i113];
        i114 = (int) l19;
        r2.println(i114);
        r2.print("Method: <Test3: void func2(int)>\n");
        r5 = func2EdgeExeNumMatrix;
        r2.print("BB0 -> BB1:  ");
        i116 = (int) 1L;
        l20 = r5[i116];
        i117 = (int) l20;
        r2.println(i117);
        r2.print("BB1 -> BB2:  ");
        i119 = (int) 7L;
        l21 = r5[i119];
        i120 = (int) l21;
        r2.println(i120);
        r2.print("BB1 -> BB3:  ");
        i122 = (int) 8L;
        l22 = r5[i122];
        i123 = (int) l22;
        r2.println(i123);
        r2.print("BB2 -> BB4:  ");
        i125 = (int) 14L;
        l23 = r5[i125];
        i126 = (int) l23;
        r2.println(i126);
        r2.print("BB3 -> BB1:  ");
        i128 = (int) 16L;
        l24 = r5[i128];
        i129 = (int) l24;
        r2.println(i129);
        r2.print("\n");
        r2.print("+----------------------------------\n");
        r2.print("| Average Number Of Variables Live\n");
        r2.print("| At An Executed Instruction\n");
        r2.print("+----------------------------------\n");
        r2.print("Method: <Test3: void main(java.lang.String[])>\n");
        l25 = mainBB0ExeNum;
        l1 = l25 * 0L;
        f1 = 0.0F + l1;
        l26 = 0L + l25;
        l27 = l25 * 0L;
        f2 = f1 + l27;
        l28 = l26 + l25;
        l29 = l25 * 0L;
        f3 = f2 + l29;
        l30 = l28 + l25;
        l31 = l25 * 0L;
        f4 = f3 + l31;
        l32 = l30 + l25;
        f5 = f4 / l32;
        r2.println(f5);
        r2.print("Method: <Test3: void func1(int)>\n");
        l34 = func1BB0ExeNum;
        l35 = l34 * 0L;
        f7 = 0.0F + l35;
        l36 = 0L + l34;
        l37 = func1BB1ExeNum;
        l38 = l37 * 1L;
        f8 = f7 + l38;
        l39 = l36 + l37;
        l40 = l37 * 1L;
        f9 = f8 + l40;
        l41 = l39 + l37;
        l42 = func1BB2ExeNum;
        l43 = l42 * 0L;
        f10 = f9 + l43;
        l44 = l41 + l42;
        l45 = func1BB3ExeNum;
        l46 = l45 * 1L;
        f11 = f10 + l46;
        l47 = l44 + l45;
        l48 = l45 * 1L;
        f12 = f11 + l48;
        l49 = l47 + l45;
        l50 = l45 * 1L;
        f13 = f12 + l50;
        l51 = l49 + l45;
        l52 = l45 * 1L;
        f14 = f13 + l52;
        l53 = l51 + l45;
        l54 = l45 * 1L;
        f15 = f14 + l54;
        l55 = l53 + l45;
        l56 = func1BB4ExeNum;
        l57 = l56 * 0L;
        f16 = f15 + l57;
        l58 = l55 + l56;
        l59 = l56 * 0L;
        f17 = f16 + l59;
        l60 = l58 + l56;
        f18 = f17 / l60;
        r2.println(f18);
        r2.print("Method: <Test3: void func2(int)>\n");
        l62 = func2BB0ExeNum;
        l63 = l62 * 0L;
        f20 = 0.0F + l63;
        l64 = 0L + l62;
        l65 = func2BB1ExeNum;
        l66 = l65 * 1L;
        f21 = f20 + l66;
        l67 = l64 + l65;
        l68 = l65 * 1L;
        f22 = f21 + l68;
        l69 = l67 + l65;
        l70 = func2BB2ExeNum;
        l71 = l70 * 0L;
        f23 = f22 + l71;
        l72 = l69 + l70;
        l73 = func2BB3ExeNum;
        l74 = l73 * 1L;
        f24 = f23 + l74;
        l75 = l72 + l73;
        l76 = l73 * 1L;
        f25 = f24 + l76;
        l77 = l75 + l73;
        l78 = l73 * 1L;
        f26 = f25 + l78;
        l79 = l77 + l73;
        l80 = l73 * 1L;
        f27 = f26 + l80;
        l81 = l79 + l73;
        l82 = l73 * 1L;
        f28 = f27 + l82;
        l83 = l81 + l73;
        l84 = func2BB4ExeNum;
        l85 = l84 * 0L;
        f29 = f28 + l85;
        l86 = l83 + l84;
        l87 = l84 * 0L;
        f30 = f29 + l87;
        l88 = l86 + l84;
        f31 = f30 / l88;
        r2.println(f31);
    }

    public static void func1(int  i0)
    {

        int i1, i32, i33, i34, i35, i36, i37, i38, i39;
        long l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16, l17, l18, l19, l20, l21, l22, l23, l24, l25, l26, l27, l28, l29, l30, l31;
        long[] r0, r1, r2, r3;
        l2 = func1BB0ExeNum;
        l4 = l2 + 1L;
        func1BB0ExeNum = l4;
        func1LastBlock = 0L;

        while (true)
        {
            l5 = func1BB1ExeNum;
            l6 = l5 + 1L;
            func1BB1ExeNum = l6;
            l7 = func1LastBlock;
            l8 = l7 * 5L;
            l9 = l8 + 1L;
            r0 = func1EdgeExeNumMatrix;
            i32 = (int) l9;
            l3 = r0[i32];
            l10 = l3 + 1L;
            i33 = (int) l9;
            r0[i33] = l10;
            func1LastBlock = 1L;

            if (i0 <= 0)
            {
                l11 = func1BB2ExeNum;
                l12 = l11 + 1L;
                func1BB2ExeNum = l12;
                l13 = func1LastBlock;
                l14 = l13 * 5L;
                l15 = l14 + 2L;
                r1 = func1EdgeExeNumMatrix;
                i34 = (int) l15;
                l16 = r1[i34];
                l17 = l16 + 1L;
                i35 = (int) l15;
                r1[i35] = l17;
                func1LastBlock = 2L;
                l25 = func1BB4ExeNum;
                l26 = l25 + 1L;
                func1BB4ExeNum = l26;
                l27 = func1LastBlock;
                l28 = l27 * 5L;
                l29 = l28 + 4L;
                r3 = func1EdgeExeNumMatrix;
                i38 = (int) l29;
                l30 = r3[i38];
                l31 = l30 + 1L;
                i39 = (int) l29;
                r3[i39] = l31;
                func1LastBlock = 4L;
                return;
            }

            l18 = func1BB3ExeNum;
            l19 = l18 + 1L;
            func1BB3ExeNum = l19;
            i1 = i0 - 1;
            i0 = i1;
            l20 = func1LastBlock;
            l21 = l20 * 5L;
            l22 = l21 + 3L;
            r2 = func1EdgeExeNumMatrix;
            i36 = (int) l22;
            l23 = r2[i36];
            l24 = l23 + 1L;
            i37 = (int) l22;
            r2[i37] = l24;
            func1LastBlock = 3L;
        }
    }

    public static void func2(int  i0)
    {

        int i1, i32, i33, i34, i35, i36, i37, i38, i39;
        long l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16, l17, l18, l19, l20, l21, l22, l23, l24, l25, l26, l27, l28, l29, l30, l31;
        long[] r0, r1, r2, r3;
        l2 = func2BB0ExeNum;
        l4 = l2 + 1L;
        func2BB0ExeNum = l4;
        func2LastBlock = 0L;

        while (true)
        {
            l5 = func2BB1ExeNum;
            l6 = l5 + 1L;
            func2BB1ExeNum = l6;
            l7 = func2LastBlock;
            l8 = l7 * 5L;
            l9 = l8 + 1L;
            r0 = func2EdgeExeNumMatrix;
            i32 = (int) l9;
            l3 = r0[i32];
            l10 = l3 + 1L;
            i33 = (int) l9;
            r0[i33] = l10;
            func2LastBlock = 1L;

            if (i0 >= 100)
            {
                l11 = func2BB2ExeNum;
                l12 = l11 + 1L;
                func2BB2ExeNum = l12;
                l13 = func2LastBlock;
                l14 = l13 * 5L;
                l15 = l14 + 2L;
                r1 = func2EdgeExeNumMatrix;
                i34 = (int) l15;
                l16 = r1[i34];
                l17 = l16 + 1L;
                i35 = (int) l15;
                r1[i35] = l17;
                func2LastBlock = 2L;
                l25 = func2BB4ExeNum;
                l26 = l25 + 1L;
                func2BB4ExeNum = l26;
                l27 = func2LastBlock;
                l28 = l27 * 5L;
                l29 = l28 + 4L;
                r3 = func2EdgeExeNumMatrix;
                i38 = (int) l29;
                l30 = r3[i38];
                l31 = l30 + 1L;
                i39 = (int) l29;
                r3[i39] = l31;
                func2LastBlock = 4L;
                return;
            }

            l18 = func2BB3ExeNum;
            l19 = l18 + 1L;
            func2BB3ExeNum = l19;
            i1 = i0 + 1;
            i0 = i1;
            l20 = func2LastBlock;
            l21 = l20 * 5L;
            l22 = l21 + 3L;
            r2 = func2EdgeExeNumMatrix;
            i36 = (int) l22;
            l23 = r2[i36];
            l24 = l23 + 1L;
            i37 = (int) l22;
            r2[i37] = l24;
            func2LastBlock = 3L;
        }
    }
}

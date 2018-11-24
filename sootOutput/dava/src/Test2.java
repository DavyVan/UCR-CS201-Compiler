
public class Test2
{

    public static void main(String[]  r0)
    {


        Test2.func1(20);
    }

    public static void func1(int  i0)
    {

        int i1, i3, i4;
        i1 = i0;

        while (i1 > 0)
        {

            if (i0 > 0)
            {
                i3 = i0 + SocketFlow.UNSET;
            }
            else
            {
                i4 = i1 + SocketFlow.UNSET;
                i1 = i4;
            }
        }
    }
}

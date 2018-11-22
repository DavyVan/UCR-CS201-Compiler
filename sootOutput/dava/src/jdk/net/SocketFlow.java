package jdk.net;


public class SocketFlow
{
    private static final int UNSET = -1;
    public static final int NORMAL_PRIORITY = 1;
    public static final int HIGH_PRIORITY = 2;
    private int priority;
    private long bandwidth;
    private SocketFlow$Status status;

    private SocketFlow()
    {

        priority = NORMAL_PRIORITY;
        bandwidth = -1L;
        status = SocketFlow$Status.NO_STATUS;
    }

    public static SocketFlow create()
    {


        return new SocketFlow();
    }

    public SocketFlow priority(int  i0)
    {


        if (i0 != NORMAL_PRIORITY && i0 != HIGH_PRIORITY)
        {
            throw new IllegalArgumentException("invalid priority");
        }

        priority = i0;
        return this;
    }

    public SocketFlow bandwidth(long  l0)
    {


        if (l0 - 0L >= 0)
        {
            bandwidth = l0;
            return this;
        }

        throw new IllegalArgumentException("invalid bandwidth");
    }

    public int priority()
    {


        return priority;
    }

    public long bandwidth()
    {


        return bandwidth;
    }

    public SocketFlow$Status status()
    {


        return status;
    }
}

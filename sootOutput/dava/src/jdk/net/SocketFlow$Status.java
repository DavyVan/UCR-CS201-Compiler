package jdk.net;


public final enum class SocketFlow$Status extends Enum
{
    public static final enum SocketFlow$Status NO_STATUS;
    public static final enum SocketFlow$Status OK;
    public static final enum SocketFlow$Status NO_PERMISSION;
    public static final enum SocketFlow$Status NOT_CONNECTED;
    public static final enum SocketFlow$Status NOT_SUPPORTED;
    public static final enum SocketFlow$Status ALREADY_CREATED;
    public static final enum SocketFlow$Status IN_PROGRESS;
    public static final enum SocketFlow$Status OTHER;
    private static final SocketFlow$Status[] $VALUES;

    public static SocketFlow$Status[] values()
    {


        return (SocketFlow$Status[]) $VALUES.clone();
    }

    public static SocketFlow$Status valueOf(String  r0)
    {


        return (SocketFlow$Status) Enum.valueOf(class "jdk/net/SocketFlow$Status", r0);
    }

    private SocketFlow$Status(String  r1, int  i0)
    {
        super(r1, i0);

    }

    static
    {

        NO_STATUS = new SocketFlow$Status("NO_STATUS", 0);
        OK = new SocketFlow$Status("OK", SocketFlow.NORMAL_PRIORITY);
        NO_PERMISSION = new SocketFlow$Status("NO_PERMISSION", SocketFlow.HIGH_PRIORITY);
        NOT_CONNECTED = new SocketFlow$Status("NOT_CONNECTED", 3);
        NOT_SUPPORTED = new SocketFlow$Status("NOT_SUPPORTED", 4);
        ALREADY_CREATED = new SocketFlow$Status("ALREADY_CREATED", 5);
        IN_PROGRESS = new SocketFlow$Status("IN_PROGRESS", 6);
        OTHER = new SocketFlow$Status("OTHER", 7);
        SocketFlow$Status[] $r8 = {NO_STATUS , OK , NO_PERMISSION , NOT_CONNECTED , NOT_SUPPORTED , ALREADY_CREATED , IN_PROGRESS , OTHER};
        $VALUES = $r8;
    }
}

package jdk.net;

import java.net.SocketOption;

public final class ExtendedSocketOptions
{
    public static final SocketOption SO_FLOW_SLA;

    static
    {


        SO_FLOW_SLA = new ExtendedSocketOptions$ExtSocketOption("SO_FLOW_SLA", class "jdk/net/SocketFlow");
    }
}

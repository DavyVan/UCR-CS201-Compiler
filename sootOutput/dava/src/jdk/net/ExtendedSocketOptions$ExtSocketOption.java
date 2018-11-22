package jdk.net;

import java.net.SocketOption;

class ExtendedSocketOptions$ExtSocketOption implements java.net.SocketOption
{
    private final String name;
    private final Class type;

    ExtendedSocketOptions$ExtSocketOption(String  r1, Class  r2)
    {

        name = r1;
        type = r2;
    }

    public String name()
    {


        return name;
    }

    public Class type()
    {


        return type;
    }

    public String toString()
    {


        return name;
    }
}

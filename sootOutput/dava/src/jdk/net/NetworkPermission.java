package jdk.net;

import java.security.BasicPermission;

public final class NetworkPermission extends BasicPermission
{
    private static final long serialVersionUID = -2012939586906722291l;

    public NetworkPermission(String  r1)
    {
        super(r1);

    }

    public NetworkPermission(String  r1, String  r2)
    {
        super(r1, r2);

    }
}

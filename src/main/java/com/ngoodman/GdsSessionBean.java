package com.ngoodman;

import java.util.concurrent.atomic.AtomicLong;

public class GdsSessionBean implements GdsSessionService
{

    private static AtomicLong idCounter = new AtomicLong();

    private static GdsSessionBean gdsSessionBean;

    private GdsSessionBean(){}

    public static GdsSessionBean getGdsSessionBean()
    {
        if (gdsSessionBean == null)
        {
            gdsSessionBean = new GdsSessionBean();
        }
        return gdsSessionBean;
    }

    public Session CreateSession()
    {
        return new Session(createID());
    }

    public void CloseSession(Session session)
    {

    }

    public void KeepAlive()
    {

    }

    private static long createID()
    {
        return idCounter.getAndIncrement();
    }
}

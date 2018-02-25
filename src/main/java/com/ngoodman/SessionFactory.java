package com.ngoodman;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class SessionFactory extends BasePooledObjectFactory<Session>
{
    public Session create()
    {
        GdsSessionBean gdsSessionBean = GdsSessionBean.getGdsSessionBean();
        return gdsSessionBean.CreateSession();
    }

    public void destroyObject(Session session)
    {
        GdsSessionBean gdsSessionBean = GdsSessionBean.getGdsSessionBean();
        gdsSessionBean.CloseSession(session);
    }

    public PooledObject<Session> wrap(Session session)
    {
        return new DefaultPooledObject<Session>(session);
    }
}

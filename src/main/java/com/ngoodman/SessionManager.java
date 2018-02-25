package com.ngoodman;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class SessionManager
{
    private ObjectPool<Session> pool;

    SessionManager(int maxPoolSize)
    {
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxTotal(maxPoolSize);


        GenericObjectPool<Session> genericObjectPool = new GenericObjectPool<Session>(new SessionFactory());
        genericObjectPool.setConfig(genericObjectPoolConfig);

        pool = genericObjectPool;
    }

    public Session requestSession() throws Exception
    {
        return pool.borrowObject();
    }

    public void returnSession(Session session) throws Exception
    {
        pool.returnObject(session);
    }

    public void invalidateSession(Session session) throws Exception
    {
        pool.invalidateObject(session);
    }

}

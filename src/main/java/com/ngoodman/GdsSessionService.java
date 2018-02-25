package com.ngoodman;

public interface GdsSessionService
{
    Session CreateSession();

    void CloseSession(Session session);

    void KeepAlive();
}

package com.gp.homework.rmi.Naming;

import java.rmi.Remote;

/**
 * Create by Wayne on 2020/7/20
 */
public interface IHello extends Remote {
    String sayHello(String name) throws java.rmi.RemoteException;
}

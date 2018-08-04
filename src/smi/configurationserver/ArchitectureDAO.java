/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smi.configurationserver;

import java.util.ArrayList;

/**
 *
 * @author Joyce-Kabala
 * @param <T>
 */
public interface ArchitectureDAO<T> {
    
    boolean create(T obj);
    
    ArrayList<T> listAll();
    
    boolean update(T obj);
    
    boolean deleteById(T obj);
    
    T searchById(int id);
    
    T searchByObject(T obj);
    
    Object findId(T obj);
}

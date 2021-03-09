package com.valid.repository.local

/**
 * Interface that manage the methods that can be used in local repository
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
interface ILocalRepositoryManager<T, D> {

    fun update(element : T)
    fun create(element : T)
    fun delete(element : T)
    fun read(element : T) : T
    fun removeAll(element : D)
    fun getAll(element : T) : ArrayList<T>
}
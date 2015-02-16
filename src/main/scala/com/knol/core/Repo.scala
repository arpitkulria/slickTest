package com.knol.core
 case class College(name:String, location:String, id:Int=0)
  
trait Repo {

 
  def insert(clg:College):Option[Int]
  def delete(id:Int):Option[Int]
  def update(clg:College):Option[Int]
  def show(id:Int):Option[College]
  
}

package com.knol.impl

import scala.slick.driver.PostgresDriver.simple._
import java.util.Date
import java.text.SimpleDateFormat
import scala.slick.lifted.ProvenShape
import com.knol.db.connection.DbConnection
import com.knol.core._
//import org.postgresql.Driver

object RepoImpl extends App with DbConnection with Repo {

  implicit val util2sqlDateMapper = MappedColumnType.base[java.util.Date, java.sql.Date](
    { utilDate => new java.sql.Date(utilDate.getTime()) },
    { sqlDate => new java.util.Date(sqlDate.getTime()) })

  class CollegeTable(tag: Tag) extends Table[College](tag, "college") {
    def id: Column[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def name: Column[String] = column[String]("name", O.NotNull)
    def location: Column[String] = column[String]("location", O.NotNull)
    def * = (name, location, id) <> (College.tupled, College.unapply)
  }

  val collegeTable = TableQuery[CollegeTable]
  val con = getConnection().get.withSession { implicit session =>
    (collegeTable.ddl).create

  }
  val ins=insert(College("IIIM","Jaipur",0))
  insert(College("RTU","Jaipur",0))
  //println(ins.get)
  
  update(College("I I I M","J P R",1))
  
  delete(1)
  val ans=show(2)
  println(ans)

  def insert(clg: College): Option[Int] = {

    val con = getConnection().get.withSession { implicit session =>

    val ans=collegeTable.insert(clg)
    
      Some(ans)
      

    }
    None
  }
  def delete(id: Int): Option[Int] = { 
    
     val con = getConnection().get.withSession { implicit session =>
       collegeTable.filter(_.id===1).delete
       
     }
    None }
    
  def update(clg: College): Option[Int] = {
    val con = getConnection().get.withSession { implicit session =>
      collegeTable.filter(_.id === 1).update(clg)
    }

    None
  }
  def show(id: Int): Option[College] = { 
    
     val con = getConnection().get.withSession { implicit session =>
    val collegeName = collegeTable.filter(_.id===2).list
     println(collegeName)
     }
    Some(College("abc","abc",3))
    
  }

}
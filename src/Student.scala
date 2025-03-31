package university

import scala.util.Random

class Student(name: String, gender: Char, age: Int, group:String, token:Token, addr: Address) extends Human(name, gender, age, token, addr) {
  private var _group: String = group
  private var _scholarship: Double = 0
  private var _grade: Int = 0

  def Group: String = _group

  def Scholarship: Double = _scholarship

  def Grade: Int = _grade

  def setGrade(score: Int) = {
    _grade = score
  }

  def setScholarship(rate: Double): Unit = {
    _scholarship = rate
  }

  def EstimateTeacher(): Int = {
    var score: Int = 0
    this.Grade match {
      case x if x >= 90 && x <= 100 => score = 5
      case x if x >= 80 && x <= 89 =>  score = 4
      case x if x >= 70 && x <= 79 =>  score = 3
      case x if x >= 60 && x <= 69 =>  score = 2
      case _ => score = 1
    }
    score
  }

  override def Details(): String =
    s"""
       |---Student Information---
       |Name:          ${name}
       |Age:           ${age}
       |Gender:        ${gender}
       |Group:         ${_group}
       |Grade:         ${_grade}
       |Scholarship:   ${_scholarship}
       |Token:         ${_token.Token_inf()}
       |Address:       ${addr.toString()}
     """.stripMargin

}
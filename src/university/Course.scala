package university

//import university.course_category._

import scala.util.Random

abstract class Course(title: String, category: CourseCategory, price: Token, start: Int, end: Int) {
  private var _title: String = title
  private var _category: CourseCategory = category
  private var _price: Token = price
  private var _start: Int = start
  private var _end: Int = end
  private var _available: Boolean = false
  private var _listSTD: List[Student] = List()

  def Title: String = _title
  def Category: CourseCategory = _category
  def Price: Token = _price
  def Start: Int = _start
  def End: Int = _end
  def Available: Boolean = _available
  def getListStudents: List[Student] = _listSTD

  def setAvailable(month: Int): Unit = {
    if (month == _start && _listSTD.nonEmpty)
      _available = true
    else if (month == _end)
      _available = false
  }

  def addStudent(std: Student): Unit = {
    _listSTD = _listSTD :+ std
  }

  def printListStudents(): String = {
    _listSTD.map(student =>
      f"* ${student.Name}%-10s : Grade: ${student.Grade}%-5s Scholarship: ${student.Scholarship}%-5s ; Balance:${student.getToken.Token_inf()}"
    ).mkString("\n")
  }

  def estimationStudent(): Unit = {
    _listSTD.foreach(el => {
      val score: Int = new Random().between(59, 100)
      el.setGrade(score)
    })
  }

  def PaymentForCourse(person: Human, value: Token) {
    println("[Payment....]")
    val exchange = person.getToken - value
    person.setToken(exchange)
    Platform.paymentTransaction(value)
  }

}

object Course {
  def apply(title: String, category: CourseCategory, price: Token, start: Int, end: Int): Course = {
    category match {
      case CourseCategory.SWE =>
        new SWE(title, price, start, end)
      case CourseCategory.Database =>
        new Database(title, price, start, end)
      case CourseCategory.DevOps =>
        new DevOps(title, price, start, end)
      case CourseCategory.Programming =>
        new Programming(title, price, start, end)
    }
  }
}

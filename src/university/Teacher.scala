package university

class Teacher(name: String, gender: Char, age: Int, amountStudents: Int,
              token: Token, addr: Address) extends Human(name, gender, age, token, addr) {
  private var _salary: Double = 0
  private var _amountStudents: Int = amountStudents
  private var _courses: List[Course] = List()
  private var _rating: Double = 0
  private var _amountCourses: Int = _courses.length
  private var _addingCourses: Boolean = true

  def Salary: Double = _salary
  def AmountStudents: Int = _amountStudents
  def getCourses: List[Course] = _courses
  def AmountCourses: Int = _amountCourses
  def Rating: Double = _rating

  def setRating(rating: Double): Unit = {
    var activeCourses: List[Course] = List()
    _courses.foreach{ course =>
      if (course.getListStudents.nonEmpty) {
          activeCourses = activeCourses :+ course
        }
    }
    _rating = rating / activeCourses.length
  }

  def addAmountCourses() = {
    _amountCourses = _amountCourses + 1
  }

  def setSalary(rate: Double) = {
    _salary = rate
  }

  def addCourse(course: Course): Unit = {
    if (_addingCourses) {
      if (course.Price < this.getToken) {
        println(s"Teacher ${this.Name} is going to add course '${course.Title}' (Category: ${course.Category}): ")
        course.PaymentForCourse(this, course.Price)
        _courses = _courses :+ course
        addAmountCourses()
        if (_amountCourses >= 2)
          _addingCourses = false
      }
      else {
        println(s"Teacher ${this.Name} is going to add course '${course.Title}': ")
        var res:Token = course.Price - this.getToken
        this.buy(res)
        course.PaymentForCourse(this, course.Price)
        _courses = _courses :+ course
        addAmountCourses()
        if (_amountCourses >= 2)
          _addingCourses = false
      }
    }

  }

  def removeCourse(course: Course): Unit = {
    _courses = _courses.filterNot(_ == course)
  }

  def AddingCourses(allowance: Boolean) {
    {
      _addingCourses = allowance
    }
  }

  override def Details(): String = {
    val coursesInfo = _courses.map(course =>
      s"${course.Title} (Category: ${course.Category.name}, Students: ${course.getListStudents.length})"
    ).mkString("\n")

    s"""
       |---Teacher Information---
       |Name:             ${name}
       |Age:              ${age}
       |Rating:           ${_rating}
       |Salary:           ${_salary}
       |Balance:          ${getToken.Token_inf()}
       |Total Students:   ${_amountStudents}
       |Can add courses:  ${_addingCourses}
       |Courses:
       |${coursesInfo}
       |Address:          ${addr.toString()}
     """.stripMargin
  }

}
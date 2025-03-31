package university

object Platform {
  private var _token:Token = new Token(1000000000,"SP")
  private var _data:DataList =_
  private var _end:Int =_

  def init(data:DataList, end:Int){
    this._data=data
    this._end = end
  }

  def get():(String) = (_token.Token_inf())

  def paymentTransaction(value:Token){
    println("[ "+value.Amount +" "+value.Symb+"]")
    this._token = _token + value
  }


  def sendScholarship(): Unit = {
    _data.getDataListTeachers.foreach { teacher =>
      teacher.getCourses.foreach { course =>
        if (course.getListStudents.nonEmpty) {
          course.getListStudents.foreach { student =>
            val rate: Double = ScholarshipCalc(student.Grade, course.Price.Amount)
            Transaction(rate, student)
          }
        }
      }
    }
  }

  def sendSalary() = {
    _data.getDataListTeachers.foreach { teacher =>
      teacher.getCourses.foreach { course =>
        if (course.getListStudents.nonEmpty) {
          val rate: Double = SalaryCalc(teacher.Rating, course.Price.Amount)
          Transaction(rate, teacher)
        }
      }
    }
  }

  def Transaction(rate:Double, person: Human){
    var token:Token = new Token(rate, "SP")
    person match {
      case student: Student =>
        println("Send:[" + " "+rate + " " + token.Symb  + "] From:Platform To:"+ student.Name)
        student.setScholarship(rate)
        student.addToken(token)
        _token = _token - token
      case teacher: Teacher =>
        println("Send:[" + " "+rate + " " + token.Symb  + "] From:Platform To:"+ teacher.Name)
        teacher.setSalary(rate)
        teacher.addToken(token)
        _token = _token - token
    }
  }

  def ScholarshipCalc(grade:Int, priceCourse:Double):Double = {
    var rate:Double = 0
    grade match {
      case x if x >= 90 && x <= 100 => rate = priceCourse * 1.5
      case x if x >= 80 && x <= 89 =>  rate = priceCourse * 1
      case x if x >= 70 && x <= 79 =>  rate = priceCourse * 0.75
      case x if x >= 60 && x <= 69 =>  rate = priceCourse * 0.5
      case _ => rate = 0
    }
    rate
  }

  def SalaryCalc(rating:Double, priceCourse:Double):Double = {
    var rate:Double = 0
    rating match {
      case x if x >= 4 && x <= 5 => rate = priceCourse * 1.5
      case x if x >= 3 && x < 4 => rate = priceCourse * 1
      case x if x >= 2 && x < 3 => rate = priceCourse * 0.75
      case x if x >= 1 && x < 2 => rate = priceCourse * 0.5
      case _ => rate = 0
    }
    rate
  }

  def tuitionFee(): Unit = {
    var listTeachers:List[Teacher] = _data.getDataListTeachers
    listTeachers.foreach(teacher=> {
      teacher.getCourses.foreach(course=>{
        course.getListStudents.foreach(student=>{
          if (course.Price < student.getToken){

            println(student.Name + " must pay:["+course.Price.Amount + " " + course.Price.Symb+"]; [" + student.getToken.Token_inf()+"]")
            course.PaymentForCourse(student, course.Price)
          }
          else {
            println(student.Name + " must pay:["+course.Price.Amount + " " + course.Price.Symb+"]; [" + student.getToken.Token_inf()+"]")
            var res:Token = course.Price - student.getToken
            student.buy(res)
            course.PaymentForCourse(student, course.Price)
          }
        })
      })
    })
  }

  def Teaching(students: List[Student]): Unit = {
    var month: Int = 1

    while (month <= this._end) {
      println(s"Month: $month")
      if (month == 1) {
        _data.EnrollToTeacher(students, 2)
        _data.PrintAllInformation(month)
      } else {
        _data.EstimationAllStudents()
        _data.EstimationAllTeachers()
        sendScholarship()
        tuitionFee()
        sendSalary()
        if (month == this._end)
          _data.UpdateTeacherStatus()
        _data.PrintAllInformation(month)
      }
      month = month + 1
    }
  }

}

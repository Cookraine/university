package university

import scala.util.Random

class DataList() {
  private var _dataListTeachers: List[Teacher] = List()

  def getDataListTeachers: List[Teacher] = _dataListTeachers

  def AddNewTeachers(t: Teacher): Unit = {
    _dataListTeachers = _dataListTeachers :+ t
  }

  def PrintAllInformation(month: Int): Unit = {
    _dataListTeachers.foreach { teacher =>
      if (month == 1 || teacher.getCourses.exists(_.getListStudents.nonEmpty)) {
        println(teacher.Details())
        teacher.getCourses.foreach { course =>
          if (course.getListStudents.nonEmpty) {
            println(s"Course: ${course.Title}\nStudents:\n${course.printListStudents()}\n")
          }
        }
      }
    }
  }

  def getTeacherByID(id: Int): Teacher = _dataListTeachers(id)

  def UpdateCourseAvailability(month: Int) = {
    _dataListTeachers.foreach { teacher =>
      teacher.getCourses.foreach { course =>
        course.setAvailable(month)
      }
    }
  }

  def EnrollToTeacher(students: List[Student], coursesPerStudent: Int): Unit = {
    val rand = new Random()
    students.foreach { student =>
      val randomTeachers = rand.shuffle(_dataListTeachers).take(rand.between(1, coursesPerStudent))
      randomTeachers.foreach { teacher =>
        val availableCourses = teacher.getCourses.filter(_.getListStudents.length < teacher.AmountStudents)
        if (availableCourses.nonEmpty) {
          val course = rand.shuffle(availableCourses).head
          course.addStudent(student)
        }
      }
    }
  }

  def EstimationAllStudents(): Unit = {
    _dataListTeachers.foreach { teacher =>
      teacher.getCourses.foreach { course =>
        if (course.getListStudents.nonEmpty) {
          course.estimationStudent()
        }
      }
    }
  }

  def EstimationAllTeachers() = {
    _dataListTeachers.foreach { teacher =>
      var totalScore: Double = 0
      teacher.getCourses.foreach { course =>
        var totalScoreCourse: Double = 0
        if (course.getListStudents.nonEmpty)
          {
          course.getListStudents.foreach { student =>
            totalScoreCourse = totalScoreCourse + student.EstimateTeacher()
          }
          var avgScoreCourse: Double = totalScoreCourse / course.getListStudents.length
          totalScore = totalScore + avgScoreCourse
        }
      }
      teacher.setRating(totalScore)
    }
  }

  def UpdateTeacherStatus() = {
    _dataListTeachers.foreach {teacher =>
      if (teacher.Rating >=4) {
        teacher.addAmountCourses()
        teacher.AddingCourses(true)
      }
    }
  }

}

package university

sealed trait CourseCategory {
  def category: String
}

object CourseCategory {
  case object SWE extends CourseCategory {
    override def category: String = "Software Engineering"
  }
  case object Database extends CourseCategory {
    override def category: String = "Database Design & Development"
  }
  case object DevOps extends CourseCategory {
    override def category: String = "DevOps"
  }
  case object Programming extends CourseCategory {
    override def category: String = "Programming"
  }
}

class Programming (title: String, price: Token, start: Int, end: Int) extends Course(title, CourseCategory.Database, price,  start, end)  {

}

class DevOps (title: String, price: Token, start: Int, end: Int) extends Course(title, CourseCategory.DevOps, price,  start, end) {

}

class SWE (title: String, price: Token, start: Int, end: Int) extends Course(title, CourseCategory.SWE, price,  start, end) {

}

class Database(title: String, price: Token, start: Int, end: Int) extends Course(title, CourseCategory.Database, price,  start, end) {

}


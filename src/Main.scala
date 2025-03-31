package university

import scala.collection.mutable.ListBuffer

object Program {
  def main(args: Array[String]) = {

    var token: Token = new Token(1000000000, "SP")
    Exchange.init(1000, token)

    var addr1 = new Address("Ukraine", "Ivano-Frankivsk")
    var addr2 = new Address("Ukraine", "Kherson")
    var addr3 = new Address("Ukraine", "Kyiv")

    var students = new ListBuffer[Student]()

    students += new Student("Maria", 'F', 19, "241", new Token(100, "SP"), addr1)
    students += new Student("Mykhailo", 'M', 22, "231", new Token(100, "SP"), addr2)
    students += new Student("Dmytro", 'M', 21, "241", new Token(100, "SP"), addr2)
    students += new Student("Olena", 'F', 20, "261", new Token(100, "SP"), addr1)
    students += new Student("Petro", 'M', 20, "231", new Token(100, "SP"), addr3)
    students += new Student("Valentyna", 'F', 23, "241", new Token(100, "SP"), addr2)
    students += new Student("Oleksii", 'M', 18, "261", new Token(100, "SP"), addr3)
    students += new Student("Oksana", 'F', 20, "231", new Token(100, "SP"), addr1)
    students += new Student("Maria", 'F', 21, "241", new Token(100, "SP"), addr1)
    students += new Student("Oleksandr", 'M', 22, "261", new Token(100, "SP"), addr3)

    val studentsList = students.toList

    var tchr1 = new Teacher("Maksym", 'M', 33, 5, new Token(200, "SP"), addr2)
    tchr1.addCourse(new Course("Data Science", new Token(70, "SP"), 1, 10))
    tchr1.addCourse(new Course("Machine Learning", new Token(80, "SP"), 4, 12))

    var tchr2 = new Teacher("Iryna", 'F', 47, 5, new Token(200, "SP"), addr1)
    tchr2.addCourse(new Course("C++", new Token(100, "SP"), 1, 10))
    tchr2.addCourse(new Course("Rust", new Token(95, "SP"), 3, 15))

    var tchr3 = new Teacher("Tetiana", 'F', 35, 5, new Token(200, "SP"), addr2)
    tchr3.addCourse(new Course("SQL", new Token(90, "SP"), 2, 10))
    tchr3.addCourse(new Course("Web Development", new Token (80, "SP"), 4, 12))

    var DATA_TEACHERS = new DataList()
    DATA_TEACHERS.AddNewTeachers(tchr1)
    DATA_TEACHERS.AddNewTeachers(tchr2)
    DATA_TEACHERS.AddNewTeachers(tchr3)

    Platform.init(DATA_TEACHERS, 15)
    println("Platform: " + Platform.get())
    Platform.Teaching(studentsList)

    println("Platform: " + Platform.get())
    println("Exchange: " + Exchange.get())

  }
}
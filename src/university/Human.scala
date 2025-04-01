package university

class Human(name: String, gender: Char, age: Int, token:Token, addr: Address) extends Trading {
  protected var _name:String = name
  protected var _age:Int = age
  protected var _gender:Char = gender
  protected var _addr:Address = addr

  protected var _token:Token = token

  def Name:String = _name
  def Age:Int = _age
  def Gender:Char = _gender
  def Addr:Address = _addr
  def getToken:Token = _token

  def setToken(token:Token){
    _token = token
  }

  def addToken(token:Token): Unit = {
    _token = _token + token
  }

  def Details():String = "Name "+_name + "\n" + "Age: " +_age + "\n" + "Gender:" + _gender + "\n" + "Address:\n" + _addr.toString()

  override def sell(amount: Token) = {
    _token = _token - amount
  }

  override def buy(amount:Token) = {
    _token = token + amount
    Exchange.buy(amount)
  }

}
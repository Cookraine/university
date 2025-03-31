package university

class Token(var amount:Double, var symb:String){
  private var _amount:Double  = amount
  private var _symb:String = symb

  def Amount:Double = _amount
  def Symb:String = _symb


  def Token_inf():String = "Amount: "+_amount + " " + _symb

  def +(value: Token): Token = {
    if (this.symb == value.symb)
      new Token(this.amount + value.amount, this.symb)
    else
      throw new IllegalArgumentException("Not match token symb!")
  }

  def -(value: Token): Token = {
    if (this.symb == value.symb && this.amount >= value.amount)
      new Token(this.amount - value.amount, this.symb)
    else
      throw new IllegalArgumentException("Not match token symb")
  }

  def >(value: Token): Boolean = {
    if (this.symb == value.symb)
      this.amount > value.amount
    else
      throw new IllegalArgumentException("Tokens have different symbols!")
  }

  def <(value: Token): Boolean = {
    if (this.symb == value.symb)
      this.amount < value.amount
    else
      throw new IllegalArgumentException("Tokens have different symbols!")
  }

}

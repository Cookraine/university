package university

object Exchange extends Trading {
  private var fiat: Double = _
  private var token: Token = _
  private var initialized: Boolean = false

  def init(fiat: Double,token: Token): Unit =  {
    if (!initialized) {
      this.fiat = fiat
      this.token = token
      initialized = true
      println(s"Exchange initialized: fiat = $fiat, token = $token")
    } else {
      println("Exchange is already initialized!")
    }
  }

  def get(): (Double, String) = (fiat, token.Token_inf())

  def getTokenPrice():Double = token.Amount / this.fiat

  override def buy(arg: Token): Unit =  {
    if (token.amount >= arg.amount) {
      token = token - arg
      fiat += arg.amount  * getTokenPrice()
    } else {

    }
  }

  override def sell(amount: Token): Unit =  {
    token = token + amount
    fiat -= amount.amount * getTokenPrice()
  }

}

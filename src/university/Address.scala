package university

class Address(country:String, city:String)
{
  private var _country:String = country
  private var _city:String = city

  def Country:String = _country
  def City:String = _city

  override def toString():String = "Country: " +_country + "\n" + "City:" +_city + "\n"

}

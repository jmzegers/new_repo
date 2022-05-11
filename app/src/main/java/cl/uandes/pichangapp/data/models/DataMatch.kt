package cl.uandes.pichangapp.data.models

open class DataMatch(
  open var teamOne: String,
  open var teamTwo: String,
  open var date: String,
  open var hour: String,
  open var place: String,
  open var category: String,
  open var rules: String,
  open var teamOnePoints: Int,
  open var teamTwoPoints: Int,
  open var time:String
)
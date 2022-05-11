package cl.uandes.pichangapp.data.models

data class StructureDataMatch(
  override var teamOne: String,
  override var teamTwo: String,
  override var date: String,
  override var hour: String,
  override var place: String,
  override var category: String,
  override var rules: String,
  override var teamOnePoints: Int,
  override var teamTwoPoints: Int,
  override var time : String,
  ): DataMatch (
  teamOne = teamOne,
  teamTwo = teamTwo,
  date = date,
  hour = hour,
  place = place,
  category = category,
  rules = rules,
  teamOnePoints = teamOnePoints,
  teamTwoPoints = teamTwoPoints,
  time = time
)
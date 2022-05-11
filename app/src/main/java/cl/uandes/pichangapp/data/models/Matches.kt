package cl.uandes.pichangapp.data.models

class Matches {
  companion object {
    fun MyNextMatches() : ArrayList<DataMatch> {
      val characters = ArrayList<DataMatch>()
      characters.add(StructureDataMatch("UANDES", teamTwo = "UAI",date ="2022-04-25" ,hour = "14:01", place = "Cancha de pasto principal", category = "Masculina", rules = "Amistoso", teamOnePoints = 5, teamTwoPoints = 2,time = "2022-04-25 14:01"))
      characters.add(StructureDataMatch("UANDES", teamTwo = "", date ="2022-06-25" ,hour = "14:00", place = "Cancha de pasto secundaria", category = "Masculina", rules = "Se prohibe ...", teamOnePoints = -3, teamTwoPoints = -3,time = "2022-06-25 14:00"))
      characters.add(StructureDataMatch("UANDES", teamTwo = "UCHILE",date ="2022-05-25" ,hour = "14:00", place = "Cancha de pasto secundaria", category = "Masculina", rules = "Se prohibe ...", teamOnePoints = -2, teamTwoPoints = -2,time = "2022-05-25 14:00"))
      characters.add(StructureDataMatch("UANDES", teamTwo = "BRAZIL",date ="1999-04-25" ,hour = "14:00", place = "Cancha de pasto princiapl", category = "Masculina", rules = "Se prohibe ...", teamOnePoints = 7, teamTwoPoints = 0,time = "1999-04-25 14:00"))
      characters.add(StructureDataMatch("UDD", teamTwo = "",date ="1000-04-25" ,hour = "14:00", place = "Cancha de pasto secundaria", category = "Masculina", rules = "Se prohibe ...", teamOnePoints = -1, teamTwoPoints = -1,time = "1000-04-25 14:00"))
      characters.add(StructureDataMatch("UAI", teamTwo = "UC",date ="2020-04-25" ,hour = "12:00", place = "Cancha de pasto principal", category = "Masculina", rules = "Sin reglas establecidas", teamOnePoints = -1, teamTwoPoints = -1,time = "2020-04-25 12:00"))
      characters.add(StructureDataMatch("UAI", teamTwo = "UNAB",date ="1001-04-25" ,hour = "14:00", place = "Cancha de pasto principal", category = "Masculina", rules = "Amistoso", teamOnePoints = -1, teamTwoPoints = -1,time = "1001-04-25 14:00"))
      characters.add(StructureDataMatch("UAI", teamTwo = "UNAB",date ="2022-07-25" ,hour = "14:00", place = "Cancha de pasto principal", category = "Masculina", rules = "Sin reglas establecidas", teamOnePoints = -1, teamTwoPoints = -1,time = "2022-07-25 14:00"))
      characters.add(StructureDataMatch("UAI", teamTwo = "",date ="2022-08-10" ,hour = "14:00", place = "Cancha de pasto principal", category = "Masculina", rules = "Sin reglas establecidas", teamOnePoints = -1, teamTwoPoints = -1,time = "2022-08-10 14:00"))
      characters.add(StructureDataMatch("UDD", teamTwo = "UANDES",date ="2022-04-25" ,hour = "14:00", place = "Cancha de pasto principal", category = "Masculina", rules = "Amistoso", teamOnePoints = -1, teamTwoPoints = -1,time = "2022-04-25 14:00"))
      return characters
    }
  }
}

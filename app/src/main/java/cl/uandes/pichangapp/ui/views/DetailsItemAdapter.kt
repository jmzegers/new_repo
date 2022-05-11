package cl.uandes.pichangapp.ui.views

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cl.uandes.pichangapp.R
import cl.uandes.pichangapp.data.models.DataMatch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter




class DetailsItemAdapter(
  private val fighters: MutableList<DataMatch>,
  private val actionListener: ActionListener
) : RecyclerView.Adapter<DetailsItemAdapter.ViewHolder>() {

  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // Buscamos una única vez los views de la vista que contiene nuestro item
    // en este caso, fighter_item.
    val TeamOne = itemView.findViewById<TextView>(R.id.TeamOneName)!!
    val TeamTwo = itemView.findViewById<TextView>(R.id.TeamTwoName)!!
    val fighterImage = itemView.findViewById<ImageView>(R.id.fighterImageView)!!
    val fighterImage2 = itemView.findViewById<ImageView>(R.id.fighterImageView2)!!
    val DateMatch = itemView.findViewById<TextView>(R.id.date)!!
    val HourMatch = itemView.findViewById<TextView>(R.id.hour)!!
    val PlaceMatch = itemView.findViewById<TextView>(R.id.place)!!
    val CategoryMatch = itemView.findViewById<TextView>(R.id.category)!!
    val RulesMatch = itemView.findViewById<TextView>(R.id.rules)!!
    val PointOne = itemView.findViewById<TextView>(R.id.pointone)!!
    val PointTwo = itemView.findViewById<TextView>(R.id.pointtwo)!!
    val ActionButton = itemView.findViewById<Button>(R.id.actionbutton)!!
    val fighterItem = itemView.findViewById<ViewGroup>(R.id.fighterItemDetails2)!!
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    // obtenemos el contexto del parent
    val context = parent.context
    // le pedimos el inflater al parent
    val inflater = LayoutInflater.from(context)

    // aqui "inflamos" la vista del parent con nuestro fighter item
    val fighterView: View = inflater.inflate(R.layout.details_item, parent, false)

    // para crear el viewholder, le pasamos la vista
    return ViewHolder(fighterView)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {


    fighters.sortBy { it.time } //ordeno la lista en tiempo de manera antiguo -> actual

    val fighter: DataMatch = fighters[position]

    val current = LocalDateTime.now() //Esto es para sacar mi tiempo actual
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val formatted = current.format(formatter)

    val teamone = holder.TeamOne
    val teamtwo = holder.TeamTwo
    val image = holder.fighterImage
    val image2 = holder.fighterImage2
    val date = holder.DateMatch
    val hour = holder.HourMatch
    val place = holder.PlaceMatch
    val category = holder.CategoryMatch
    val rules = holder.RulesMatch
    val pointone = holder.PointOne
    val pointtwo = holder.PointTwo
    val buttonaction = holder.ActionButton
    val detailsButton = holder.fighterItem

    fun clean(){
      detailsButton.visibility = View.GONE
      val params = detailsButton.layoutParams
      params.height = 0
      params.width = 0
      detailsButton.layoutParams = params
    }

    if (contextSituation == "homepage") {
      if (formatted < fighter.time && n == 0 && (elementId == fighter.teamOne) || (elementId == fighter.teamTwo)) {
        n = 1
        teamone.text = fighter.teamOne
        teamtwo.text = fighter.teamTwo
        image.setImageResource(R.mipmap.team1)
        if (fighter.teamTwo != "") {
          image2.setImageResource(R.mipmap.team1)
        } else {
          image2.setImageResource(R.mipmap.empty)
        }
        val s1 = "Fecha: "
        date.text = concat(s1, fighter.date)
        val s2 = "Hora: "
        hour.text = concat(s2, fighter.hour)
        val s3 = "Lugar: "
        place.text = concat(s3, fighter.place)
        val s4 = "Categoria: "
        category.text = concat(s4, fighter.category)
        val s5 = "Reglas: "
        rules.text = concat(s5, fighter.rules)
        pointone.visibility = View.INVISIBLE
        pointtwo.visibility = View.INVISIBLE
        buttonaction.text = "Retirarse"
      } else {
        clean()
      }
    } else {
      if (contextSituation == "search") {
        if ((formatted < fighter.time)&&(elementId != fighter.teamOne) && (elementId != fighter.teamTwo)){
          teamone.text = fighter.teamOne
          teamtwo.text = fighter.teamTwo
          image.setImageResource(R.mipmap.team1)
          if (fighter.teamTwo != "") {
            image2.setImageResource(R.mipmap.team1)
          } else {
            image2.setImageResource(R.mipmap.empty)
          }
          val s1 = "Fecha: "
          date.text = concat(s1, fighter.date)
          val s2 = "Hora: "
          hour.text = concat(s2, fighter.hour)
          val s3 = "Lugar: "
          place.text = concat(s3, fighter.place)
          val s4 = "Categoria: "
          category.text = concat(s4, fighter.category)
          val s5 = "Reglas: "
          rules.text = concat(s5, fighter.rules)
          pointone.visibility = View.INVISIBLE
          pointtwo.visibility = View.INVISIBLE
          if (fighter.teamTwo == "") {
            buttonaction.text = "Unirse"
          } else{
            buttonaction.visibility = View.INVISIBLE
          }
        } else {
          clean()
        }
      } else {
        if (contextSituation == "history") {
          if (formatted > fighter.time && ((elementId == fighter.teamOne) || (elementId == fighter.teamTwo)) && fighter.teamOnePoints >= 0) {
            teamone.text = fighter.teamOne
            teamtwo.text = fighter.teamTwo
            image.setImageResource(R.mipmap.team1)
            image2.setImageResource(R.mipmap.team1)
            val s1 = "Fecha: "
            date.text = concat(s1, fighter.date)
            val s2 = "Hora: "
            hour.text = concat(s2, fighter.hour)
            val s3 = "Lugar: "
            place.text = concat(s3, fighter.place)
            val s4 = "Categoria: "
            category.text = concat(s4, fighter.category)
            val s5 = "Reglas: "
            rules.text = concat(s5, fighter.rules)
            pointone.text = fighter.teamOnePoints.toString()
            pointtwo.text = fighter.teamTwoPoints.toString()
            buttonaction.visibility = View.INVISIBLE
          } else {
            clean()
          }
        } else {
          if (contextSituation == "rate") {
            if (formatted > fighter.time && ((elementId == fighter.teamOne) || (elementId == fighter.teamTwo)) && fighter.teamOnePoints <= -1) {
              teamone.text = fighter.teamOne
              teamtwo.text = fighter.teamTwo
              image.setImageResource(R.mipmap.team1)
              image2.setImageResource(R.mipmap.team1)
              val s1 = "Fecha: "
              date.text = concat(s1, fighter.date)
              val s2 = "Hora: "
              hour.text = concat(s2, fighter.hour)
              val s3 = "Lugar: "
              place.text = concat(s3, fighter.place)
              val s4 = "Categoria: "
              category.text = concat(s4, fighter.category)
              val s5 = "Reglas: "
              rules.text = concat(s5, fighter.rules)
              pointone.visibility = View.INVISIBLE
              pointtwo.visibility = View.INVISIBLE
              buttonaction.text = "Calificar"
            } else {
              clean()
            }
          } else{
            if (contextSituation == "ourPichangas") {
              if (formatted < fighter.time && (elementId == fighter.teamOne) || (elementId == fighter.teamTwo)) {
                teamone.text = fighter.teamOne
                teamtwo.text = fighter.teamTwo
                image.setImageResource(R.mipmap.team1)
                if (fighter.teamTwo != "") {
                  image2.setImageResource(R.mipmap.team1)
                } else {
                  image2.setImageResource(R.mipmap.empty)
                }
                val s1 = "Fecha: "
                date.text = concat(s1, fighter.date)
                val s2 = "Hora: "
                hour.text = concat(s2, fighter.hour)
                val s3 = "Lugar: "
                place.text = concat(s3, fighter.place)
                val s4 = "Categoria: "
                category.text = concat(s4, fighter.category)
                val s5 = "Reglas: "
                rules.text = concat(s5, fighter.rules)
                pointone.visibility = View.INVISIBLE
                pointtwo.visibility = View.INVISIBLE
                buttonaction.text = "Retirarse"
              } else {
                clean()
              }
            }
          }
        }
      }
    }
  }

  override fun getItemCount(): Int {
    return fighters.size
  }

  // esta funcion notifica al adapter cuando cambia el
  // dataset definido al principio, en el constructor
  @SuppressLint("NotifyDataSetChanged")
  fun updateFighters(newFighters: List<DataMatch>) {
    fighters.clear()

    fighters.addAll(newFighters)
    notifyDataSetChanged()
  }

  // esta funcion obtiene la clase de mi objeto
  private fun fighterType(fighter: DataMatch): String {
    return fighter.javaClass.simpleName
  }

  interface ActionListener {
    fun goToFighterDetails(fighter: DataMatch)
  }
}
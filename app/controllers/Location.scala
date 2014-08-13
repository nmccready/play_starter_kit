package controllers

import play.api._
import play.api.mvc._
import actions.Logging


trait ILocation extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def clean(location: String) = Logging(
    Action {
      Ok(views.html.index("Your new application is ready."))
    }
    , Some("clean")
  )
}

object Location extends ILocation